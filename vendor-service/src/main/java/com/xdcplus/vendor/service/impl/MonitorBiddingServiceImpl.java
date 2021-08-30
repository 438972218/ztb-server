package com.xdcplus.vendor.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xdcplus.vendor.common.enums.DirectionOfEnum;
import com.xdcplus.vendor.common.pojo.dto.OfferDTO;
import com.xdcplus.vendor.common.pojo.vo.OfferVO;
import com.xdcplus.vendor.common.pojo.vo.VendorUserVO;
import com.xdcplus.vendor.remote.service.FeignService;
import com.xdcplus.vendor.service.MonitorBiddingService;
import com.xdcplus.vendor.service.OfferService;
import com.xdcplus.vendor.service.VendorUserService;
import com.xdcplus.vendor.websocket.BiddingTimeMessage;
import com.xdcplus.vendor.websocket.SendMessage;
import com.xdcplus.vendor.websocket.VendorStateMessage;
import com.xdcplus.vendor.websocket.WebSocketServer;
import com.xdcplus.ztb.common.cache.RedisUtils;
import com.xdcplus.ztb.common.remote.domain.sourcing.PaidMaterialInfoVO;
import com.xdcplus.ztb.common.remote.domain.sourcing.PaidSheetInfoDTO;
import com.xdcplus.ztb.common.remote.domain.sourcing.PaidSheetInfoVO;
import com.xdcplus.ztb.common.tool.constants.CacheConstant;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 监控投标服务实现
 *
 * @author Rong.Jia
 * @date 2021/08/23
 */
@Slf4j
@Service
public class MonitorBiddingServiceImpl implements MonitorBiddingService {

    private static final String FROM_USER_ID = "server";
    private static final String PAID_STATUS = "已截止";

    @Autowired
    private VendorUserService vendorUserService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private FeignService feignService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Async
    public void messageHandler(String message, String userId) {

        if (StrUtil.isNotBlank(message)) {
            if (JSONValidator.from(message).validate()) {

                // 保存出价记录
                OfferDTO offerDTO = JSONObject.parseObject(message, OfferDTO.class);
                OfferVO offerVO = offerService.saveOffer(offerDTO);

                PaidSheetInfoVO paidSheetInfoVO = feignService.findPaidInvitationByRequestId(offerVO.getRequestId());
                if (ObjectUtil.isNotNull(paidSheetInfoVO)) {

                    String paidDirection = paidSheetInfoVO.getPaidDirection();
                    Boolean direction = DirectionOfEnum.POSITIVE.getValue().equalsIgnoreCase(paidDirection)
                            ? Boolean.TRUE : Boolean.FALSE;

                    offerVO.setRanking(offerService.getRanking(offerVO.getId(), direction));

                    sendMessage(userId, userId, NumberConstant.ONE, offerVO);

                    // 获取toUserId， 判断当前用户是内部端，还是外部端
                    if (!feignService.getSysRoleMarkByUserName(offerVO.getOfferUser())) {
                        if (ObjectUtil.isNotNull(paidSheetInfoVO)
                                && ObjectUtil.isNotNull(paidSheetInfoVO.getSysUserInfoVO())) {
                            Long id = paidSheetInfoVO.getSysUserInfoVO().getId();
                            List<OfferVO> offerVOList = offerService.findOfferByOfferGoods(offerVO.getOfferGoods(), direction);
                            sendMessage(userId, Convert.toStr(id), NumberConstant.ONE, offerVOList);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void sendClientState() {

        try {
            List<String> clientUsers = WebSocketServer.getClientUsers();
            if (CollectionUtil.isEmpty(clientUsers) || clientUsers.size() == NumberConstant.ZERO) {
                log.warn("No client is online");
                return;
            }

            List<VendorStateMessage> vendorStateMessageList = new ArrayList<>();
            for (String clientUser : clientUsers) {
                VendorUserVO vendorUserVO = vendorUserService.findVendorUserByUserId(Convert.toLong(clientUser));
                if (ObjectUtil.isNotNull(vendorUserVO)) {
                    VendorStateMessage vendorStateMessage = new VendorStateMessage();
                    vendorStateMessage.setState(NumberConstant.ONE);
                    vendorStateMessage.setVendorId(vendorUserVO.getVendorId());
                    vendorStateMessageList.add(vendorStateMessage);
                }
            }

            if (vendorStateMessageList.size() == NumberConstant.ZERO) {
                log.warn("No information about the supplier corresponding to the user has been obtained");
                return;
            }

            sendMessage(FROM_USER_ID, null, NumberConstant.TWO, vendorStateMessageList);
        } catch (Exception e) {
            log.error("BroadcastThread {}", e.getMessage());
        }
    }

    @Override
    @Async
    public void sendBiddingTime(@Nullable String userId, Long finalBiddingTime) {

        if (!redisUtils.hasKey(CacheConstant.BIDDING_TIMES)) {
            log.warn("There are no bidding goods for now");
            return;
        }

        // key: 物品标识, 竞价信息
        Map<String, BiddingTime> biddingTimeCacheMap = redisUtils.get(CacheConstant.BIDDING_TIMES, new TypeReference<Map<String, BiddingTime>>() {});
        if (CollectionUtil.isEmpty(biddingTimeCacheMap)) {
            log.warn("There are no bidding goods for now");
            return;
        }

        if (StrUtil.isBlank(userId)) {
            for (Map.Entry<String, BiddingTime> entry : biddingTimeCacheMap.entrySet()) {

                String key = entry.getKey();
                BiddingTime biddingTime = entry.getValue();
                Long currentTime = DateUtil.current();

                if (Validator.equal(NumberConstant.TWO, biddingTime.getPaidStatus())) {
                    continue;
                }

                BiddingTimeMessage biddingTimeMessage = BiddingTimeMessage.builder()
                        .computationTime(currentTime)
                        .endTime(biddingTime.getEndTime())
                        .startTime(biddingTime.getStartTime())
                        .offerGoods(key)
                        .requestId(biddingTime.getRequestId())
                        .delayedTime(NumberConstant.ZERO.longValue())
                        .countdown(biddingTime.getEndTime() - currentTime)
                        .timeOfDuration(currentTime - biddingTime.getStartTime())
                        .build();

                // 更新竞价状态
                if (biddingTime.getEndTime() >= currentTime) {
                    PaidSheetInfoDTO paidSheetInfoDTO = PaidSheetInfoDTO.builder()
                            .id(biddingTime.getPaidSheetId())
                            .paidStatus(PAID_STATUS)
                            .paidStatusMark(NumberConstant.FOUR).build();
                    feignService.updatePaidSheet(paidSheetInfoDTO);
                    refreshBiddingTimeInfo();
                }

                sendMessage(FROM_USER_ID, null, NumberConstant.THREE, biddingTimeMessage);
            }
        } else {

            biddingTimeCacheMap.entrySet().stream()
                    .filter(a -> StrUtil.equals(userId, Convert.toStr(a.getValue().getUserId())))
                    .findAny().ifPresent(a -> {
                        Long currentTime = DateUtil.current();
                        BiddingTime biddingTime = a.getValue();

                        Long endTime = biddingTime.getEndTime();
                        Long delayInterval = TimeUnit.MINUTES.toMillis(biddingTime.getDelayInterval());
                        Long triggeringTime = biddingTime.getEndTime() - delayInterval;
                        if (finalBiddingTime > triggeringTime) {
                            endTime = finalBiddingTime + delayInterval;
                        }
                        Long delayedTime = endTime - biddingTime.getEndTime();

                        BiddingTimeMessage biddingTimeMessage = BiddingTimeMessage.builder()
                                .requestId(biddingTime.getRequestId())
                                .computationTime(currentTime)
                                .endTime(endTime)
                                .startTime(biddingTime.getStartTime())
                                .offerGoods(a.getKey())
                                .delayedTime(delayedTime)
                                .countdown(biddingTime.getEndTime() - currentTime)
                                .timeOfDuration(currentTime - biddingTime.getStartTime())
                                .build();

                        sendMessage(FROM_USER_ID, null, NumberConstant.THREE, biddingTimeMessage);
                    });
        }
    }

    @Override
    public void refreshBiddingTimeInfo() {

        Map<String, BiddingTime> biddingTimeCacheMap = null;
        if (redisUtils.hasKey(CacheConstant.BIDDING_TIMES)) {
            biddingTimeCacheMap = redisUtils.get(CacheConstant.BIDDING_TIMES, new TypeReference<Map<String, BiddingTime>>() {
            });
        }

        List<PaidSheetInfoVO> paidSheetInfos = feignService.findPaidSheetInfos();
        if (CollectionUtil.isNotEmpty(paidSheetInfos)) {

            // key: 物品ID, 竞价信息
            Map<String, BiddingTime> biddingTimeMap = MapUtil.newHashMap();
            for (PaidSheetInfoVO paidSheetInfo : paidSheetInfos) {
                List<PaidMaterialInfoVO> paidMaterialInfoVOList = paidSheetInfo.getPaidMaterialVOS();
                if (CollectionUtil.isNotEmpty(paidMaterialInfoVOList)) {
                    for (PaidMaterialInfoVO paidMaterialInfoVO : paidMaterialInfoVOList) {
                        BiddingTime biddingTime = new BiddingTime();
                        biddingTime.setRequestId(paidSheetInfo.getRequestId());
                        biddingTime.setEndTime(paidMaterialInfoVO.getEndTime());
                        biddingTime.setPaidStatusName(paidMaterialInfoVO.getMaterialName());
                        biddingTime.setStartTime(paidMaterialInfoVO.getBeginTime());
                        biddingTime.setOfferGoods(Convert.toStr(paidMaterialInfoVO.getMaterialMark()));
                        biddingTime.setPaidStatus(paidSheetInfo.getPaidStatusMark());
                        biddingTime.setPaidDirection(paidSheetInfo.getPaidDirection());
                        if (ObjectUtil.isNotNull(paidSheetInfo) && ObjectUtil.isNotNull(paidSheetInfo.getSysUserInfoVO())) {
                            biddingTime.setUserId(paidSheetInfo.getSysUserInfoVO().getId());
                        }
                        biddingTimeMap.put(Convert.toStr(paidMaterialInfoVO.getMaterialMark()), biddingTime);
                    }
                }
            }

            if (MapUtil.isNotEmpty(biddingTimeCacheMap)) {
                biddingTimeCacheMap = biddingTimeMap;
            }
        }

        if (MapUtil.isNotEmpty(biddingTimeCacheMap)) {
            redisUtils.set(CacheConstant.BIDDING_TIMES, JSON.toJSONString(biddingTimeCacheMap));
        }
    }

    @Data
    private static class BiddingTime implements Serializable {

        private static final long serialVersionUID = 2805070377673290602L;

        /**
         * 表单ID
         */
        private Long requestId;

        /**
         * 竞价单id
         */
        private Long paidSheetId;

        /**
         * 物品标识
         */
        private String offerGoods;

        /**
         * 开始时间
         */
        private Long startTime;

        /**
         * 结束时间
         */
        private Long endTime;

        /**
         * 竞价状态，1：进行中，2：暂停，3：已终止，4：截止
         */
        private Integer paidStatus;

        /**
         * 竞价状态名
         */
        private String paidStatusName;

        /**
         * 竞价方向
         */
        private String paidDirection;

        /**
         * 用户ID
         */
        private Long userId;

        /**
         * 延时间隔
         */
        private Long delayInterval;

    }

    /**
     * 发送消息
     *
     * @param message    消息
     * @param fromUserId 发送人
     * @param type       类型
     * @param toUserId   接收人
     * @return {@link SendMessage}
     */
    private void sendMessage(String fromUserId, String toUserId, Integer type, Object message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setMessage(message);
        sendMessage.setFromUserId(fromUserId);
        sendMessage.setType(type);

        sendMessage(sendMessage, toUserId);
    }

    /**
     * 发送消息
     *
     * @param userId  用户id
     * @param message 消息
     */
    private void sendMessage(Object message, String userId) {
        WebSocketServer.sendMessage(jsonString(message), userId);
    }

    /**
     * json字符串
     *
     * @param object 对象
     * @return {@link String}
     */
    private String jsonString(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat);
    }

}

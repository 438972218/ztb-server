package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.workflow.common.pojo.bo.DiscussRecordBO;
import com.xdcplus.workflow.common.pojo.dto.DiscussGroupFilterDTO;
import com.xdcplus.workflow.common.pojo.dto.PostDiscussionsDTO;
import com.xdcplus.workflow.common.pojo.dto.ReplyDiscussDTO;
import com.xdcplus.workflow.common.pojo.entity.DiscussGroup;
import com.xdcplus.workflow.common.pojo.entity.DiscussRecordRelation;
import com.xdcplus.workflow.common.pojo.vo.DiscussGroupVO;
import com.xdcplus.workflow.common.pojo.vo.DiscussRecordVO;
import com.xdcplus.workflow.service.DiscussDistributionService;
import com.xdcplus.workflow.service.DiscussGroupService;
import com.xdcplus.workflow.service.DiscussRecordService;
import com.xdcplus.workflow.service.RequestService;
import com.xdcplus.ztb.common.mp.utils.AuthUtils;
import com.xdcplus.ztb.common.tool.constants.AuthConstant;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 讨论分发服务实现
 *
 * @author Rong.Jia
 * @date 2021/08/19 09:44:44
 */
@Service
public class DiscussDistributionServiceImpl implements DiscussDistributionService {

    @Autowired
    private DiscussGroupService discussGroupService;

    @Autowired
    private DiscussRecordService discussRecordService;

    @Autowired
    private RequestService requestService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void postDiscussions(PostDiscussionsDTO postDiscussionsDTO) {

        String currentUser = AuthUtils.getCurrentUser();
        Long group = discussGroupService.saveDiscussGroup(postDiscussionsDTO.getRequestId(),
                postDiscussionsDTO.getSubject(), currentUser);

        discussRecordService.saveDiscussRecord(group, postDiscussionsDTO.getContent(),
                currentUser, postDiscussionsDTO.getToUsers());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void replyDiscuss(ReplyDiscussDTO replyDiscussDTO) {

        discussRecordService.saveDiscussRecord(replyDiscussDTO.getGroupId(),
                replyDiscussDTO.getContent(), AuthUtils.getCurrentUser(),
                replyDiscussDTO.getToUsers());

    }

    @Override
    public List<DiscussGroupVO> findDiscussGroup(Long requestId) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        List<DiscussGroup> discussGroupList = discussGroupService.findDiscussionGroup(requestId, getCurrentUser());
        return copyProperties(discussGroupList);
    }

    @Override
    public PageVO<DiscussGroupVO> findDiscussGroup(DiscussGroupFilterDTO discussGroupFilterDTO) {

        PageVO<DiscussGroupVO> pageVO = new PageVO<>();

        if (discussGroupFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(discussGroupFilterDTO);
        }

        List<DiscussGroup> discussGroupList = discussGroupService.findDiscussionGroup(discussGroupFilterDTO.getRequestId(), getCurrentUser());
        PageInfo<DiscussGroup> pageInfo = new PageInfo<>(discussGroupList);

        PropertyUtils.copyProperties(pageInfo, pageVO, this.copyProperties(pageInfo.getList()));

        return pageVO;
    }

    @Override
    public List<DiscussRecordVO> findDiscussRecordsByGroupId(Long groupId) {

        Assert.notNull(groupId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        List<DiscussRecordBO> discussRecordBOList = discussRecordService.findDiscussRecordByGroupId(groupId, getCurrentUser());

        return copyRecordProperties(discussRecordBOList);
    }

    @Override
    public List<DiscussRecordVO> findDiscussRecordsByRequestId(Long requestId) {

        List<DiscussGroup> discussGroupList = discussGroupService.findDiscussionGroup(requestId, getCurrentUser());
        if (CollectionUtil.isNotEmpty(discussGroupList)) {
            Set<Long> groupIds = discussGroupList.stream().map(DiscussGroup::getId)
                    .collect(Collectors.toSet());
            return copyRecordProperties(discussRecordService.findDiscussRecordByGroupIds(groupIds, getCurrentUser()));
        }

        return null;
    }

    /**
     * 复制记录属性
     *
     * @param discussRecordBOList 讨论记录
     * @return {@link List<DiscussRecordVO>}
     */
    private List<DiscussRecordVO> copyRecordProperties(List<DiscussRecordBO> discussRecordBOList) {

        if (CollectionUtil.isNotEmpty(discussRecordBOList)) {
            return discussRecordBOList.stream().map(this::copyRecordProperties).collect(Collectors.toList());
        }

        return null;
    }

    /**
     * 复制属性
     *
     * @param discussRecordBO 讨论记录BO
     * @return {@link DiscussRecordVO}
     */
    private DiscussRecordVO copyRecordProperties(DiscussRecordBO discussRecordBO) {

        if (ObjectUtil.isNotNull(discussRecordBO)) {
            DiscussRecordVO discussRecordVO = new DiscussRecordVO();
            BeanUtil.copyProperties(discussRecordBO, discussRecordVO);

            List<DiscussRecordRelation> recordRelationList = discussRecordBO.getRelations();
            if (CollectionUtil.isNotEmpty(recordRelationList)) {
                discussRecordVO.setToUsers(recordRelationList.stream().map(DiscussRecordRelation::getToUser).collect(Collectors.toList()));
            }

           Optional.ofNullable(discussGroupService.getById(discussRecordBO.getGroupId()))
                   .ifPresent(a -> discussRecordVO.setDiscussGroup(copyProperties(a)));

            return discussRecordVO;
        }

        return null;
    }

    /**
     * 复制属性
     *
     * @param discussGroupList 讨论组列表
     * @return {@link List<DiscussGroupVO>}
     */
    private List<DiscussGroupVO> copyProperties(List<DiscussGroup> discussGroupList) {

        if (CollectionUtil.isNotEmpty(discussGroupList)) {
            return discussGroupList.stream().map(this::copyProperties)
                    .collect(Collectors.toList());
        }

        return null;
    }

    /**
     * 复制属性
     *
     * @param discussGroup 讨论组
     * @return {@link DiscussGroupVO}
     */
    private DiscussGroupVO copyProperties(DiscussGroup discussGroup) {

        if(ObjectUtil.isNotNull(discussGroup)) {
            DiscussGroupVO discussGroupVO = new DiscussGroupVO();
            BeanUtil.copyProperties(discussGroup, discussGroupVO);
            discussGroupVO.setRequest(requestService.findOne(discussGroup.getRequestId()));

            return discussGroupVO;
        }

        return null;
    }

    /**
     * 获取当前的用户
     *
     * @return {@link String}
     */
    private String getCurrentUser() {

        String currentUser = AuthUtils.getCurrentUser();
        return StrUtil.equals(AuthConstant.ADMINISTRATOR, currentUser) ? null : currentUser;

    }













}

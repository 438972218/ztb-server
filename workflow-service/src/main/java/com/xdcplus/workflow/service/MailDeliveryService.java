package com.xdcplus.workflow.service;


import com.xdcplus.workflow.common.pojo.bo.MailDeliveryBO;
import com.xdcplus.workflow.common.pojo.dto.*;
import com.xdcplus.workflow.common.pojo.entity.MailDelivery;
import com.xdcplus.workflow.common.pojo.vo.MailDeliveryVO;
import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

/**
 * 邮件发送点信息 服务类
 *
 * @author Rong.Jia
 * @since 2021-08-10
 */
public interface MailDeliveryService extends BaseService<MailDeliveryBO, MailDeliveryVO, MailDelivery> {

    /**
     * 同步邮件发送信息
     *
     * @param mailDeliveryDTO 邮件发送DTO
     * @return {@link Long} 主键
     */
    Long syncMailDelivery(MailDeliveryDTO mailDeliveryDTO);

    /**
     * 删除邮件发送
     *
     * @param id 主键
     * @return {@link Boolean}
     */
    Boolean deleteMailDelivery(Long id);

    /**
     * 查询邮件通知点信息
     *
     * @param pageDTO 分页参数
     * @return {@link PageVO<MailDeliveryVO>} 邮件通知点信息
     */
    PageVO<MailDeliveryVO> findMailDelivery(MailDeliveryFilterDTO pageDTO);

    /**
     * 查询一个
     *
     * @param id 主键ID
     * @return {@link MailDeliveryVO} 邮件通知点信息
     */
    MailDeliveryVO findOne(Long id);

    /**
     * 查询邮件通知点信息
     *
     * @param point 通知点
     * @return {@link MailDeliveryVO} 邮件通知点信息
     */
    MailDeliveryVO findMailDelivery(String point);





















}

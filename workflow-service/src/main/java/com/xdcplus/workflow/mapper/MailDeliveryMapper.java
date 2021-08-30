package com.xdcplus.workflow.mapper;

import com.xdcplus.workflow.common.pojo.bo.MailDeliveryBO;
import com.xdcplus.workflow.common.pojo.entity.MailDelivery;
import com.xdcplus.workflow.common.pojo.query.MailDeliveryQuery;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 邮件发送点信息 Mapper 接口
 *
 * @author Rong.Jia
 * @since 2021-08-10
 */
public interface MailDeliveryMapper extends IBaseMapper<MailDelivery> {

    /**
     * 查询邮件发送通过发送点
     *
     * @param sendPoint 发送点
     * @return {@link MailDelivery} 邮件发送信息
     */
    MailDelivery findMailDeliveryBySendPoint(@Param("sendPoint") String sendPoint);

    /**
     * 查询邮件发送信息
     *
     * @param query 查询条件
     * @return {@link List<MailDeliveryBO>} 发送信息
     */
    List<MailDeliveryBO> findMailDelivery(MailDeliveryQuery query);

    /**
     * 查询一个
     *
     * @param id 主键ID
     * @return {@link MailDeliveryBO} 邮件通知点信息
     */
    MailDeliveryBO findOne(@Param("id") Long id);

    /**
     * 查询邮件发送通过发送点
     *
     * @param sendPoint 发送点
     * @return {@link MailDelivery} 邮件发送信息
     */
    MailDeliveryBO findMailDeliveryByPoint(@Param("sendPoint") String sendPoint);


}

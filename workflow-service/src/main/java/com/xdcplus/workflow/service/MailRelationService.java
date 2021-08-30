package com.xdcplus.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdcplus.workflow.common.pojo.entity.MailRelation;

/**
 * 邮件发送点-签名-模板关系接口
 *
 * @author Rong.Jia
 * @date 2021/08/10 15:22:44
 */
public interface MailRelationService extends IService<MailRelation> {

    /**
     * 同步关系
     *
     * @param deliveryId 交付id
     * @param templateId 模板id
     * @return {@link Long} 主键ID
     */
    Long syncRelation(Long deliveryId, Long templateId);

    /**
     * 查询关系通过交付id
     *
     * @param deliveryId 交付id
     * @return {@link MailRelation} 关系
     */
    MailRelation findRelationByDeliveryId(Long deliveryId);

    /**
     * 查询关系通过模板id
     *
     * @param templateId 模板id
     * @return {@link MailRelation} 关系
     */
    MailRelation findRelationByTemplateId(Long templateId);

    /**
     * 查询一个
     *
     * @param id 主鍵ID
     * @return {@link MailRelation} 关系
     */
    MailRelation findOne(Long id);

    /**
     * 删除关系
     *
     * @param id 主鍵ID
     * @return {@link Boolean}
     */
    Boolean deleteRelation(Long id);


}

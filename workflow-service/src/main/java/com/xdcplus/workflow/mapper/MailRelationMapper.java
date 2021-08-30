package com.xdcplus.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdcplus.workflow.common.pojo.entity.MailRelation;
import org.apache.ibatis.annotations.Param;

/**
 * 邮件发送点-签名-模板关系
 *
 * @author Rong.Jia
 * @date 2021/08/10 15:19:48
 */
public interface MailRelationMapper extends BaseMapper<MailRelation> {

    /**
     * 查询关系
     *
     * @param deliveryId 交付id
     * @param templateId 模板id
     * @return {@link MailRelation}
     */
    MailRelation findRelation(@Param("deliveryId") Long deliveryId,
                              @Param("templateId") Long templateId);


}

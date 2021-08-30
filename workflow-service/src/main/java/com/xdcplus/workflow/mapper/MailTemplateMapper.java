package com.xdcplus.workflow.mapper;

import com.xdcplus.workflow.common.pojo.entity.MailTemplate;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 邮件模板信息 Mapper 接口
 *
 * @author Rong.Jia
 * @since 2021-08-10
 */
public interface MailTemplateMapper extends IBaseMapper<MailTemplate> {

    /**
     * 查询模板通过的名字
     *
     * @param name 名字
     * @return {@link MailTemplate} 模板
     */
    MailTemplate findTemplateByName(@Param("name") String name);

    /**
     * 查询模板通过类型
     *
     * @param type 类型
     * @return {@link MailTemplate}
     */
    MailTemplate findTemplateByType(@Param("type") Byte type);


}

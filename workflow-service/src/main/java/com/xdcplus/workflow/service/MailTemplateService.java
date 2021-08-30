package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.MailTemplateDTO;
import com.xdcplus.workflow.common.pojo.entity.MailTemplate;
import com.xdcplus.workflow.common.pojo.vo.MailTemplateVO;
import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

/**
 * 邮件模板信息 服务类
 *
 * @author Rong.Jia
 * @since 2021-08-10
 */
public interface MailTemplateService extends BaseService<MailTemplate, MailTemplateVO, MailTemplate> {

    /**
     * 删除模板
     *
     * @param id 主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteTemplate(Long id);

    /**
     * 查询模板
     *
     * @param pageDTO 分页参数
     * @return {@link PageVO<MailTemplateVO>} 模板信息
     */
    PageVO<MailTemplateVO> findTemplates(PageDTO pageDTO);

    /**
     * 验证存在
     *
     * @param name 名字
     * @return {@link Boolean}
     */
    Boolean validationExists(String name);

    /**
     * 查询一个
     *
     * @param templateId 模板id
     * @return {@link MailTemplateVO} 模板信息
     */
    MailTemplateVO findOne(Long templateId);

    /**
     * 查询模板
     *
     * @param type 类型
     * @return {@link MailTemplateVO}
     */
    MailTemplateVO findTemplate(Byte type);

    /**
     * 保存邮件模板
     *
     * @param mailTemplateDTO 邮件模板DTO
     * @return {@link Long}
     */
    Long syncMailTemplate(MailTemplateDTO mailTemplateDTO);





















}

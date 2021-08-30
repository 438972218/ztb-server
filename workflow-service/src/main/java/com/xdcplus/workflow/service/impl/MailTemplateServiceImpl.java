package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.xdcplus.workflow.common.builder.ReplaceAttributeBuilder;
import com.xdcplus.workflow.common.pojo.dto.MailTemplateDTO;
import com.xdcplus.workflow.common.pojo.entity.MailTemplate;
import com.xdcplus.workflow.common.pojo.vo.MailTemplateVO;
import com.xdcplus.workflow.mapper.MailTemplateMapper;
import com.xdcplus.workflow.service.MailTemplateService;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 邮件模板信息 服务实现类
 *
 * @author Rong.Jia
 * @since 2021-08-10
 */
@Slf4j
@Service
public class MailTemplateServiceImpl extends BaseServiceImpl<MailTemplate, MailTemplateVO, MailTemplate, MailTemplateMapper> implements MailTemplateService {

    @Autowired
    private MailTemplateMapper mailTemplateMapper;

    @Override
    public Boolean deleteTemplate(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        MailTemplate mailTemplate = this.getById(id);
        Assert.notNull(mailTemplate, ResponseEnum.THE_MAIL_TEMPLATE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        return this.removeById(id);
    }

    @Override
    public PageVO<MailTemplateVO> findTemplates(PageDTO pageDTO) {

        PageVO<MailTemplateVO> pageVO = new PageVO<>();

        if (pageDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(pageDTO);
        }

        List<MailTemplate> templateList = this.list();
        PageInfo<MailTemplate> pageInfo = new PageInfo<>(templateList);

        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(pageInfo.getList()));

        return pageVO;
    }

    @Override
    public MailTemplateVO objectConversion(MailTemplate mailTemplate) {
        MailTemplateVO mailTemplateVO = super.objectConversion(mailTemplate);
        if (ObjectUtil.isNotNull(mailTemplateVO)) {
            String template = mailTemplate.getTemplate();
            if (StrUtil.isNotBlank(mailTemplate.getDefaultValue())) {
                Map<String, String> defaultValue = JSONObject.parseObject(mailTemplate.getDefaultValue(), new TypeReference<Map<String, String>>() {});
                for (Map.Entry<String, String> entry : defaultValue.entrySet()) {
                    template = StrUtil.replace(template, ReplaceAttributeBuilder.builder().addParameter(entry.getKey()).build(), entry.getValue());
                }
            }

            mailTemplateVO.setContent(template);
        }

        return mailTemplateVO;
    }

    @Override
    public Boolean validationExists(String name) {

        MailTemplate mailTemplate = mailTemplateMapper.findTemplateByName(name);

        return ObjectUtil.isNotEmpty(mailTemplate) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public MailTemplateVO findOne(Long templateId) {

        Assert.notNull(templateId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(templateId));
    }

    @Override
    public MailTemplateVO findTemplate(Byte type) {

        if (Validator.isNotNull(type)) {
            return this.objectConversion(mailTemplateMapper.findTemplateByType(type));
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long syncMailTemplate(MailTemplateDTO mailTemplateDTO) {

        MailTemplate mailTemplate = mailTemplateMapper.findTemplateByName(mailTemplateDTO.getName());
        if (ObjectUtil.isNull(mailTemplate)) {
            mailTemplate = new MailTemplate();
        }

        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);
        BeanUtil.copyProperties(mailTemplateDTO, mailTemplate, copyOptions);

        if (Validator.isNull(mailTemplate.getId())) {
            mailTemplate.setCreatedTime(DateUtil.current());
            this.save(mailTemplate);
        } else {
            mailTemplate.setUpdatedTime(DateUtil.current());
            this.updateById(mailTemplate);
        }

        return mailTemplate.getId();
    }


}

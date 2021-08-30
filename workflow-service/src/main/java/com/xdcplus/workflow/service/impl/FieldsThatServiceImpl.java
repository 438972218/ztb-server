package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import com.xdcplus.workflow.common.pojo.dto.FieldsThatDTO;
import com.xdcplus.workflow.common.pojo.entity.FieldsThat;
import com.xdcplus.workflow.common.pojo.vo.FieldsThatVO;
import com.xdcplus.workflow.mapper.FieldsThatMapper;
import com.xdcplus.workflow.service.FieldsThatService;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 对接字段说明 服务实现类
 *
 * @author Rong.Jia
 * @date 2021-06-24
 */
@Slf4j
@Service
public class FieldsThatServiceImpl extends BaseServiceImpl<FieldsThat, FieldsThatVO, FieldsThat, FieldsThatMapper> implements FieldsThatService {

    @Autowired
    private FieldsThatMapper fieldsThatMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveFieldsThat(FieldsThatDTO fieldsThatDTO) {

        FieldsThat fieldsThat = new FieldsThat();
        BeanUtil.copyProperties(fieldsThatDTO, fieldsThat);
        fieldsThat.setCreatedTime(DateUtil.current());

        return this.save(fieldsThat);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateFieldsThat(FieldsThatDTO fieldsThatDTO) {

        FieldsThat fieldsThat = this.getById(fieldsThatDTO.getId());
        Assert.notNull(fieldsThat,
                ResponseEnum.FIELD_CORRESPONDENCE_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);

        BeanUtil.copyProperties(fieldsThatDTO, fieldsThat, copyOptions);
        fieldsThat.setUpdatedTime(DateUtil.current());

        return this.updateById(fieldsThat);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteFieldsThat(Long id) {

        FieldsThat fieldsThat = this.getById(id);
        Assert.notNull(fieldsThat,
                ResponseEnum.FIELD_CORRESPONDENCE_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        return this.removeById(id);
    }

    @Override
    public List<FieldsThatVO> findFieldsThats(Byte type) {

        return this.objectConversion(fieldsThatMapper.findFieldsThatByType(type));
    }

    @Override
    public Boolean syncFieldsThat(FieldsThatDTO fieldsThatDTO) {

        Boolean flag = Boolean.FALSE;

        if (Validator.isNull(fieldsThatDTO.getId())) {
            flag = this.saveFieldsThat(fieldsThatDTO);
        } else {
            flag = this.updateFieldsThat(fieldsThatDTO);
        }

        return flag;
    }
}

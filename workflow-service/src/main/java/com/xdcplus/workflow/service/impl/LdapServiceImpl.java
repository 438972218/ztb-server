package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.workflow.common.pojo.dto.LdapDTO;
import com.xdcplus.workflow.common.pojo.entity.Ldap;
import com.xdcplus.workflow.common.pojo.vo.LdapVO;
import com.xdcplus.workflow.mapper.LdapMapper;
import com.xdcplus.workflow.service.LdapService;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ldap信息 服务实现类
 *
 * @author Rong.Jia
 * @date 2021-06-24
 */
@Slf4j
@Service
public class LdapServiceImpl extends BaseServiceImpl<Ldap, LdapVO, Ldap, LdapMapper> implements LdapService {

    @Autowired
    private LdapMapper ldapMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveLdap(LdapDTO ldapDTO) {

        Ldap ldap = ldapMapper.findLdapByAddress(ldapDTO.getAddress());
        Assert.isNull(ldap, ResponseEnum.LDAP_INFORMATION_ALREADY_EXISTS.getMessage());

        ldap = new Ldap();
        BeanUtil.copyProperties(ldapDTO, ldap);
        ldap.setCreatedTime(DateUtil.current());

        return this.save(ldap);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateLdap(LdapDTO ldapDTO) {

        Ldap ldap = this.getById(ldapDTO.getId());
        Assert.notNull(ldap, ResponseEnum.LDAP_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        if (!StrUtil.equals(ldap.getAddress(), ldapDTO.getAddress())) {
            Assert.isNull(ldapMapper.findLdapByAddress(ldapDTO.getAddress()),
                    ResponseEnum.LDAP_INFORMATION_ALREADY_EXISTS.getMessage());
        }

        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);
        BeanUtil.copyProperties(ldapDTO, ldap, copyOptions);
        ldap.setUpdatedTime(DateUtil.current());

        return this.updateById(ldap);
    }

    @Override
    public Boolean syncLdap(LdapDTO ldapDTO) {

        Boolean flag = Boolean.FALSE;

        if (Validator.isNull(ldapDTO.getId())) {
            flag = this.saveLdap(ldapDTO);
        } else {
            flag = this.updateLdap(ldapDTO);
        }

        return flag;
    }

    @Override
    public LdapVO findLdap() {

        List<Ldap> ldapList = this.list();
        if (CollectionUtil.isNotEmpty(ldapList)) {
            return this.objectConversion(ldapList.get(NumberConstant.ZERO));
        }

        return null;
    }
}

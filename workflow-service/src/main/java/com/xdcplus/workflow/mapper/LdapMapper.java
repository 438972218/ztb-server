package com.xdcplus.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdcplus.workflow.common.pojo.entity.Ldap;
import org.apache.ibatis.annotations.Param;

/**
 * ldap信息 Mapper 接口
 *
 * @author Rong.Jia
 * @date 2021-06-24
 */
public interface LdapMapper extends BaseMapper<Ldap> {

    /**
     * 查询ldap通过地址
     *
     * @param address 地址
     * @return {@link Ldap} Ldap 信息
     */
    Ldap findLdapByAddress(@Param("address") String address);


}

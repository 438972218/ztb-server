package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.LdapDTO;
import com.xdcplus.workflow.common.pojo.entity.Ldap;
import com.xdcplus.workflow.common.pojo.vo.LdapVO;
import com.xdcplus.ztb.common.mp.service.BaseService;

/**
 * ldap信息 服务类
 *
 * @author Rong.Jia
 * @date 2021-06-24
 */
public interface LdapService extends BaseService<Ldap, LdapVO, Ldap> {

    /**
     * 添加ldap
     *
     * @param ldapDTO ldapDTO
     * @return {@link Boolean} 是否成功
     */
    Boolean saveLdap(LdapDTO ldapDTO);

    /**
     * 修改ldap
     *
     * @param ldapDTO ldapDTO
     * @return {@link Boolean} 是否成功
     */
    Boolean updateLdap(LdapDTO ldapDTO);

    /**
     * ldap同步
     *
     * @param ldapDTO ldapDTO
     * @return {@link Boolean} 是否成功
     */
    Boolean syncLdap(LdapDTO ldapDTO);

    /**
     * 查询ldap
     *
     * @return {@link LdapVO}
     */
    LdapVO findLdap();


}

package com.xdcplus.permission.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.permission.common.pojo.vo.sysuser.UserPermVO;
import com.xdcplus.permission.service.SysUserService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysUserInfoDTO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 自定义用户认证和授权
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUserInfoDTO userInfoDTO = SysUserInfoDTO.builder().userName(username).build();
        UserPermVO userPermVO = sysUserService.getUserByUserIdOrUserName(userInfoDTO.getId(), userInfoDTO.getUserName());

        if (ObjectUtil.isNull(userPermVO)) {
            throw new UsernameNotFoundException(ResponseEnum.THE_ACCOUNT_DOES_NOT_EXIST_PLEASE_CHANGE_THE_ACCOUNT_TO_LOGIN.getMessage());
        }
        if (CollectionUtil.isEmpty(userPermVO.getRoleIds())) {
            throw new ZtbWebException(ResponseEnum.THE_ROLE_IDS_DOES_NOT_EXIST_PLEASE_CHANGE_THE_ACCOUNT_TO_LOGIN);
        }

        Collection<SimpleGrantedAuthority> authorities = CollectionUtil.newArrayList();
        userPermVO.getRoleIds().forEach(m -> authorities.add(new SimpleGrantedAuthority(Convert.toStr(m))));

        boolean enabled = !ObjectUtil.equal(NumberConstant.ONE, userPermVO.getStatus());

        //用户权限列表

        SecurityUser securityUser = new SecurityUser(
                userPermVO.getId(), userPermVO.getUserName(), userPermVO.getPassword(),
                enabled, true, true, true,
                authorities);

        if (!securityUser.isEnabled()) {
            throw new DisabledException("该账户已被禁用");
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定");
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期");
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("该账户的登录凭证已过期");
        }

        return securityUser;

    }

}

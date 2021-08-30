package com.xdcplus.ztb.common.remote.domain.perm.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户信息DTO
 *
 * @author Rong.Jia
 * @date 2021/08/03 17:22:53
 */
@Data
@Builder
public class SysUserInfoDTO implements Serializable {

    private static final long serialVersionUID = -4392102746332814573L;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户id
     */
    private Long id;









}

package com.xdcplus.permission.common.pojo.dto.sysuser;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("参数对照对象")
public class GetSysUserByUserIdOrUserNameDto implements Serializable {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户id
     */
    private Long id;
}

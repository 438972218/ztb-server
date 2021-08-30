package com.xdcplus.vendorperm.common.pojo.dto.sysuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("获取用户通过用户id或用户的名字，请求参数")
public class GetUserByUserIdOrUserNameDto implements Serializable {
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;




}

package com.xdcplus.vendorperm.common.pojo.dto.sysuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("更新密码Dto")
public class UpdatePasswordByIdDto implements Serializable {
    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    private Long id;
    @ApiModelProperty("旧密码")
    @NotNull(message = "旧密码不能为空")
    private String oldPassword;
    @ApiModelProperty("新密码")
    @NotNull(message = "新密码不能为空")
    private String newPassword;
}

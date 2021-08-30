package com.xdcplus.vendorperm.common.pojo.dto.sysuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
@ApiModel("注册用户请求参数")
public class RegisterUserDto implements Serializable {
    private static final long serialVersionUID = -22340563453816956L;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    @NotEmpty(message = "姓名不能为空")
    private String name;
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    @NotEmpty(message = "账号不能为空")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @NotEmpty(message = "邮箱不能为空")
    private String mail;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;

}

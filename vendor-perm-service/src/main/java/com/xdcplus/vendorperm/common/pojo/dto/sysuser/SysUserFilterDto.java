package com.xdcplus.vendorperm.common.pojo.dto.sysuser;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户信息表(SysUser)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户信息分页请求过滤查询 参数对照对象")
public class SysUserFilterDto extends PageDTO implements Serializable {
    private static final long serialVersionUID = -22340563453816956L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String mail;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;
    /**
     * 帐号状态（0正常 1冻结）
     */
    @ApiModelProperty("帐号状态（0正常 1冻结）")
    private String status;
    /**
     * 最后登陆IP
     */
    @ApiModelProperty("最后登陆IP")
    private String loginIp;
    /**
     * 最后登陆时间
     */
    @ApiModelProperty("最后登陆时间")
    private Long loginDate;
    /**
     * 账户是否被锁定(0:启用,1:锁定)
     */
    @ApiModelProperty("账户是否被锁定(0:启用,1:锁定)")
    private String lockStatus;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createdUser;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long createdTime;
    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updatedUser;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Long updatedTime;


}

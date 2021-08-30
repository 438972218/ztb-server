package com.xdcplus.vendorperm.common.pojo.vo.sysuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetSysUserByUserIdOrUserNameVo {
    /**
     *员工id
     */
    @ApiModelProperty("员工id")
    private Long employeeId;
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
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String realName;
    /**
     * 员工编号
     */
    @ApiModelProperty("员工编号")
    private String employeeNo;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Integer sex;
//    /**
//     * 所属岗位
//     */
//    @ApiModelProperty(" 所属岗位")
//    private String positionName;
//    /**
//     * 所属部门
//     */
//    @ApiModelProperty("所属部门")
//    private String departmentName;

    /**
     * 状态(在线、离职)
     */
    @ApiModelProperty("状态(在线、离职)")
    private Integer status;
    /**
     * 办公地点
     */
    @ApiModelProperty("办公地点")
    private String officeAddress;
    /**
     * 专长
     */
    @ApiModelProperty("专长")
    private String speciality;
    /**
     * 爱好
     */
    @ApiModelProperty("爱好")
    private String hobby;

    /**
     * 办公电话
     */
    @ApiModelProperty("办公电话")
    private String officePhone;
    /**
     * 办公室
     */
    @ApiModelProperty("办公室")
    private String office;
    @ApiModelProperty("用户id")
    private Long userId;
}

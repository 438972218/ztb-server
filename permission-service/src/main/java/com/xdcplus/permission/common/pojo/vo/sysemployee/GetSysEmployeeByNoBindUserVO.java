package com.xdcplus.permission.common.pojo.vo.sysemployee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("员工Vo")
public class GetSysEmployeeByNoBindUserVO implements Serializable {
    private static final long serialVersionUID = -32805542345053702L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 直接上级
     */
    @ApiModelProperty("直接上级")
    private Long managerId;
    @ApiModelProperty("直接上级")
    private String managerName;
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
    /**
     * 所属岗位
     */
    @ApiModelProperty(" 所属岗位")
    private Long position;
    /**
     * 所属部门
     */
    @ApiModelProperty("所属部门")
    private Long departmentId;

    @ApiModelProperty("所属部门名称")
    private String departmentName;
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
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;
    /**
     * 办公电话
     */
    @ApiModelProperty("办公电话")
    private String officePhone;
    /**
     * 邮箱
     */
    @ApiModelProperty(" 邮箱")
    private String mail;
    /**
     * 办公室
     */
    @ApiModelProperty("办公室")
    private String office;
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
    @ApiModelProperty("用户名")
    private String userName;
}

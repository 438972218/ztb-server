package com.xdcplus.permission.common.pojo.dto.sysemployee;

import com.xdcplus.permission.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 员工信息表(SysEmployee)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("员工信息请求 参数对照对象")
public class SysEmployeeDto implements Serializable {
    private static final long serialVersionUID = -32805542345053702L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class,UpdateGroupValidator.class}, message = "id不能为空")
    private Long id;
    /**
     * 直接上级
     */
    @ApiModelProperty("直接上级id")
    private Long managerId;

    @ApiModelProperty("直接上级员工code")
    private String managerCode;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "姓名不能为空")
    @NotBlank(message = "姓名不能为空！")
    private String realName;
    /**
     * 员工编号
     */
    @ApiModelProperty("员工编号")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "员工编号不能为空")
    @NotBlank(message = "员工编号不能为空！")
    private String employeeNo;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Integer sex;
    /**
     * 所属岗位
     */
    @ApiModelProperty("所属岗位")
    private Long position;
    /**
     * 所属部门
     */
    @ApiModelProperty("所属部门")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "所属部门不能为空")
    private Long departmentId;

    @ApiModelProperty("所属部门code")
    private String departmentCode;
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
    @ApiModelProperty("邮箱")
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
    private String dept;
    @ApiModelProperty("岗位名称")
    private String positionName;



}

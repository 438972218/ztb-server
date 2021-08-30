package com.xdcplus.permission.common.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 员工信息表(SysEmployee)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysEmployee implements Serializable {
    private static final long serialVersionUID = -32805542345053702L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 直接上级
     */
    private Long managerId;
    /**
     * 直接上级员工code
     */
    private String managerCode;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 员工编号
     */
    private String employeeNo;

    /**
     * 性别
     */
    private Integer sex;
    /**
     * 所属岗位
     */
    private Long position;
    /**
     * 所属部门
     */
    private Long departmentId;

    private String departmentCode;
    /**
     * 状态(在线、离职)
     */
    private Integer status;
    /**
     * 办公地点
     */
    private String officeAddress;
    /**
     * 专长
     */
    private String speciality;
    /**
     * 爱好
     */
    private String hobby;
    /**
     * 电话
     */
    private String phone;
    /**
     * 办公电话
     */
    private String officePhone;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 办公室
     */
    private String office;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 更新人
     */
    private String updatedUser;
    /**
     * 更新时间
     */
    private Long updatedTime;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 是否已经逻辑删除（0：未删除 1：已删除）
     */
    private Integer deleted;
    /**
     * 岗位名称
     */
    private String positionName;


}

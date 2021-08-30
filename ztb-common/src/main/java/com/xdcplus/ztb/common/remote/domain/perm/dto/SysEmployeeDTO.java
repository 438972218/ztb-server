package com.xdcplus.ztb.common.remote.domain.perm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 员工DTO
 *
 * @author Rong.Jia
 * @date 2021/08/04 13:35:30
 */
@Data
public class SysEmployeeDTO implements Serializable {

    private static final long serialVersionUID = 5758574100014121110L;

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

    /**
     * 所属部门code
     */
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


    private String dept;




















}

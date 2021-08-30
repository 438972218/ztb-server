package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对接字段说明
 *
 * @author Rong.Jia
 * @date  2021-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_fields_that")
public class FieldsThat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * ldap 字段名
     */
    private String ldapName;

    /**
     * 类型（1：组织，2：部门，3：人员）
     */
    private Byte type;

    /**
     * 添加时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 描述
     */
    private String description;


}

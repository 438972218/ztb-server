package com.xdcplus.workflow.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 字段 对应VO
 *
 * @author Rong.Jia
 * @date 2021/06/24
 */
@ApiModel("属性对应信息 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class FieldsThatVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 6173493719083713514L;

    /**
     * 字段名
     */
    @ApiModelProperty("字段名")
    private String fieldName;

    /**
     * ldap 字段名
     */
    @ApiModelProperty("ldap 字段名")
    private String ldapName;

    /**
     * 类型（1：组织，2：部门，3：人员）
     */
    @ApiModelProperty("类型")
    private Byte type;


}

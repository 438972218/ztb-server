package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 字段对应关系DTO
 *
 * @author Rong.Jia
 * @date 2021/06/25
 */
@ApiModel("字段对应关系 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class FieldsThatDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -3921192751418793113L;

    /**
     * 字段名
     */
    @NotBlank(message = "字段名 不能为空")
    @ApiModelProperty(value = "字段名", required = true)
    private String fieldName;

    /**
     * ldap 字段名
     */
    @NotBlank(message = "ldap 字段名 不能为空")
    @ApiModelProperty(value = "ldap 字段名", required = true)
    private String ldapName;

    /**
     * 类型（1：组织，2：部门，3：人员）
     */
    @NotBlank(message = "类型 不能为空")
    @ApiModelProperty(value = "类型 （详见数据字典）", required = true)
    private Byte type;


}


package com.xdcplus.biz.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 项目(ProjectSheet)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:30:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class ProjectSheetDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 486434530253081029L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("代码")
    private String code;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("项目激活时间")
    private Long activationTime;

    @ApiModelProperty("项目启动时间")
    private Long startTime;

    @ApiModelProperty("项目完成时间")
    private Long finishTime;

    @ApiModelProperty("品类")
    private String items;

    @ApiModelProperty("说明")
    private String explanation;

    @ApiModelProperty("创建人")
    private String createdUser;

    @ApiModelProperty("创建时间")
    private Long createdTime;

    @ApiModelProperty("修改人")
    private String updatedUser;

    @ApiModelProperty("修改时间")
    private Long updatedTime;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("版本号")
    private Integer version;

    @ApiModelProperty("是否已经逻辑删除（0：未删除 1：已删除）")
    private Integer deleted;



    @ApiModelProperty("单号规则ID")
    private Long ruleId;

    @ApiModelProperty("流程ID")
    private Long processId;

    @ApiModelProperty("流程配置版本号")
    private String configVersion;

    @ApiModelProperty("userId")
    private Long userId;

    @ApiModelProperty("用戶名称")
    private String name;

    @ApiModelProperty("表单类型ID")
    private Long typeId;

}

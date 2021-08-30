package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 项目(ProjectSheet)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:31:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class ProjectSheetVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -23229855781608999L;

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


    private List<ProjectAttachmentVO> projectAttachmentVOS;

    private List<ProjectMemberVO> projectMemberVOS;

}

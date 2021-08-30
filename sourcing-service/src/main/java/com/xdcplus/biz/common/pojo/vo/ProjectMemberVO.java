package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 项目成员信息(ProjectMember)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-24 09:40:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class ProjectMemberVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -71034277423334631L;

    @ApiModelProperty("招标单id")
    private Long projectId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("邮件")
    private String email;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("岗位")
    private String post;

}

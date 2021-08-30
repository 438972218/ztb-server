package com.xdcplus.vendor.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * bid招标单(BidSheet)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-08-26 16:40:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidSheetDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -17412023274921681L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("项目id")
    private Long projectSheetId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("招标单号")
    private String bidSheetNum;

    @ApiModelProperty("招标单类型")
    private String sheetType;

    @ApiModelProperty("投标类型")
    private String tenderType;

    @ApiModelProperty("招标方式")
    private String tenderMode;

    @ApiModelProperty("投标截止时间")
    private Long bidEndTime;

    @ApiModelProperty("澄清截止时间")
    private Long clarifyEndTime;

    @ApiModelProperty("开标时间")
    private Long openBidTime;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("招标事项")
    private String tenderMatters;

    @ApiModelProperty("说明")
    private String explanation;

    @ApiModelProperty("标识（澄清报价/最终报价）")
    private String mark;

    @ApiModelProperty("审批流程")
    private Integer approvalProcess;

    @ApiModelProperty("资质标")
    private Integer qualificationTender;

    @ApiModelProperty("技术标")
    private Integer technicalTender;

    @ApiModelProperty("供应商回复排名规则")
    private String rankingRules;

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

}

package com.xdcplus.vendor.common.pojo.vo;

import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * bid招标单(BidSheet)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-26 16:40:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidSheetVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 964694366448773546L;

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

    @ApiModelProperty("单号")
    private String oddNumber;

    @ApiModelProperty("request标题")
    private String requestTitle;

    @ApiModelProperty("request状态")
    private String requestStatusName;

    @ApiModelProperty("供应商状态")
    private String vendorStatus;

    @ApiModelProperty("request")
    private RequestVO requestVO;
    @ApiModelProperty("技术标")
    private Integer technicalTender;

    @ApiModelProperty("所有节点状态")
    private List<String> statusNames;
    @ApiModelProperty("供应商回复排名规则")
    private String rankingRules;

}

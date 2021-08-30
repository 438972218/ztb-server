package com.xdcplus.biz.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 竞价单(PaidSheet)表查询入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:41:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidSheetFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 697768675511707162L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("requestId")
    private Long requestId;

    @ApiModelProperty("项目id")
    private Long projectSheetId;

    @ApiModelProperty("竞价状态标识")
    private Integer paidStatusMark;

    @ApiModelProperty("竞价状态")
    private String paidStatus;

    @ApiModelProperty("竞价单号")
    private String paidNum;

    @ApiModelProperty("竞价单项目名称")
    private String title;

    @ApiModelProperty("竞价开始时间")
    private Long offerStartTime;

    @ApiModelProperty("竞价截止时间")
    private Long offerEndTime;

    @ApiModelProperty("第一批次截止时间")
    private Long firstEndTime;

    @ApiModelProperty("位移时间")
    private Integer displacementTime;

    @ApiModelProperty("暂停时间")
    private Long pauseTime;

    @ApiModelProperty("预算")
    private Double budget;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("是否测试（1：是测试，0：不是测试）")
    private Integer whetherTest;

    @ApiModelProperty("品类")
    private String items;

    @ApiModelProperty("竞价方向（正向，反向）")
    private String paidDirection;

    @ApiModelProperty("出价结构（总额，多项产品）")
    private String bidStructure;

    @ApiModelProperty("多批次类型（平行，交替）")
    private String manyBatchesType;

    @ApiModelProperty("起始价格")
    private Double startPrice;

    @ApiModelProperty("最小出价规则")
    private String minimumBidRule;

    @ApiModelProperty("最小出价幅度")
    private String minimumBidRange;

    @ApiModelProperty("触发价格")
    private Double triggerPrice;

    @ApiModelProperty("延时间隔")
    private Integer delayInterval;

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


    @ApiModelProperty("供应商代码")
    private String vendorCode;

    @ApiModelProperty("表单状态")
    private String requestStatusName;

    @ApiModelProperty("keyword")
    private String keyword;

    @ApiModelProperty("requestIds")
    private List<Long> requestIds;

    @ApiModelProperty("statusId")
    private Long statusId;

    @ApiModelProperty("竞价状态标识s")
    private List<Integer> paidStatusMarks;

}

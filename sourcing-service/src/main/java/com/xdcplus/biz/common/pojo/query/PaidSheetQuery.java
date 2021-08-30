package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 竞价单(PaidSheet)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:41:16
 */
@Data
@SuppressWarnings("serial")
public class PaidSheetQuery implements Serializable {
    private static final long serialVersionUID = 964133538730684669L;

    private Long id;

    private Long requestId;

    private Long projectSheetId;

    private Integer paidStatusMark;

    private String paidStatus;

    private String paidNum;

    private String title;

    private Long offerStartTime;

    private Long offerEndTime;

    private Long firstEndTime;

    private Integer displacementTime;

    private Long pauseTime;

    private Double budget;

    private String currency;

    private Integer whetherTest;

    private String items;

    private String paidDirection;

    private String bidStructure;

    private String manyBatchesType;

    private Double startPrice;

    private String minimumBidRule;

    private String minimumBidRange;

    private Double triggerPrice;

    private Integer delayInterval;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

    private List<Integer> paidStatusMarks;

}

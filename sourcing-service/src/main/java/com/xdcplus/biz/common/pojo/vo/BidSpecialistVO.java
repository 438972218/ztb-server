package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 专家(BidSpecialist)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-24 16:22:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidSpecialistVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -44765475483691329L;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @ApiModelProperty("标类型")
    private String bidType;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("专家姓名")
    private String specialistName;

}

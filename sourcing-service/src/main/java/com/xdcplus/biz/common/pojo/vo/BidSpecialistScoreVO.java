package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 招标专家评分(BidSpecialistScore)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-26 17:17:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidSpecialistScoreVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -62752044599926941L;

    @ApiModelProperty("供应商id")
    private Long bidVendorId;

    @ApiModelProperty("供应商名称")
    private String bidVendorName;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("标类型")
    private String bidType;

    @ApiModelProperty("是否查看")
    private Integer whetherView;

    @ApiModelProperty("评分")
    private Double score;

    @ApiModelProperty("评论")
    private String comment;

    @ApiModelProperty("状态（最新/历史）")
    private String status;

    @ApiModelProperty("用户")
    private SysUserInfoVO sysUserInfoVO;

}

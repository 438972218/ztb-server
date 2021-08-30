package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 招标专家评分(BidSpecialistScore)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-26 17:17:59
 */
@Data
@SuppressWarnings("serial")
public class BidSpecialistScoreQuery implements Serializable {
    private static final long serialVersionUID = 854844179921264520L;

    private Long id;

    private Long bidVendorId;

    private String bidVendorName;

    private Long userId;

    private String bidType;

    private Integer whetherView;

    private Double score;

    private String comment;

    private String status;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;


    private List<Long> bidVendorIds;

}

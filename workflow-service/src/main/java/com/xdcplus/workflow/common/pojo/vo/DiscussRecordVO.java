package com.xdcplus.workflow.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 讨论记录VO
 *
 * @author Rong.Jia
 * @date 2021/08/18 17:13:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("讨论记录信息对照对象")
public class DiscussRecordVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 3319887944101018045L;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 发信人
     */
    @ApiModelProperty("发信人")
    private String fromUser;

    /**
     * 收信人
     */
    @ApiModelProperty("收信人")
    private List<String> toUsers;

    /**
     * 讨论组信息
     */
    @ApiModelProperty("讨论组信息")
    private DiscussGroupVO discussGroup;









}

package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 通知历史过滤器DTO
 *
 * @author Rong.Jia
 * @date 2021/08/11 13:23:04
 */
@ApiModel("通知历史过滤查询对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class NotificationHistoryFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 288376212467664329L;

    /**
     *  通知类型
     */
    @ApiModelProperty("通知类型")
    private Byte type;

    /**
     *  消息ID
     */
    @ApiModelProperty("消息ID")
    private String messageId;

    /**
     *  表单ID
     */
    @ApiModelProperty("表单ID")
    private Long requestId;

    /**
     *  流转ID
     */
    @ApiModelProperty("流转ID")
    private Long flowId;

    /**
     *  通知发送点
     */
    @ApiModelProperty("通知发送点")
    private String point;










}

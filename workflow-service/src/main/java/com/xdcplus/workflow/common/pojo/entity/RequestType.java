package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 表单类型信息
 * @author Rong.Jia
 * @date 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_request_type")
public class RequestType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 表单类型
     */
    private String requestType;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 描述
     */
    private String description;


}

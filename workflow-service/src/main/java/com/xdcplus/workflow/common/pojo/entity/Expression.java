package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 表达式标识表
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_expression")
public class Expression implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 标识含义
     */
    private String name;

    /**
     * 标识类型
     */
    private Integer type;

    /**
     * 标识
     */
    private String symbol;

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

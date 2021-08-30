package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 表单-表单类型关系
 *
 * @author Rong.Jia
 * @date 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_request_type_relation")
public class RequestTypeRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  主键ID
     */
    private Long id;

    /**
     * 表单类型ID
     */
    private Long typeId;

    /**
     * 表单ID
     */
    private Long requestId;

    public RequestTypeRelation() {
    }

    public RequestTypeRelation(Long id, Long typeId, Long requestId) {
        this.id = id;
        this.typeId = typeId;
        this.requestId = requestId;
    }

    public RequestTypeRelation(Long typeId, Long requestId) {
        this.typeId = typeId;
        this.requestId = requestId;
    }
}

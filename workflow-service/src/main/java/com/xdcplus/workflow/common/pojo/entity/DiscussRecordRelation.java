package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 讨论记录关联信息
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_discuss_record_relation")
public class DiscussRecordRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 收信人
     */
    private String toUser;

    /**
     * 记录Id
     */
    private Long recordId;


}

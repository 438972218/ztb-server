package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 讨论记录
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_discuss_record")
public class DiscussRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 讨论组ID
     */
    private Long groupId;

    /**
     * 内容
     */
    private String content;

    /**
     * 发信人
     */
    private String fromUser;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 添加时间
     */
    private Long createdTime;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 描述
     */
    private String description;


}

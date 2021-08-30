package com.xdcplus.workflow.common.pojo.bo;

import com.xdcplus.workflow.common.pojo.entity.DiscussRecord;
import com.xdcplus.workflow.common.pojo.entity.DiscussRecordRelation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 讨论记录BO
 *
 * @author Rong.Jia
 * @date 2021/08/18 17:36:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DiscussRecordBO extends DiscussRecord implements Serializable {

    private static final long serialVersionUID = 4583219472001828070L;

    /**
     * 关系
     */
    private List<DiscussRecordRelation> relations;


}

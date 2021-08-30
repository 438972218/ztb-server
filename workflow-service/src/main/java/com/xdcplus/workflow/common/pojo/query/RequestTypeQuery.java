package com.xdcplus.workflow.common.pojo.query;

import com.xdcplus.workflow.common.pojo.dto.RequestTypeFilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 表单类型查询对象
 *
 * @author Rong.Jia
 * @date 2021/08/05 14:54:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestTypeQuery extends RequestTypeFilterDTO implements Serializable {

    private static final long serialVersionUID = 8928120112006131615L;

}

package com.xdcplus.workflow.factory.flow;

import com.xdcplus.workflow.common.pojo.dto.ProcessTransforDTO;
import com.xdcplus.workflow.common.pojo.vo.RequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流转过程 信息
 * @author Rong.Jia
 * @date 2021/06/07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProcessTransforParam extends ProcessTransforDTO implements Serializable {

    private static final long serialVersionUID = -1296237723402831177L;

    /**
     * 表单
     */
    private RequestVO request;





}

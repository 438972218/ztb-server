package com.xdcplus.ztb.common.remote.domain.sourcing;

import lombok.Data;

import java.io.Serializable;

/**
 * 竞价物品信息
 *
 * @author Rong.Jia
 * @date 2021/08/23
 */
@Data
public class PaidMaterialInfoVO implements Serializable {

    private static final long serialVersionUID = 2932598820356816585L;

    /**
     * 竞价单id
     */
    private Long paidSheetId;

    /**
     * 物品标识
     */
    private Integer materialMark;

    /**
     * 物品名称
     */
    private String materialName;

    /**
     * 竞价开始时间
     */
    private Long beginTime;

    /**
     * 竞价截止时间
     */
    private Long endTime;

}

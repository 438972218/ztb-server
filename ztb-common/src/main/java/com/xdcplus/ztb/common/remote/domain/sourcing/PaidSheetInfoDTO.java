package com.xdcplus.ztb.common.remote.domain.sourcing;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 竞价单DTO
 *
 * @author Rong.Jia
 * @date 2021/08/23
 */
@Builder
public class PaidSheetInfoDTO implements Serializable {

    /**
     *  主键ID
     */
    private Long id;

    /**
     * 竞价状态
     */
    private String paidStatus;

    /**
     *  竞价状态标识
     */
    private Integer paidStatusMark;



}

package com.xdcplus.vendor.common.pojo.query;

import com.xdcplus.vendor.common.pojo.dto.OfferFilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 出价记录查询对象
 *
 * @author Rong.Jia
 * @date 2021/08/18 09:03:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OfferQuery extends OfferFilterDTO implements Serializable {
    private static final long serialVersionUID = 4431025823282770661L;
}

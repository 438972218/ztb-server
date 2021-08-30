package com.xdcplus.biz.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.PaidSheet;
import com.xdcplus.biz.common.pojo.query.PaidSheetQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞价单(PaidSheet)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:41:16
 */
public interface PaidSheetMapper extends IBaseMapper<PaidSheet> {

    /**
     * 查询竞价单(PaidSheet)
     *
     * @param paidSheetQuery 竞价单(PaidSheet)查询
     * @return {@link List<PaidSheet>}
     */
    List<PaidSheet> queryPaidSheet(PaidSheetQuery paidSheetQuery);

    /**
     * 查询竞价单(PaidSheet)(缓存)
     *
     * @return {@link List<PaidSheet>}
     */
    List<PaidSheet> queryPaidSheetCache();

}

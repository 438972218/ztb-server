package com.xdcplus.vendor.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendor.common.pojo.entity.BidAttachment;
import com.xdcplus.vendor.common.pojo.query.BidAttachmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标附件(BidAttachment)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-26 10:00:02
 */
public interface BidAttachmentMapper extends IBaseMapper<BidAttachment> {

    /**
     * 查询招标附件(BidAttachment)
     *
     * @param bidAttachmentQuery 招标附件(BidAttachment)查询
     * @return {@link List<BidAttachment>}
     */
    List<BidAttachment> queryBidAttachment(BidAttachmentQuery bidAttachmentQuery);

}

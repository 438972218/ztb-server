package com.xdcplus.vendor.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendor.common.pojo.entity.VendorAttachment;
import com.xdcplus.vendor.common.pojo.query.VendorAttachmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标附件(VendorAttachment)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:54
 */
public interface VendorAttachmentMapper extends IBaseMapper<VendorAttachment> {

    /**
     * 查询招标附件(VendorAttachment)
     *
     * @param vendorAttachmentQuery 招标附件(VendorAttachment)查询
     * @return {@link List<VendorAttachment>}
     */
    List<VendorAttachment> queryVendorAttachment(VendorAttachmentQuery vendorAttachmentQuery);

}

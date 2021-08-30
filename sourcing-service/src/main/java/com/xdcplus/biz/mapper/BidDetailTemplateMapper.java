package com.xdcplus.biz.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.BidDetailTemplate;
import com.xdcplus.biz.common.pojo.query.BidDetailTemplateQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内容模板(BidDetailTemplate)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-30 09:07:53
 */
public interface BidDetailTemplateMapper extends IBaseMapper<BidDetailTemplate> {

    /**
     * 查询内容模板(BidDetailTemplate)
     *
     * @param bidDetailTemplateQuery 内容模板(BidDetailTemplate)查询
     * @return {@link List<BidDetailTemplate>}
     */
    List<BidDetailTemplate> queryBidDetailTemplate(BidDetailTemplateQuery bidDetailTemplateQuery);

}

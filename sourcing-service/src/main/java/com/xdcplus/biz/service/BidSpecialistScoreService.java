package com.xdcplus.biz.service;

import com.xdcplus.biz.common.pojo.dto.BidSpecialistScoreDTO;
import com.xdcplus.biz.common.pojo.dto.BidSpecialistScoreFilterDTO;
import com.xdcplus.biz.generator.BidSpecialistScoreBaseService;
import com.xdcplus.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.biz.common.pojo.vo.BidSpecialistScoreVO;

import java.util.List;


/**
 * 招标专家评分(BidSpecialistScore)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-24 16:30:57
 */
public interface BidSpecialistScoreService extends BidSpecialistScoreBaseService<BidSpecialistScore, BidSpecialistScoreVO, BidSpecialistScore> {

    /**
     * 查询报价专家评分voby专家
     * 查询已回复的供应商对应的专家评分
     *
     * @param bidSpecialistScoreFilterDTO 专家评分过滤器dto
     * @return {@link List<BidSpecialistScoreVO>}
     */
    List<BidSpecialistScoreVO> queryBidSpecialistScoreVOBySpecialist(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO);

    /**
     * 查询专业分数voby报价供应商
     *
     * @param bidSpecialistScoreFilterDTO 专家评分过滤器dto
     * @return {@link List<BidSpecialistScoreVO>}
     */
    List<BidSpecialistScoreVO> queryBidSpecialistScoreVOByBidVendor(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO);

    /**
     * 更新评分中是否查看字段并更新供应商中是否查看字段
     *
     * @param bidSpecialistScoreDTO 收购专家评分dto
     */
    void  updateWhetherViewBySpecialist(BidSpecialistScoreDTO bidSpecialistScoreDTO);

    /**
     * 更新评分并更新供应商的评分
     */
    void updateScoreWithBidVendor(BidSpecialistScoreDTO bidSpecialistScoreDTO);

}

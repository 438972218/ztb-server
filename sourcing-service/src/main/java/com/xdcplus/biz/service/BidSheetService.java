package com.xdcplus.biz.service;

import com.xdcplus.biz.common.pojo.dto.BidSheetDTO;
import com.xdcplus.biz.common.pojo.dto.BidSheetFilterDTO;
import com.xdcplus.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.biz.common.pojo.vo.BidSpecialistVO;
import com.xdcplus.biz.generator.BidSheetBaseService;
import com.xdcplus.biz.common.pojo.entity.BidSheet;
import com.xdcplus.biz.common.pojo.vo.BidSheetVO;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.ProcessTransforDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.RequestDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.RequestFlowDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;


/**
 * bid招标单(BidSheet)表服务接口层
 *
 * @author makejava
 * @since 2021-08-12 14:45:42
 */
public interface BidSheetService extends BidSheetBaseService<BidSheet, BidSheetVO, BidSheet> {
    /**
     * 添加bid招标单(BidSheet)返回VO
     *
     * @param bidSheetDTO bid招标单(BidSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    BidSheetVO saveBidSheetReturnVO(BidSheetDTO bidSheetDTO);

    /**
     * 查询一个并带出附表中数据
     *
     * @param id bid招标单(BidSheet)主键
     * @return {@link BidSheetVO} bid招标单(BidSheet)信息
     */
    BidSheetVO showBidSheetByRequestId(Long id);

    /**
     * 查询招标单(request)(BidSheet)
     *
     * @param bidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<  BidSheetVO  >} 状态信息
     */
    PageVO<BidSheetVO> queryBidSheetWithRequest(BidSheetFilterDTO bidSheetFilterDTO);

    /**
     * 查询招标单模板(request)(BidSheet)
     *
     * @param bidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<  BidSheetVO  >} 状态信息
     */
    PageVO<BidSheetVO> queryBidSheetTemplate(BidSheetFilterDTO bidSheetFilterDTO);

    int selectCountByProjectId(Long id);


    void submitBeforeJudgeStatus(BidSheetDTO bidSheetDTO, RequestDTO requestDTO);

    void submitAfterJudgeStatus(BidSheetDTO bidSheetDTO);

    ProcessTransforDTO approveBeforeJudgeStatus(RequestFlowDTO requestFlowDTO);

    void approveAfterJudgeStatus(RequestFlowDTO requestFlowDTO);

    List<BidSpecialistScore> insertBidSpecialistScore(List<BidSpecialistVO> bidSpecialistVOS, BidSheetVO bidSheetVO,
                                                      long bidVendorId, String bidVendorName, List<BidSpecialistScore> bidSpecialistScores);

    Boolean updateBidSheetMark(BidSheetVO bidSheetVO, String mark);

}

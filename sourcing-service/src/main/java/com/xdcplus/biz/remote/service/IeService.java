package com.xdcplus.biz.remote.service;

import com.xdcplus.ztb.common.remote.domain.workflow.dto.*;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.ProcessConfigVO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestFlowVO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import java.util.List;

/**
 * 即请求服务
 *
 * @author Fish.Fei
 * @date 2021/08/16
 */
public interface IeService {

    RequestVO saveRequest(RequestDTO requestDTO);

    PageVO<RequestVO> findRequest(RequestFilterDTO requestFilterDTO);

    List<RequestVO> findRequestByParentId(Long parentId);

    RequestVO findRequestById(Long requestId);

    List<RequestVO> handleMatters(HandleMattersFilterDTO handleMattersFilterDTO);

    ResponseVO processTransfor(ProcessTransforDTO processTransforDTO);

    List<RequestFlowVO> findRequestFlowByRequestId(Long requestId);

    PageVO<ProcessConfigVO> findProcessConfigFilter(ProcessConfigFilterDTO processConfigFilterDTO);


}

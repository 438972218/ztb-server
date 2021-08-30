package com.xdcplus.vendor.remote.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.vendor.remote.IeRequestRemote;
import com.xdcplus.vendor.remote.service.IeService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.*;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.ztb.common.tool.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 即服务impl
 * 即请求服务impl
 *
 * @author Fish.Fei
 * @date 2021/08/16
 */
@Slf4j
@Service("ieRequestService")
public class IeServiceImpl implements IeService {

    @Autowired
    IeRequestRemote ieRequestRemote;

    @Override
    public PageVO<RequestVO> findRequest(RequestFilterDTO requestFilterDTO) {
        ResponseVO<PageVO<RequestVO>> responseVO = ieRequestRemote.findRequest(requestFilterDTO);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("getRequestVO() get api findRequest failed");
            throw new ZtbWebException(ResponseEnum.API_REQUEST_FLOWVO_FAIL, responseVO.getMessage());
        }

        return ResponseUtils.getResponse(responseVO);
    }

    @Override
    public List<RequestVO> findRequestByParentId(Long parentId) {
        RequestFilterDTO requestFilterDTO =new RequestFilterDTO();
        requestFilterDTO.setParentId(parentId);
        requestFilterDTO.setCurrentPage(-1);
        ResponseVO<PageVO<RequestVO>> responseVO = ieRequestRemote.findRequest(requestFilterDTO);

        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("getRequestFlowVO() get api findRequestByParentId failed");
            throw new ZtbWebException(ResponseEnum.API_REQUEST_FLOWVO_FAIL, responseVO.getMessage());
        }

        return ResponseUtils.getResponse(responseVO).getRecords();
    }

    @Override
    public RequestVO findRequestById(Long requestId) {
        ResponseVO<RequestVO> responseVO = ieRequestRemote.findRequestById(requestId);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("getRequestFlowVO() get api findRequestById failed");
            throw new ZtbWebException(ResponseEnum.API_REQUEST_FLOWVO_FAIL, requestId+":"+responseVO.getMessage());
        }

        return ResponseUtils.getResponse(responseVO);
    }

}

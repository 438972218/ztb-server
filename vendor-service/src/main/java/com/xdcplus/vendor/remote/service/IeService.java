package com.xdcplus.vendor.remote.service;

import com.xdcplus.ztb.common.remote.domain.workflow.dto.*;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 即请求服务
 *
 * @author Fish.Fei
 * @date 2021/08/16
 */
public interface IeService {

    PageVO<RequestVO> findRequest(RequestFilterDTO requestFilterDTO);

    List<RequestVO> findRequestByParentId(Long parentId);

    RequestVO findRequestById(Long requestId);

}

package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.entity.UserTo;
import com.xdcplus.workflow.common.pojo.vo.UserToVO;
import com.xdcplus.ztb.common.mp.service.BaseService;

import java.util.List;

/**
 *  用户去向信息服务类
 *
 * @author Rong.Jia
 * @date  2021-07-01
 */
public interface UserToService extends BaseService<UserTo, UserToVO, UserTo> {

    /**
     * 查询用户去向信息
     *
     * @return {@link List<UserToVO>} 用户去向信息集合
     */
    List<UserToVO> findUserTo();











}

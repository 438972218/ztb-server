package com.xdcplus.workflow.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xdcplus.workflow.remote.PermRemote;
import com.xdcplus.workflow.service.HttpService;
import com.xdcplus.ztb.common.cache.RedisUtils;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysUserInfoDTO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysDepartmentVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.constants.CacheConstant;
import com.xdcplus.ztb.common.tool.constants.QueryConstant;
import com.xdcplus.ztb.common.tool.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HTTP 调用业务层接口实现类
 *
 * @author Rong.Jia
 * @date 2021/07/20
 */
@Service
@Slf4j
public class HttpServiceImpl implements HttpService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PermRemote permRemote;

    @Override
    public Long getBigBossUser() {
        SysUserInfoVO sysUserInfoVO = ResponseUtils.getResponse(permRemote.getGeneralManagerSysUser());
        return getUserInfoId(sysUserInfoVO, QueryConstant.ID);
    }

    @Override
    public Long getSuperiorUserByUser(Long userId, String username) {

        SysUserInfoDTO sysUserInfoDTO = SysUserInfoDTO.builder()
                .id(userId).userName(username).build();
        return getUserInfoId(ResponseUtils.getResponse(permRemote.getSysUserMangerByUserIdOrUserName(sysUserInfoDTO)), QueryConstant.ID);
    }

    @Override
    public List<SysDepartmentVO> getDepartments() {

        if (!redisUtils.hasKey(CacheConstant.DEPARTMENTS)) {

            List<SysDepartmentVO> departmentList = ResponseUtils.getResponse(permRemote.getSysDepartmentList());
            if (CollectionUtil.isNotEmpty(departmentList)) {
                redisUtils.set(CacheConstant.DEPARTMENTS, JSON.toJSONString(departmentList));
            }
        }

        return redisUtils.get(CacheConstant.DEPARTMENTS, new TypeReference<List<SysDepartmentVO>>() {
        });
    }

    @Override
    public Long getDepartmentHeadByDepartmentId(Long departmentId) {

        SysUserInfoVO sysUserInfoVO = ResponseUtils.getResponse(permRemote.getSysUserManagerByDepartmentId(departmentId));
        return getUserInfoId(sysUserInfoVO, QueryConstant.ID);
    }

    @Override
    public Long getUserInfoByName(String username) {

        SysUserInfoVO sysUserInfoVO = ResponseUtils.getResponse(permRemote.queryByUserName(username));
        return getUserInfoId(sysUserInfoVO, QueryConstant.ID);
    }

    @Override
    public void refreshDepartmentsCache() {

        List<SysDepartmentVO> departmentList = ResponseUtils.getResponse(permRemote.getSysDepartmentList());
        if (CollectionUtil.isNotEmpty(departmentList)) {
            redisUtils.set(CacheConstant.DEPARTMENTS, JSON.toJSONString(departmentList));
        }

    }

    /**
     * 获取用户信息标识
     *
     * @param sysUserInfoVO 用户信息
     * @param fieldName     字段名
     * @return {@link Long} 用户去向
     */
    private <T> Long getUserInfoId(SysUserInfoVO sysUserInfoVO, String fieldName) {
        return ResponseUtils.getFieldValue(sysUserInfoVO, fieldName, Long.class);
    }


}

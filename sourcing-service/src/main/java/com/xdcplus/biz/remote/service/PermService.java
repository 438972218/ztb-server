package com.xdcplus.biz.remote.service;

import com.xdcplus.ztb.common.remote.domain.perm.vo.GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysCompanyVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysEmployeeVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 烫发服务
 *
 * @author Fish.Fei
 * @date 2021/08/17
 */
public interface PermService {

    /**
     * 查询分部表(公司)
     * @param id
     * @return
     */
    SysCompanyVO sysCompanyQueryById(Long id);

    /**
     * 获取公司tree
     * @return
     */
    List<SysCompanyVO> getDepartmentTree();

    /**
     * 判断公司是否是最底层的公司，只有最低层的公司，才允许添加部门
     * @param id id
     * @return {@link ResponseVO}
     */
    ResponseVO judgeGroupCompany(Long id);

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<Long> queryByUserId(Long userId);

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysUserInfoVO} 用户信息vo
     */
    SysUserInfoVO sysUserQueryById(Long id);

    SysUserInfoVO queryByUserName(String userName);

    SysEmployeeVO getEmployeeVoByUserName(String userName);

    GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO getDepartmentManagerEmployeeVoAndSysUserVoByUserName(String userName);


}

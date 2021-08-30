package com.xdcplus.vendorperm.service;

import com.xdcplus.vendorperm.common.pojo.dto.sysrole.SysRoleDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysrole.SysRoleFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.sysrole.SysRoleVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 角色表(SysRole)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:08:43
 */
public interface SysRoleService {


    /**
     * 获取sys角色分页通过条件
     *
     * @param sysRoleFilterDto 系统角色过滤dto
     * @return {@link PageVO<SysRoleVo>}
     */
    PageVO<SysRoleVo> getSysRolePageByCondition(SysRoleFilterDto sysRoleFilterDto);

    /**
     * 根据条件查询角色
     *
     * @param sysRoleFilterDto 系统角色过滤dto
     * @return {@link List<SysRoleVo>}
     */
    List<SysRoleVo> getSysRoleListByCondition(SysRoleFilterDto sysRoleFilterDto);

    /**
     * 获取sys角色列表
     *
     * @return {@link List<SysRoleVo>}
     */
    List<SysRoleVo> getSysRoleList();

    /**
     * 根据用户id获取角色标识
     *
     * @param userName 用户名
     * @return {@link String}
     */
    String getSysRoleMarkByUserId(String userName);

    /**
     * 通过主键id查询
     *
     * @param id id
     * @return {@link SysRoleVo}
     */
    SysRoleVo queryById(Long id);

    /**
     * 根据用户id查询角色信息
     *
     * @param userId 用户id
     * @return {@link List<SysRoleVo>}
     */
    List<SysRoleVo> queryByUserId(Long userId);


    /**
     * 新增角色
     *
     * @param sysRole   角色
     * @param loginUser 登录用户
     */
    void insert(SysRoleDto sysRole,String loginUser);


    /**
     * 修改数据
     *
     * @param sysRole   角色
     * @param loginUser 登录用户
     */
    void updateById(SysRoleDto sysRole,String loginUser);


    /**
     * 通过主键id删除
     *
     * @param id        主键id
     * @param loginUser 登录用户
     */
    void deleteById(Long id,String loginUser);

    /**
     * 根据公司id查询角色信息
     *
     * @param getByCompanyId 获取通过公司id
     * @return {@link List<SysRoleVo>}
     */
    List<SysRoleVo> getByCompanyId(Long getByCompanyId);

}

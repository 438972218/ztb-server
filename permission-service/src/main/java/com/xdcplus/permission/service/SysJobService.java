package com.xdcplus.permission.service;

import com.xdcplus.permission.common.pojo.dto.sysjob.SysJobDto;
import com.xdcplus.permission.common.pojo.dto.sysjob.SysJobFilterDto;
import com.xdcplus.permission.common.pojo.vo.sysjob.SysJobVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 职务表(SysJob)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:04
 */
public interface SysJobService {


    /**
     * 获取职务分页通过条件
     *
     * @param sysJobFilterDto 职务dto
     * @return {@link <PageInfo<SysJobVo>>}
     */
    PageVO<SysJobVo> getSysJobPageByCondition(SysJobFilterDto sysJobFilterDto);
    List<SysJobVo> getSysJobByCondition(SysJobFilterDto sysJobFilterDto);

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysJobVo} 职务vo
     */
    SysJobVo queryById(Long id);


    /**
     * 插入
     *
     * @param sysJobDto 职务dto
     */
    void insert(SysJobDto sysJobDto,String loginUser);


    /**
     * 更新通过id
     *
     * @param sysJobDto 职务dto
     */
    void updateById(SysJobDto sysJobDto,String loginUser);


    /**
     * 删除通过id
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);


    /**
     * 根据职位类别id获取职位
     *
     * @param jobTypeId 作业类型id
     * @return {@link List<SysJobVo>}
     */
    List<SysJobVo> getByJobTypeId(Long jobTypeId);
}

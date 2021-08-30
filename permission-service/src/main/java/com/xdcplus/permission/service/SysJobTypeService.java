package com.xdcplus.permission.service;

import com.xdcplus.permission.common.pojo.dto.sysjobtype.SysJobTypeDto;
import com.xdcplus.permission.common.pojo.dto.sysjobtype.SysJobTypeFilterDto;
import com.xdcplus.permission.common.pojo.vo.sysjobtype.SysJobTypeVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 职务类别表(SysJobType)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
public interface SysJobTypeService {

    /**
     * 获取职务类别分页通过条件
     *
     * @param sysJobTypeFilterDto 职务类别dto
     * @return {@link <PageInfo<SysJobTypeVo>>} 职务类别vo
     */
    PageVO<SysJobTypeVo> getSysJobTypePageByCondition(SysJobTypeFilterDto sysJobTypeFilterDto);

    /**
     * 根据条件获取职位类型
     *
     * @return {@link List<SysJobTypeVo>}
     */
    List<SysJobTypeVo> getSysJobTypeByCondition();


    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysJobTypeVo} 职务类别vo
     */
    SysJobTypeVo queryById(Long id);


    /**
     * 插入
     *
     * @param sysJobTypeDto 职务类别dto
     */
    void insert(SysJobTypeDto sysJobTypeDto,String loginUser);


    /**
     * 更新通过id
     *
     * @param sysJobTypeDto 职务类别dto
     */
    void updateById(SysJobTypeDto sysJobTypeDto,String loginUser);

    /**
     * 删除通过id
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);

}

package com.xdcplus.vendorperm.service;

import com.xdcplus.vendorperm.common.pojo.dto.sysdictionary.SysDictionaryDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysdictionary.SysDictionaryFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.sysdictionary.SysDictionaryVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

/**
 * 字典表(SysDictionary)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
public interface SysDictionaryService {

    /**
     * 获取字典分页通过条件
     *
     * @param sysDictionaryFilterDto 字典dto
     * @return {@link <PageInfo<SysDictionaryVo>>}
     */
    PageVO<SysDictionaryVo> getSysDictionaryPageByCondition(SysDictionaryFilterDto sysDictionaryFilterDto);

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysDictionaryVo}
     */
    SysDictionaryVo queryById(Long id);

    /**
     * 插入
     *
     * @param sysDictionaryDto 系统字典dto
     */
    void insert(SysDictionaryDto sysDictionaryDto,String loginUser);


    /**
     * 更新通过id
     *
     * @param sysDictionaryDto 系统字典dto
     */
    void updateById(SysDictionaryDto sysDictionaryDto,String loginUser);


    /**
     * 删除通过id
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);

}

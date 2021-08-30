package com.xdcplus.vendorperm.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendorperm.common.pojo.entity.SysSecuritySettings;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 安全设置表(SysSecuritySettings)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
public interface SysSecuritySettingsDao extends IBaseMapper<SysSecuritySettings> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysSecuritySettings 实例对象
     * @return 对象列表
     */
    List<SysSecuritySettings> queryAll(@Param("sysSecuritySettings")SysSecuritySettings sysSecuritySettings);


}


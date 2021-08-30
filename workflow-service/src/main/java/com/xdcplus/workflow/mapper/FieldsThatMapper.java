package com.xdcplus.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdcplus.workflow.common.pojo.entity.FieldsThat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对接字段说明 Mapper 接口
 *
 * @author Rong.Jia
 * @date 2021-06-24
 */
public interface FieldsThatMapper extends BaseMapper<FieldsThat> {

    /**
     * 查询字段对应关系通过类型
     *
     * @param type 类型
     * @return {@link List<FieldsThat>} 字段对应关系
     */
    List<FieldsThat> findFieldsThatByType(@Param("type") Byte type);


}

package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.FieldsThatDTO;
import com.xdcplus.workflow.common.pojo.entity.FieldsThat;
import com.xdcplus.workflow.common.pojo.vo.FieldsThatVO;
import com.xdcplus.ztb.common.mp.service.BaseService;

import java.util.List;

/**
 * 对接字段说明 服务类
 *
 * @author Rong.Jia
 * @date 2021-06-24
 */
public interface FieldsThatService extends BaseService<FieldsThat, FieldsThatVO, FieldsThat> {

    /**
     * 添加字段对应关系
     *
     * @param fieldsThatDTO 字段那DTO
     * @return {@link Boolean} 是否成功
     */
    Boolean saveFieldsThat(FieldsThatDTO fieldsThatDTO);

    /**
     * 修改字段对应关系
     *
     * @param fieldsThatDTO 字段DTO
     * @return {@link Boolean} 是否成功
     */
    Boolean updateFieldsThat(FieldsThatDTO fieldsThatDTO);

    /**
     * 删除字段对应关系
     *
     * @param id 主键ID
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteFieldsThat(Long id);

    /**
     * 查询字段对应关系
     *
     * @param type 类型（1：组织，2：部门，3：人员）
     * @return {@link List<FieldsThatVO>} 对应关系集合
     */
    List<FieldsThatVO> findFieldsThats(Byte type);

    /**
     * 同步字段对应关系
     *
     * @param fieldsThatDTO 字段DTO
     * @return {@link Boolean} 是否成功
     */
    Boolean syncFieldsThat(FieldsThatDTO fieldsThatDTO);


}

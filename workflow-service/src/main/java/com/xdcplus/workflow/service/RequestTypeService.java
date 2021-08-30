package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.RequestTypeDTO;
import com.xdcplus.workflow.common.pojo.dto.RequestTypeFilterDTO;
import com.xdcplus.workflow.common.pojo.entity.RequestType;
import com.xdcplus.workflow.common.pojo.vo.RequestTypeVO;
import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

/**
 *  表单类型服务类
 *
 * @author Rong.Jia
 * @date 2021-08-05
 */
public interface RequestTypeService extends BaseService<RequestType, RequestTypeVO, RequestType> {

    /**
     * 保存表单类型
     *
     * @param requestTypeDTO 表单类型DTO
     * @return {@link Long} 主键
     */
    Long saveRequestType(RequestTypeDTO requestTypeDTO);


    /**
     * 更新表单类型
     *
     * @param requestTypeDTO 表单类型DTO
     * @return {@link Long} 主键
     */
    Long updateRequestType(RequestTypeDTO requestTypeDTO);

    /**
     * 删除表单类型
     *
     * @param typeId 类型ID
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteRequestType(Long typeId);

    /**
     * 查询表单类型
     *
     * @param requestTypeFilterDTO 表单类型过滤器DTO
     * @return {@link PageVO<RequestTypeVO>} 表单类型信息
     */
    PageVO<RequestTypeVO> findRequestType(RequestTypeFilterDTO requestTypeFilterDTO);

    /**
     * 验证表单类型信息是否存在
     *
     * @param typeName 类型名字
     * @return {@link Boolean} 是否存在
     */
    Boolean validationExists(String typeName);

    /**
     * 查询类型信息
     *
     * @param id 主键ID
     * @return {@link RequestTypeVO} 类型信息
     */
    RequestTypeVO findOne(Long id);


}

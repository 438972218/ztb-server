package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.Dictionary;
import com.xdcplus.biz.common.pojo.dto.DictionaryDTO;
import com.xdcplus.biz.common.pojo.dto.DictionaryFilterDTO;
import com.xdcplus.biz.common.pojo.vo.DictionaryVO;

import java.util.List;

/**
 * 字典管理(Dictionary)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:20:51
 */
public interface DictionaryBaseService<S, T, E> extends BaseService<Dictionary, DictionaryVO, Dictionary> {

    /**
     * 添加字典管理(Dictionary)
     *
     * @param dictionaryDTO 字典管理(DictionaryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveDictionary(DictionaryDTO dictionaryDTO);

    /**
     * 修改字典管理(Dictionary)
     *
     * @param dictionaryDTO 字典管理(DictionaryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateDictionary(DictionaryDTO dictionaryDTO);

    /**
     * 批量保存或更新字典管理(Dictionary)
     *
     * @param dictionaryList 字典管理(DictionaryList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<Dictionary> dictionaryList);

    /**
     * 批量保存或更新字典管理(DictionaryDTOList)
     *
     * @param dictionaryDTOList 字典管理(DictionaryDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<DictionaryDTO> dictionaryDTOList);

    /**
     * 删除字典管理(Dictionary)
     *
     * @param id 字典管理(Dictionary)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteDictionaryLogical(Long id);

    /**
     * 查询字典管理(Dictionary)
     *
     * @param dictionaryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<DictionaryVO>} 状态信息
     */
    List<DictionaryVO> queryDictionaryVOList(DictionaryFilterDTO dictionaryFilterDTO);

    /**
     * 查询字典管理(Dictionary)
     *
     * @param dictionaryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<DictionaryVO>} 状态信息
     */
    PageVO<DictionaryVO> queryDictionary(DictionaryFilterDTO dictionaryFilterDTO);

    /**
     * 查询一个
     *
     * @param id 字典管理(Dictionary)主键
     * @return {@link DictionaryVO} 字典管理(Dictionary)信息
     */
    DictionaryVO queryDictionaryById(Long id);


}

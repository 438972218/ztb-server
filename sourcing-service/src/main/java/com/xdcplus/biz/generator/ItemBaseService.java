package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.Item;
import com.xdcplus.biz.common.pojo.dto.ItemDTO;
import com.xdcplus.biz.common.pojo.dto.ItemFilterDTO;
import com.xdcplus.biz.common.pojo.vo.ItemVO;

import java.util.List;

/**
 * 品类管理(Item)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-18 13:47:06
 */
public interface ItemBaseService<S, T, E> extends BaseService<Item, ItemVO, Item> {

    /**
     * 添加品类管理(Item)
     *
     * @param itemDTO 品类管理(ItemDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveItem(ItemDTO itemDTO);

    /**
     * 修改品类管理(Item)
     *
     * @param itemDTO 品类管理(ItemDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateItem(ItemDTO itemDTO);

    /**
     * 批量保存或更新品类管理(Item)
     *
     * @param itemList 品类管理(ItemList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<Item> itemList);

    /**
     * 批量保存或更新品类管理(ItemDTOList)
     *
     * @param itemDTOList 品类管理(ItemDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<ItemDTO> itemDTOList);

    /**
     * 删除品类管理(Item)
     *
     * @param id 品类管理(Item)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteItemLogical(Long id);

    /**
     * 查询品类管理(Item)
     *
     * @param itemFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ItemVO>} 状态信息
     */
    List<ItemVO> queryItemVOList(ItemFilterDTO itemFilterDTO);

    /**
     * 查询品类管理(Item)
     *
     * @param itemFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ItemVO>} 状态信息
     */
    PageVO<ItemVO> queryItem(ItemFilterDTO itemFilterDTO);

    /**
     * 查询一个
     *
     * @param id 品类管理(Item)主键
     * @return {@link ItemVO} 品类管理(Item)信息
     */
    ItemVO queryItemById(Long id);


}

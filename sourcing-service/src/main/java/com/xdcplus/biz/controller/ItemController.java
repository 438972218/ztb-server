package com.xdcplus.biz.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.ItemDTO;
import com.xdcplus.biz.common.pojo.dto.ItemFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.ItemVO;
import com.xdcplus.biz.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import javax.validation.constraints.NotNull;


/**
 * 品类管理(Item)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-18 13:47:07
 */
@Api(tags = "品类管理(Item)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("item")
public class ItemController extends AbstractController {

    @Autowired
    private ItemService itemService;

    @ApiOperation("新增品类管理")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveItem(@RequestBody ItemDTO itemDTO) {

        log.info("saveItem {}", itemDTO.toString());

        itemDTO.setCreatedUser(getAccount());
        itemService.saveItem(itemDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改品类管理")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateItem(@RequestBody ItemDTO itemDTO) {

        log.info("updateItem {}", itemDTO.toString());

        itemDTO.setUpdatedUser(getAccount());
        itemService.updateItem(itemDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除品类管理")
    @DeleteMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "itemId", dataType = "Long", value = "品类管理ID", required = true),
    })
    public ResponseVO deleteItemLogical(@PathVariable("itemId")
                                        @NotNull(message = "品类管理ID不能为空") Long itemId) {

        log.info("deleteItemLogical {}", itemId);

        itemService.deleteItemLogical(itemId);

        return ResponseVO.success();
    }

    @ApiOperation("查询品类管理")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ItemVO>> findItem(ItemFilterDTO itemFilterDTO) {

        log.info("findItem {}", itemFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(itemFilterDTO);

        return ResponseVO.success(itemService.queryItem(itemFilterDTO));
    }


}

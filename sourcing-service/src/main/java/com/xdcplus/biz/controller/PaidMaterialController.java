package com.xdcplus.biz.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.PaidMaterialDTO;
import com.xdcplus.biz.common.pojo.dto.PaidMaterialFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.biz.service.PaidMaterialService;
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
import java.util.List;


/**
 * 竞价物品(PaidMaterial)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-23 14:11:11
 */
@Api(tags = "竞价物品(PaidMaterial)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("paidMaterial")
public class PaidMaterialController extends AbstractController {

    @Autowired
    private PaidMaterialService paidMaterialService;

    @ApiOperation("新增竞价物品")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePaidMaterial(@RequestBody PaidMaterialDTO paidMaterialDTO) {

        log.info("savePaidMaterial {}", paidMaterialDTO.toString());

        paidMaterialDTO.setCreatedUser(getAccount());
        paidMaterialService.savePaidMaterial(paidMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改竞价物品")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePaidMaterial(@RequestBody PaidMaterialDTO paidMaterialDTO) {

        log.info("updatePaidMaterial {}", paidMaterialDTO.toString());

        paidMaterialDTO.setUpdatedUser(getAccount());
        paidMaterialService.updatePaidMaterial(paidMaterialDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除竞价物品")
    @DeleteMapping(value = "/{paidMaterialId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "paidMaterialId", dataType = "Long", value = "竞价物品ID", required = true),
    })
    public ResponseVO deletePaidMaterialLogical(@PathVariable("paidMaterialId")
                                                @NotNull(message = "竞价物品ID不能为空") Long paidMaterialId) {

        log.info("deletePaidMaterialLogical {}", paidMaterialId);

        paidMaterialService.deletePaidMaterialLogical(paidMaterialId);

        return ResponseVO.success();
    }

    @ApiOperation("查询竞价物品")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidMaterialVO>> findPaidMaterial(PaidMaterialFilterDTO paidMaterialFilterDTO) {

        log.info("findPaidMaterial {}", paidMaterialFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidMaterialFilterDTO);

        return ResponseVO.success(paidMaterialService.queryPaidMaterial(paidMaterialFilterDTO));
    }



    @ApiOperation("批量新增竞价物品（业务）")
    @PostMapping(value = "/batchSave", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO batchSavePaidMaterial(@RequestBody List<PaidMaterialDTO> paidMaterialDTOS) {

        log.info("batchSavePaidMaterial {}", paidMaterialDTOS.toString());
        paidMaterialDTOS.forEach(paidMaterialDTO -> paidMaterialDTO.setCreatedUser(getAccount()));

        return ResponseVO.success(paidMaterialService.batchSavePaidMaterial(paidMaterialDTOS));
    }


}

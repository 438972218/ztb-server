package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 公告板过滤器DTO
 *
 * @author Rong.Jia
 * @date 2021/08/16 11:43:36
 */
@ApiModel("公告板过滤查询 参数对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class BulletinBoardFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -4207022228453511820L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 公告类型
     */
    @ApiModelProperty(value = "公告类型 详见字典")
    private Byte type;







}

package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 公告牌DTO
 *
 * @author Rong.Jia
 * @date 2021/08/16 10:16:53
 */
@ApiModel("公告牌 参数对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class BulletinBoardDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 2400376372328372537L;

    /**
     * 名称
     */
    @NotBlank(message = "名称 不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 公告类型
     */
    @NotNull(message = "公告类型 不能为空")
    @ApiModelProperty(value = "公告类型 详见字典", required = true)
    private Byte type;

    /**
     * 公告时间
     */
    @NotNull(message = "公告时间 不能为空")
    @ApiModelProperty(value = "公告时间", required = true)
    private Long bulletinTime;













}

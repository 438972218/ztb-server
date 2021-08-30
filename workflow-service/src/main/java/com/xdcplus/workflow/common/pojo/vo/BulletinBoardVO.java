package com.xdcplus.workflow.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 公告牌VO
 *
 * @author Rong.Jia
 * @date 2021/08/16 10:17:02
 */
@ApiModel("公告牌信息 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class BulletinBoardVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -4346504890900295663L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 公告类型
     */
    @ApiModelProperty(value = "公告类型 详见字典")
    private Byte type;

    /**
     * 公告时间
     */
    @ApiModelProperty(value = "公告时间")
    private Long bulletinTime;















}

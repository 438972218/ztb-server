package com.xdcplus.vendorperm.common.pojo.query.sysposition;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 岗位表(SysPosition)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("过滤查询 参数对照对象")
public class SysPositionFilterQuery extends PageDTO implements Serializable {
    private static final long serialVersionUID = -88913980179924816L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 岗位简称
     */
    private String shortName;
    /**
     * 岗位全称
     */
    private String fullName;
    /**
     * 所属职务
     */
    private Long jobId;
    /**
     * 岗位职责
     */
    private String positionDuty;
    /**
     * 任职资格
     */
    private String positionQualifications;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 更新人
     */
    private String updatedUser;
    /**
     * 更新时间
     */
    private Long updatedTime;


}

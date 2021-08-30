package com.xdcplus.permission.common.pojo.vo.sysuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询所有用户的用户信息，及用户及关联的部门及岗位信息 返回参数
 *
 * @author Bullion.Yan
 * @date 2021/08/26
 */
@Data
public class GetAllUserAndDepartAndPostionVO {
    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String name;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String mail;
    /**
     * 岗位名称
     */
    @ApiModelProperty("岗位名称")
    private String positionName;
    /**
     * 部门id
     */
    @ApiModelProperty("部门id")
    private Long departmentId;
    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String departmentName;
    

}

package com.xdcplus.permission.common.pojo.dto.sysemployee;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询未绑定用户的员工 请求参数对照对象")
public class GetSysEmployeeByNoBindUser extends PageDTO implements Serializable {

    @ApiModelProperty("姓名")
    private String realName;

}

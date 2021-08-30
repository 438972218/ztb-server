package com.xdcplus.biz.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 服务器路径配置
 *
 * @author Rong.Jia
 * @date 2021/08/03 14:02:16
 */
@Data
@Component
@ConfigurationProperties(prefix = "url.path")
public class ServerPathConfig {

    /**
     * pce 公司查询用户id
     */
    private String pceSysCompanyQueryByUserId;

    /**
     * pce 公司树
     */
    private String pceSysCompanyGetSysCompanyTree;

    /**
     * pce sys判断集团公司
     */
    private String pceSysCompanyJudgeGroupCompany;

    /**
     * pce 根据用户id查询角色
     */
    private String pceSysRoleQueryByUserId;

    /**
     * pce 根据id查询用户
     */
    private String pceSysUserQueryByUserId;

    /**
     * 请求流过程
     */
    private String ieRequestFlowPostProcess;

    /**
     * 请求
     */
    private String ieRequestSaveRequest;

    /**
     * 根据id查询请求
     */
    private String ieRequestQueryRequestById;

    /**
     * 根据父id查询请求
     */
    private String ieRequestQueryRequestByParentId;


}

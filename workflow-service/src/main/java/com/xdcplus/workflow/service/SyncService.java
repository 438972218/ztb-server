package com.xdcplus.workflow.service;

/**
 * 同步业务层接口
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
public interface SyncService {

    /**
     * 同步部门
     */
    void syncDepartment();

    /**
     * 同步用户
     */
    void syncUser();

    /**
     * 同步组织
     */
    void syncOrganization();


}

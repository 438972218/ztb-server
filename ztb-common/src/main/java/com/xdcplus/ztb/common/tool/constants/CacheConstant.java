package com.xdcplus.ztb.common.tool.constants;

/**
 * 缓存常量类
 *
 * @author Rong.Jia
 * @date 2021/06/29
 */
public class CacheConstant {

    /**
     *  项目
     */
    public static final String ZTB_SD = "ztb-sd:";

    /**
     *  工作流
     */
    public static final String WORKFLOW = ZTB_SD + "synergy:";

    /**
     * 供应商端
     */
    public static final String VENDOR = "vendor:";

    public static final String LDAP = WORKFLOW + "ladap:";

    public static final String AD_LDAP = LDAP + "ad:";

    public static final String AD_DEPARTMENT_CACHE =  AD_LDAP + "departments_cache";

    public static final String DEPARTMENTS = WORKFLOW + "departments_cache";

    public static final String BIDDING_TIMES = VENDOR + "bidding_times";


    public static final String SOURCING_ENGINE = ZTB_SD + "sourcing:";
    public static final String PAIDS = SOURCING_ENGINE + "PAID_cache";







}

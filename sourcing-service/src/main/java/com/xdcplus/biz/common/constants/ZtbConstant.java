package com.xdcplus.biz.common.constants;

import java.util.Arrays;
import java.util.List;

/**
 * ztb常量
 *
 * @author Martin.Ji
 * @date 2021/07/22
 */
public class ZtbConstant {

    public static final String PLANT = "工厂";
    public static final String PERCENTAGE  = "百分比";
    public static final String ABSOLUTE_VALUE  = "绝对值";
    public static final String FORWARD  = "正向";
    public static final String BID_TITLE = "招标单-";
    public static final String INQUIRY_TITLE = "询价单-";
    public static final String PAID_TITLE = "竞价单-";
    public static final String PUBLIC  = "公开";
    public static final String SUBMIT  = "提交";
    public static final String SAVE  = "保存";
    public static final String BALLOT  = "已中签";
    public static final String HISTORY  = "历史";

    public static final String BID_TYPE  = "招标单类型";

    public static final String ARCHIVED = "已归档";
    public static final String CANCELED = "已取消";
    public static final String TOBESUBMITTED = "待提交";
    public static final String BIDOPENING = "待开标";
    public static final String TOAUDIT = "待审核";
    public static final String DAIFABU = "待发布";
    public static final String YIFABU = "已发布";
    public static final String TO_BE_AUTHORIZED = "待授权";
    public static final String AWARDOFBID = "待决标";
    public static final String CAIGOUHEJIA = "待核价";
    public static final String HEJIASHENGPI = "待审批";
    public static final String QIANJIAQIAN = "前加签";
    public static final String ZHUANJIA = "待专家评分";
    public static final String TOBECHOSEN  = "待候选";
    public static final String TO_PARTICIPATE  = "待参与";
    public static final String NO_PARTICIPATE  = "未参与";
    public static final String HAS_PARTICIPATE  = "已参与";
    public static final String HAS_REFUSED  = "已拒绝";
    public static final String TO_SCORE  = "待评分";
    public static final String EXPERT_RATING  = "专家评分";
    public static final String GROUP_RATING  = "组长评分";
    public static final String FOR_INVESTIGATING  = "待预审";
    public static final String IN_THE_BIDDING  = "招标中";
    public static final String BID_PROCESS  = "招标单流程";
    public static final String QUALIFICATION  = "资质";
    public static final String TECHNOLOGY  = "技术";
    public static final String COMMERCE  = "商业";

    public static final String BID_SAVE_STATUS  = "保存";
    public static final String VENDOR_REPLIED  = "已回复";
    public static final String  INVITED  = "受邀";
    public static final String  UNSENT  = "未发送";
    public static final String  VENDOR_NO_REPLIED  = "未回复";
    public static final String  SPECIALIST_SCORE_NEW  = "最新";

    public static final long RUNNING_STATUS_ID = 1430091118399266818L;

    /**
     * 审批流程id
     */
    public static final Long SHENGPI_PROCESS = 1415125159027109889L;
    public static final Long SHENGHE_TYPE_ID = 1424567739789418497L;
    public static final Long SHENGHE_RULE_ID = 5L;

    public static final List<Integer> PAID_SHEET_STATUSES = Arrays.asList(1, 2);

    public static final List<String> BID_STATUS_PINGGU = Arrays.asList("待资质评估", "待技术评估", "待商业评估");

//    private static final Map<String,String> BID_REQUEST_STATUS_MAP =new HashMap();
//    static {
//        BID_REQUEST_STATUS_MAP.put("前加签","待专家评分");
//        BID_REQUEST_STATUS_MAP.put("后加签","待专家组长确认");
//    }
}

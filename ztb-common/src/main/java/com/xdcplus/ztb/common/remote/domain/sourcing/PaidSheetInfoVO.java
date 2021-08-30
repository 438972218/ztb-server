package com.xdcplus.ztb.common.remote.domain.sourcing;

import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 竞价单(PaidSheet)表信息
 *
 * @author Rong.Jia
 * @date 2021/08/17 15:04:28
 */
@Data
public class PaidSheetInfoVO implements Serializable {

    private static final long serialVersionUID = -974810216043725604L;

    /**
     * requestId
     */
    private Long requestId;

    /**
     * 竞价单号
     */
    private String paidNum;

    /**
     * 采购组织id
     */
    private Long purchasingOrganizationId;

    /**
     * 采购组织
     */
    private String purchasingOrganization;

    /**
     * 公司代码id
     */
    private Long companyCodeId;

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 竞价单标题
     */
    private String title;

    /**
     * 竞价单类型
     */
    private String paidType;

    /**
     * 竞价方向
     */
    private String paidDirection;

    /**
     * 竞价方式
     */
    private String paidMode;

    /**
     * 付款方式
     */
    private String paymentMode;

    /**
     * 币种
     */
    private String currency;

    /**
     * 汇率
     */
    private String exchangeRate;

    /**
     * 报价开始时间
     */
    private Long offerStartTime;

    /**
     * 报价截止时间
     */
    private Long offerEndTime;

    /**
     * 标的类型
     */
    private String bidType;

    /**
     * 含税
     */
    private Integer offerTax;

    /**
     * 报价税码
     */
    private String offerTaxCode;

    /**
     * 税率
     */
    private String taxRate;

    /**
     * 密封报价
     */
    private Integer sealedBid;

    /**
     * 报价幅度
     */
    private String priceRange;

    /**
     * 幅度类型
     */
    private String rangeType;

    /**
     * 寻源类型
     */
    private String sourcingType;

    /**
     * 公开规则
     */
    private String publicRules;

    /**
     * 竞价规则
     */
    private String biddingRules;

    /**
     * 排名规则
     */
    private String rankingRules;

    /**
     * 价格类型
     */
    private String priceType;

    /**
     * 审批流程
     */
    private Integer approvalProcess;

    /**
     * 交货地点
     */
    private String deliveryPlace;

    /**
     * 竞价次数
     */
    private Integer bidNumber;

    /**
     * 自动延期
     */
    private String autoExtension;

    /**
     * 竞价排名
     */
    private String bidRanking;

    /**
     * 说明
     */
    private String explaination;

    /**
     * 单号
     */
    private String oddNumber;

    /**
     * request标题
     */
    private String requestTitle;

    /**
     * request状态
     */
    private String requestStatusName;

    /**
     * 供应商状态
     */
    private String vendorStatus;

    /**
     * request
     */
    private RequestVO requestVO;

    /**
     * 所有节点状态
     */
    private List<String> statusNames;

    /**
     * 子表单id
     */
    private Long requestRelationId;

    /**
     * 子表单ToUserId
     */
    private Long requestRelationToUserId;

    /**
     * 子表单ToRoleId
     */
    private Long requestRelationToRoleId;

    /**
     * 物品id
     */
    private Long paidMaterialId;

    /**
     * 用户信息
     */
    private SysUserInfoVO sysUserInfoVO;

    /**
     * 延时间隔
     */
    private Long delayInterval;

    /**
     * 竞价状态，1：进行中，2：暂停，3：已终止，4：截止
     */
    private Integer paidStatusMark;

    private List<PaidMaterialInfoVO> paidMaterialVOS;


}

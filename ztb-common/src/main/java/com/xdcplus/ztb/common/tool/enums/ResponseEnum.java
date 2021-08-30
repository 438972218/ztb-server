package com.xdcplus.ztb.common.tool.enums;

/**
 * 响应枚举
 *
 * @author Rong.Jia
 * @date 2021/07/28 09:16:48
 */
public enum ResponseEnum {

    /**
     *  枚举类code 开头使用规则：
     *  0: 成功；
     *  -1: 失败；
     *  1：参数不正确
     *
     *  900： 邮箱相关
     *  1000：公共
     *  3900：数字字典
     *  4000： 调度服务
     */

    // 成功
    SUCCESS(0,"成功"),

    // 参数不正确
    PARAMETER_ERROR(1, "参数不正确"),

    // 失败
    ERROR(-1, "失败"),
    SYSTEM_ERROR(-1, "系统错误"),
    FILE_LIMIT_EXCEEDED(-1, "文件超出限制, 请选择较小文件"),
    INT404_NOT_FOUND(-1,"找不到请求接口"),
    INT400_BAD_REQUEST(-1,"请求参数或方式错误"),


    // 未找到
    NOT_FOUND(404, "请求接口不存在"),

    // 请求方式错误
    REQUEST_MODE_ERROR(405, "请求方式错误, 请检查"),

    //媒体类型不支持
    MEDIA_TYPE_NOT_SUPPORTED(415, "媒体类型不支持"),

    /*-------------------------email begin----------------------------------------*/

    // 附件不存在
    ATTACHMENT_DOES_NOT_EXIST(900, "attachment does not exist"),

    // 接收者不能为空
    THE_RECEIVER_CANNOT_BE_EMPTY(901, "The receiver cannot be empty"),

    // 消息不能为空
    THE_MESSAGE_CANNOT_BE_EMPTY(902, "The message cannot be empty"),

    // 你的邮件客户端不支持html邮件
    YOUR_EMAIL_CLIENT_DOES_NOT_SUPPORT_HTML_MESSAGES(903,"你的邮件客户端不支持html邮件"),

    // 主题不能为空
    THE_TOPIC_CANNOT_BE_EMPTY(904,"The topic cannot be empty"),

    // 属性不能为空
    THE_PROPERTY_CANNOT_BE_EMPTY(905,"'%s' cannot be empty"),

    // 邮箱账号信息不能为空
    THE_EMAIL_ACCOUNT_INFORMATION_CANNOT_BE_EMPTY(906,"The email account information cannot be empty"),

    /*-------------------------email end----------------------------------------*/


    /*-------------------------common begin----------------------------------------*/

    // 1000：公共
    REQUEST_PARAMETER_FORMAT_IS_INCORRECT(1000, "请求参数格式不正确"),
    THE_ID_CANNOT_BE_EMPTY(1002, "id 不能为空"),
    THE_NAME_CANNOT_BE_EMPTY(1003, "名称不能为空"),
    DATA_QUOTE(1004, "数据被引用，无法执行操作"),
    TIME_IS_EMPTY(1005, "时间为空"),
    THE_PHONE_CANNOT_BE_EMPTY(1006, "联系电话不能为空"),
    THE_PHONE_ALREADY_EXISTS(1007, "联系电话已存在"),
    THE_STARTING_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_CURRENT_TIME(1008, "开始时间不能小于等于当前时间"),
    THE_END_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_START_TIME(1009, "结束时间不能等于小于开始时间"),
    THE_END_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_CURRENT_TIME(1010, "结束时间不能小于等于当前时间"),
    THE_PARAMETER_TYPE_IS_INCORRECT(1011, "参数类型不正确"),
    PARAMETER_CANNOT_BE_EMPTY(1012, "参数不能为空"),
    LACK_OF_PARAMETER(1013, "缺少必要参数，请检查"),
    DATA_RECORD_IS_NOT_EXISTS(1014,"记录不存在,无法执行操作"),
    CODE_ALREADY_EXISTS(1015,"编号已经存在,无法执行操作"),
    REQUEST_PARAMETER_IS_ERROR(1016,"请求参数有误"),
    REQUEST_PARAMETER_IS_NULL(1017,"请求参数不能为空"),

    /*-------------------------common end----------------------------------------*/


    /*-------------------------数据字典 begin----------------------------------------*/


    //  3900：数据字典
    DATA_DICTIONARY_LIST_NULL(3901,"数据字典列表为空"),
    DATA_DICTIONARY_CATEGORY_NOT_NULL(3902,"数据字典的类别不能为空"),
    DATA_DICTIONARY_NUMERICAL_NOT_NULL(3903,"数据字典的数值不能为空"),
    DATA_DICTIONARY_MEANING_NOT_NULL(3904,"数据字典的含义不能为空"),

    /*-------------------------数据字典 end----------------------------------------*/



    /*------------------------- 调度服务 begin code: 7000----------------------------------------*/

    SERVICE_EXCEPTIONS(4000, "服务异常, 请联系管理员"),



    /*------------------------- 调度服务 end----------------------------------------*/

    /*-------------------------交互服务 begin, code: 5000----------------------------------------*/


    // 单号规则模块
    THE_ORDER_NUMBER_RULE_ALREADY_EXISTS(5001, "单号规则已存在"),
    THE_ORDER_NUMBER_RULE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5402, "单号规则不存在, 或者已删除"),
    THE_ORDER_NUMBER_RULE_CANNOT_BE_MODIFIED(5403, "单号规则不能修改"),

    // 流程相关模块
    THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5601, "流程信息不存在, 或者已删除"),
    THE_PROCESS_ALREADY_EXISTS(5602, "流程信息已存在"),
    THE_FLOW_OPTION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5603, "流操作不存在, 或者已删除"),
    THE_FLOW_OPTION_ALREADY_EXISTS(5604, "流操作信息已存在"),
    THE_PROCESS_STATUS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5605, "流程状态信息不存在, 或者已删除"),
    THE_PROCESS_STATUS_ALREADY_EXISTS(5606, "流程状态信息已存在"),
    THE_QUALIFIER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5607, "流程规则信息不存在, 或者已删除"),
    THE_QUALIFIER_ALREADY_EXISTS(5608, "流程规则信息已存在"),
    THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5609, "表单信息不存在, 或者已删除"),
    THE_REQUEST_ALREADY_EXISTS(5610, "表单信息已存在"),
    FORM_DUPLICATE_ASSOCIATION(5611, "表单重复关联"),
    THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY(5613, "流程配置信息为空"),
    THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID(5614, "流程配置信息不合法"),
    THE_PROCESS_CONFIGURATION_NOT_EXIST_OR_HAS_BEEN_DELETED(5616, "流程配置信息不存在, 或者已删除"),
    A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND(5618, "未找到有效的流程配置, 请先添加流程配置"),
    FLOW_ABNORMAL_CURRENT_NODE_CANNOT_BE_FOUND(5619, "流转异常，找不到当前节点"),
    THE_FLOW_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5621, "流转信息不存在, 或者已删除"),
    ABNORMAL_FLOW_SPECIFIED_RETURN_PERSON_DOES_NOT_EXIST(5623, "流转异常，指定退回人不存在"),
    THE_FLOW_HAS_ENDED_AND_CANNOT_BE_CONTINUED(5624, "流转已结束，不可继续"),
    ABNORMAL_FLOW_SIGNATURE_STATUS_DOES_NOT_EXIST(5625, "流转异常，加签状态不存在"),
    A_LIST_CAN_ONLY_BE_CANCELLED_BY_THE_CREATOR(5626, "单子只允许创建人取消"),
    PROCESS_CONFIGURATION_VERSIONS_ARE_DUPLICATED(5627, "流程配置版本重复"),
    MISSING_START_NODE(5630, "缺少开始节点"),
    MISSING_END_NODE(5630, "缺少结束节点"),
    THE_UNSIGNED_FLOW_RECORD_WAS_NOT_OBTAINED_CORRECTLY(5631, "未正确获取非加签流转记录"),
    PRE_SIGNING_IS_NOT_ALLOWED_DURING_PRE_SIGNING(5632, "前加签过程中不允许后加签"),
    THIS_PARAMETER_CANNOT_BE_CONFIGURED_FOR_MULTIPLE_PROCESSES_SIMULTANEOUSLY(5631, "不可为多个流程同时配置"),


    // 表达式模块
    THE_EXPRESSION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5701, "表达式不存在, 或者已删除"),
    THE_EXPRESSION_ALREADY_EXISTS(5702, "表达式已存在"),
    THE_FORM_TYPE_ALREADY_EXISTS(5703, "表单类型已存在"),
    THE_FORM_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5704, "表单类型不存在或已删除"),
    THE_PROCESS_CONFIGURATION_VERSION_DOES_NOT_EXIST(5705, "流程配置版本不存在"),
    THE_FUNCTION_POLICY_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5707, "功能策略不存在或已删除"),
    NO_VALID_POLICY_WAS_FOUND(5708, "未查找到有效策略"),
    SCRIPT_RUNNING_EXCEPTION(5709, "脚本运行异常"),










    /*-------------------------交互服务 end----------------------------------------*/


    /*-------------------------外部集成服务 begin, code: 6000 ----------------------------------------*/

    // 外部集成服务 ： 6000
    LDAP_INFORMATION_ALREADY_EXISTS(6001, "Ldap 信息已存在"),
    LDAP_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(6002, "Ldap 信息不存在或已删除"),
    FIELD_CORRESPONDENCE_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(6003, "字段对应关系信息不存在或已删除"),
    THE_MAIL_TEMPLATE_EXISTS(6004, "邮件模板已存在"),
    THE_MAIL_TEMPLATE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(6005, "邮件模板不存在或已删除"),
    THE_EMAIL_TEMPLATE_ADDRESS_CANNOT_BE_EMPTY(6005, "邮件模板地址不能为空"),
    THE_EMAIL_NOTIFICATION_POINT_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(6006, "邮件通知点不存在或已删除"),
    THE_BULLETIN_BOARD_INFO_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(6007, "公告板信息不存在或已删除"),
    FAILED_TO_SEND_AN_EMAIL(6008, "邮件发送失败"),


    /*-------------------------外部集成服务 end----------------------------------------*/


    /*---------------------------权限收放 begin------------------------------------------*/

    ROLE_UPDATE_IS_NOT_EXISTS(7001,"更新的角色不存在,无法执行操作"),
    ROLE_DELETE_IS_NOT_EXISTS(7002,"删除的角色不存在,无法执行操作"),
    SYS_COMPANY_IS_NOT_EXISTS(7003,"所属公司不存在,无法执行操作"),
    SYS_COMPANY_IS_NOT_GROUP_COMPANY(7004,"所属公司不能添加部门,无法执行操作"),
    MANAGER_IS_NULL(7005,"部门负责人不存在,无法执行操作"),
    SYS_DEPARTMENT_IS_NOT_EXISTS(7006,"所属部门不存在,无法执行操作"),
    ROLE_NAME_IS_EXISTS(7007,"角色名已经存在,无法执行操作"),
    SYS_JOB_IS_NOT_EXISTS(7008,"职位不存在,无法执行操作"),
    SYS_POSITION_NAME_IS_EXISTS(7009,"岗位名称已存在,无法执行操作"),
    SYS_POSITION_IS_NOT_EXISTS(7010,"岗位记录不存在,无法执行操作"),
    SYS_JOB_TYPE_NAME_IS_EXISTS(7011,"职位类别名已经存在,无法执行操作"),
    SYS_JOB_TYPE_IS_NOT_EXISTS(7012,"职位类别不存在,无法执行操作"),
    SYS_JOB_NAME_IS_EXISTS(7013,"职位名已经存在,无法执行操作"),
    NAME_ALREADY_EXISTS(7014,"名称已经存在,无法执行操作"),
    PARENT_COMPANY_IS_NOT_EXISTS(7015,"上级组织不存在,无法执行操作"),
    PARENT_COMPANY_IS_NOT_GROUP_COMPANY(7016,"上级组织不能是公司,无法执行操作"),
    PARENT_COMPANY_IS_OWNER_DEPARTMENT(7017,"父公司下面有部门，无法维护子公司"),
    USER_NAME_AND_USER_ID_IS_ALL_NULL(7018,"用户名和用户id不能同时为空"),
    USER_IS_NOT_EMPLOYEE_ID(7019,"用户未绑定员工id"),
    EMPLOYEE_MANAGER_IS_NOT_EXIST(7020,"员工的直接上级不存在"),
    EMPLOYEE_IS_NOT_EXIST(7021,"员工不存在"),
    SYS_DEPARTMENT_IS_EXIST(7022,"部门编码已经存在"),
    SYS_SYSUSER_IS_EXIST(7023,"用户名已经存在"),
    SYS_PARENT_DEPARTMENT_IS_EXIST(7024,"上线部门不存在"),
    SYS_EMPLOYEE_IS_NOT_EXISTS(7025,"员工不存在,无法执行操作"),
    SYS_REGION_PARENT_IS_NOT_EXISTS(7026,"上级行政区域不存在,无法执行操作"),
    SYS_REGION_TYPE_IS_INVALID(7027,"行政区域类型错误,无法执行操作"),
    SYS_REGION_NAME_IS_NOT_EXISTS(7028,"同一父类子级名称不允许重复,无法执行操作"),
    REGION_EXISTS_CHILD(7029,"行政区域存在下级节点,无法删除"),
    EMPLOYEE_IS_Bind(7030,"员工已被其他用户绑定了"),
    USER_ROLE_IS_NOT_EXISTS(7031,"用户未绑定角色"),
    USER_ROLE_BIND_ERROR(7032,"用户绑定角色有误"),
    PERMISSION_GROUP_IS_NOT_EXISTS(7033,"权限组不存在"),
    PERMISSION_IS_NOT_EXISTS(7034,"权限不存在"),
    PERMISSION_GROUP_IS_NOT_EMPTY(7035,"系统权限组列表不能为空"),
    PERMISSION_IS_NOT_EMPTY(7036,"系统权限列表不能为空"),
    DEPARTMENT_ID_IS_NOT_EXISTS(7037,"departmentId不存在"),
    DEPARTMENT_MANAGER_IS_NOT_EXISTS(7038,"部门负责人为空"),
    USER_IS_NOT_EXISTS(7039,"未查询到用户信息"),
    USER_OLD_PASSWORD_IS_ERROR(7040,"用户旧的密码不正确"),
    USER_DEPARTMENT_MANAGER_SELECT_FAILD(7041,"用户部门经理查询失败"),







    /*---------------------------权限收放 end------------------------------------------*/

    /*---------------------------认证 begin------------------------------------------*/

    ACCESS_TOKEN_INVALID(401, "access_token无效"),
    REFRESH_TOKEN_INVALID(401, "refresh_token无效"),
    UNAUTHORIZED(401, "无权访问(未授权)"),
    AUTHORIZATION_EXPIRES(401, "授权过期, 请求重新登录"),
    NOT_LOGGED_IN(401, "未登录，或者授权过期"),
    ANONYMOUS_SUBJECT_UNAUTHORIZED(401, "无权访问:当前用户是匿名用户，请先登录"),
    AUTHENTICATION_FAILED(401, "身份验证未通过"),
    MISSING_TOKEN_AUTHENTICATION_FAILED(401, "缺失令牌,鉴权失败"),

    GET_USE_RPERM_BY_USER_ID_OR_USER_NAME_FAILED(8000, "获取用户权限失败"),

    SUBJECT_UNAUTHORIZED(8001, "无权访问:当前用户没有此请求所需权限"),
    USER_NAME_OR_PASSWORD_ERRORS_GREATER_THAN_5_TIMES(8002, "用户名或密码错误次数大于5次,账户已锁定, 请10分钟后再次访问"),
    ACCOUNT_AUTHORIZATION_EXPIRED(8003, "账号授权过期"),
    ACCOUNT_LOGIN_IS_PROHIBITED(8004, "账号禁止登陆"),
    THE_ACCOUNT_DOES_NOT_EXIST_PLEASE_CHANGE_THE_ACCOUNT_TO_LOGIN(8005, "账号不存在，请更换账号登录"),
    PROHIBIT_THE_LOGIN(8006, "禁止登录"),
    NO_PERMISSIONS(8007, "暂无权限， 请联系管理员"),
    THE_ROLE_IDS_DOES_NOT_EXIST_PLEASE_CHANGE_THE_ACCOUNT_TO_LOGIN(8008, "角色不存在，请联系管理员分配角色"),
    ACCOUNT_IS_EMPTY(8009, "账号为空"),
    ACCOUNT_AUTOMATIC_LOGOUT(8010, "账号已自动退出登录，无需再次退出登录"),
    ACCOUNT_DOES_NOT_EXIST(8011, "账号不存在"),
    WRONG_ACCOUNT_OR_PASSWORD(8012, "账号或密码错误"),
    VERIFICATION_CODE_OUT_OF_DATE_PLEASE_RETRIEVE_IT_AGAIN(8013, "验证码过时, 请重新获取"),
    THE_VERIFICATION_CODE_IS_INCORRECT(8014, "验证码不正确 , 请重新输入"),
    THE_VERIFICATION_CODE_IS_EMPTY(8015, "验证码为空, 请重新输入"),






    /*---------------------------认证 end------------------------------------------*/

    /*---------------------------biz begin------------------------------------------*/

    API_COMPANY_SELECT_FAIL(9001, "API获取公司为空"),
    API_JUDGE_GROUP_COMPANY_FAIL(9002, "API获取公司为空"),
    API_REQUEST_FLOWVO_FAIL(9003, "API获取表单流转失败"),
    API_ROLE_SELECT_FAIL(9004, "API查询角色失败"),
    API_USER_SELECT_FAIL(9005, "API查询用户失败"),
    // 5400：询价单
    INQUIRY_REQUEST_CREATE_FAIL(5401, "询价单创建失败"),
    INQUIRY_REQUEST_SELECT_FAIL(5402, "询价单查询失败"),
    INQUIRY_VENDOR_SELECT_FAIL(5403, "询价单供应商查询失败"),
    INQUIRY_VENDOR_MATERIAL_SELECT_FAIL(5404, "询价单供应商报价查询失败"),
    INQUIRY_VENDOR_MATERIAL_PRICE_NULL(5405, "询价单供应商报价为空"),
    INQUIRY_QUANTITY_ALLOTTED_ERROR(5505, "询价分配数量异常"),
    INQUIRY_QUANTITY_ALLOTTED_PROP_ERROR(5506, "询价分配比例应为100%"),

    // 5500：招标单
    BID_REQUEST_CREATE_FAIL(5501, "招标单创建失败"),
    BID_REQUEST_SELECT_FAIL(5502, "招标单查询失败"),
    BID_VENDOR_SELECT_FAIL(5503, "招标单供应商查询失败"),
    BID_VENDOR_UPDATE_FAIL(5503, "招标单供应商修改失败"),
    BID_VENDOR_INSERT_FAIL(5503, "招标单供应商新增失败"),
    BID_VENDOR_DETAIL_SELECT_FAIL(5503, "招标单供应商文件明细查询失败"),
    BID_VENDOR_USER_SELECT_FAIL(5503, "用户未绑定供应商"),
    BID_SPECIALIST_SELECT_FAIL(5504, "招标单专家查询失败"),
    BID_SPECIALIST_GROUP_SELECT_FAIL(5504, "招标单专家组长查询失败"),
    BID_OPE_DATE_FAIL(5505, "还未到招标单开标时间，请稍等！"),
    BID_QUANTITY_ALLOTTED_ERROR(5505, "招标分配数量异常"),
    BID_QUANTITY_ALLOTTED_PROP_ERROR(5506, "招标分配比例应为100%"),
    BID_DETAIL_SELECT_ERROR(5507, "招标明细查询失败"),

    // 5600：竞价单
    PAID_REQUEST_CREATE_FAIL(5601, "竞价单创建失败"),
    PAID_REQUEST_SELECT_FAIL(5602, "竞价单查询失败"),
    PAID_VENDOR_SELECT_FAIL(5603, "竞价单供应商查询失败"),
    PAID_MATERIAL_SELECT_FAIL(5605, "竞价单物品查询失败"),
    PAID_PRICE_ERROR(5604, "竞价价格异常"),
    PAID_QUANTITY_ALLOTTED_ERROR(5605, "竞价分配数量异常"),
    PAID_QUANTITY_ALLOTTED_PROP_ERROR(5606, "竞价分配比例应为100%"),

    // 5700：表单
    REQUEST_CREATE_FAIL(5701, "表单创建失败"),
    REQUEST_SELECT_FAIL(5702, "表单查询失败"),
    VALUE_CANNOT_BE_EMPTY(5704, "数据不能为空"),

    // 5800：现场调查
    SITELNS_SELECT_FAIL(5802, "现场调查查询失败"),

    SYS_SYSUSER_EMAIL_IS_EXIST(9025,"邮箱已经存在"),

    //5900:项目
    PROJECT_DELETE_FAILD(5901,"项目下存在招标/竞价单，删除失败");

    /*---------------------------biz end------------------------------------------*/





    ;

    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

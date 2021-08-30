/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : web_base

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 26/03/2020 16:13:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for scm_vendor
-- ----------------------------
-- DROP TABLE IF EXISTS `scm_vendor`;
CREATE TABLE IF NOT EXISTS `scm_vendor`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT 'requestId',
  `request_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `legal_representative` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法定代表人',
  `registered_capital` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注册资本',
  `icrn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工商注册号',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业类型',
  `uscc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `registration_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登记状态',
  `orz_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构代码',
  `taxpay_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属地区',
  `industry` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属行业',
  `registered_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注册地址',
  `business_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '经营范围',
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `contact_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人职位',
  `contact_mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人手机号',
  `contact_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人邮箱',
  `contact_identity_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人证件类型',
  `contact_identity_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人证件号码',
  `identity_front` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件正面照片',
  `identity_reverse` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件反面照片',
  `business_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商业模式',
  `agent_brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代理品牌',
  `suply_category_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供货品类备注',
  `main_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主营品类',
  `main_customer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主要客户',
  `factory_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂房面积',
  `factory_nature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂房性质',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_category`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '品类ID',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品类名称',
  `supply_organization` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应组织',
  `valid_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '有效状态',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(32) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商品类' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_certificate`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件类型',
  `attachment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `expire_time` bigint(20) NULL DEFAULT NULL COMMENT '有效期',
  `upload_time` bigint(20) NULL DEFAULT NULL COMMENT '上传时间',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商证书' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_kpi`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT 'requestId',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `scoring_formula` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计分方式',
  `responsible_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `vendor_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商描述',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '绩效考核表名称',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察结果',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商绩效考核表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_kpi_detail`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_kpi_id` bigint(20) NULL DEFAULT NULL COMMENT '绩效考核表ID',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考核指标',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考核子指标',
  `balance` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权重',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察情况',
  `score_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '得分范围',
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评分',
  `final_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最终得分',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '等级',
  `ins_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察描述',
  `if_force` int(2) NULL DEFAULT NULL COMMENT '强制',
  `if_attachment` int(2) NULL DEFAULT NULL COMMENT '是否需要添加附件',
  `attachment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商绩效考核表明细' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_kpi_detail_user`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_kpi_detail_id` bigint(20) NULL DEFAULT NULL COMMENT '绩效考核明细表ID',
  `vendor_kpi_user_id` bigint(20) NULL DEFAULT NULL COMMENT '考核人员表ID',
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评分',
  `ins_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察描述',
  `attachment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商绩效人员评价表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_kpi_user`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_kpi_id` bigint(20) NULL DEFAULT NULL COMMENT '绩效表ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账户',
  `user_dept` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户部门',
  `site_ins_kpi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考核指标',
  `if_leader` int(2) NULL DEFAULT NULL COMMENT '考察组长',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(255) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商绩效人员表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_qualify_review`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT 'requestId',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `scoring_formula` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计分方式',
  `responsible_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `vendor_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商描述',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合格评审表名称',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察结果',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(255) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商合格评审表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_qualify_review_detail`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_qualify_review_id` bigint(20) NULL DEFAULT NULL COMMENT '合格评审表ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结果',
  `if_force` int(2) NULL DEFAULT NULL COMMENT '强制',
  `if_attachment` int(2) NULL DEFAULT NULL COMMENT '是否需要添加附件',
  `attachment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商合格评审表明细' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_question`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT 'requestId',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `vendor_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商描述',
  `response_expire_time` bigint(20) NULL DEFAULT NULL COMMENT '供应商响应时间',
  `responsible_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调查表名称',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调查结果',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(255) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商电子调查表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_question_bank`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题类型',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题名称',
  `balance` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权重',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题内容',
  `score_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '得分范围',
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评分',
  `final_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最终得分',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '等级',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结果',
  `response` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题答复',
  `ins_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题描述',
  `if_pass` int(2) NULL DEFAULT NULL COMMENT '是否通过',
  `if_force` int(2) NULL DEFAULT NULL COMMENT '是否强制',
  `if_attachment` int(2) NULL DEFAULT NULL COMMENT '是否需要上传附件',
  `attachment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板题库表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_question_detail`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_question_id` bigint(20) NULL DEFAULT NULL COMMENT '电子调查表ID',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调查指标',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调查子指标',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `response` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '答复',
  `attachment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `if_force` int(2) NULL DEFAULT NULL COMMENT '是否强制',
  `if_pass` int(2) NULL DEFAULT NULL COMMENT '是否通过',
  `if_attachment` int(2) NULL DEFAULT NULL COMMENT '是否需要上传附件',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商电子调查表明细' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_request`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT 'requestId',
  `request_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据名称',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `legal_representative` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法定代表人',
  `registered_capital` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注册资本',
  `icrn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工商注册号',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业类型',
  `uscc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `registration_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登记状态',
  `orz_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构代码',
  `taxpay_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属地区',
  `industry` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属行业',
  `registered_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注册地址',
  `business_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '经营范围',
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `contact_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人职位',
  `contact_mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人手机号',
  `contact_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人邮箱',
  `contact_identity_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人证件类型',
  `contact_identity_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人证件号码',
  `identity_front` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件正面照片',
  `identity_reverse` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件反面照片',
  `business_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商业模式',
  `agent_brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代理品牌',
  `suply_category_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供货品类备注',
  `main_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主营品类',
  `main_customer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主要客户',
  `factory_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂房面积',
  `factory_nature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂房性质',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商注册单' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_request_category`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_request_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '品类ID',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品类名称',
  `
supply_organization` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应组织',
  `valid_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '有效状态',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(32) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商注册单品类' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_user`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(255) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_request_certificate`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_request_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件类型',
  `attachment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `expire_time` bigint(20) NULL DEFAULT NULL COMMENT '有效期',
  `upload_time` bigint(20) NULL DEFAULT NULL COMMENT '上传时间',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商注册单证书' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_site_ins`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT 'requestId',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `scoring_formula` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计分方式',
  `responsible_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `vendor_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商描述',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察表名称',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察结果',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(255) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商现场考察表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_site_ins_detail`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_site_ins_id` bigint(20) NULL DEFAULT NULL COMMENT '现场评审表ID',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考核指标',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考核子指标',
  `balance` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权重',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察情况',
  `score_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '得分范围',
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评分',
  `final_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最终得分',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '等级',
  `ins_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察描述',
  `if_force` int(2) NULL DEFAULT NULL COMMENT '强制',
  `if_attachment` int(2) NULL DEFAULT NULL COMMENT '是否需要附件',
  `attachment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商现场考察表明细' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_site_ins_detail_user`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_site_ins_detail_id` bigint(20) NULL DEFAULT NULL COMMENT '现场评审明细表ID',
  `vendor_site_ins_user_id` bigint(20) NULL DEFAULT NULL COMMENT '考察人员表ID',
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评分',
  `ins_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考察描述',
  `attachment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商现场考察人员评价表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_site_ins_user`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `vendor_site_ins_id` bigint(20) NULL DEFAULT NULL COMMENT '现场考察ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账户',
  `user_dept` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户部门',
  `site_ins_kpi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考核指标',
  `if_leader` int(2) NULL DEFAULT NULL COMMENT '考察组长',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(255) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商现场考察人员表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_template`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板类型',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `ins_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板描述',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(255) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板表' ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS `scm_vendor_template_qbank`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `template_id` bigint(20) NULL DEFAULT NULL COMMENT '模板ID',
  `question_bank_id` bigint(20) NULL DEFAULT NULL COMMENT '问题ID',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(255) NULL DEFAULT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板题库中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xdc_t_offer
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_offer`;
CREATE TABLE IF NOT EXISTS `xdc_t_offer`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '金额',
  `offer_time` bigint(20) NULL DEFAULT NULL COMMENT '出价时间',
  `offer_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出价人',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT '表单ID',
  `offer_goods` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出价物标识',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商ID',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '藐视',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出价记录' ROW_FORMAT = Dynamic;


























SET FOREIGN_KEY_CHECKS = 1;


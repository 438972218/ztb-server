/*
 Navicat Premium Data Transfer

 Source Server         : 10.20.54.133
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 10.20.54.133:3306
 Source Schema         : permission_engine

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 03/08/2021 17:17:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `short_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司/分部 简称',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司/分部全称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级组织',
  `website` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号（唯一性，手动输入）',
  `principal` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `principal_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人电话',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  `parent_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号（唯一性，手动输入）',
  `bank_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `bank_account_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户银行账号信息',
  `company_type` int(2) NULL DEFAULT NULL COMMENT '公司类型:(0:集团，1：公司)',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分部表(公司表)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  `short_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门简称',
  `full_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门全称',
  `company_id` bigint(20) NOT NULL COMMENT '所属公司id',
  `company_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属公司code',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级部门id',
  `parent_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级部门编号',
  `code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编号',
  `manager` bigint(20) NULL DEFAULT NULL COMMENT '部门负责人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `dictionary_chinese` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典中文名称',
  `dictionary_class` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类别',
  `meaning` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典的意思及描述',
  `numerical` int(11) NOT NULL COMMENT '字典的数值',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_employee
-- ----------------------------
DROP TABLE IF EXISTS `sys_employee`;
CREATE TABLE `sys_employee`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `manager_id` bigint(20) NULL DEFAULT NULL COMMENT '直接上级',
  `manager_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级员工code',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `employee_no` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工编号',
  `sex` int(2) NULL DEFAULT NULL COMMENT '性别',
  `position` bigint(20) NULL DEFAULT NULL COMMENT '所属岗位',
  `department_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department_id` bigint(20) NULL DEFAULT NULL COMMENT '所属部门',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态(在线、离职)',
  `office_address` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '办公地点',
  `speciality` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专长',
  `hobby` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '爱好',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `office_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '办公电话',
  `mail` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `office` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '办公室',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `short_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '职务简称',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '职务全称',
  `job_type_id` bigint(20) NOT NULL COMMENT '所属职务类别',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '职务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_job_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_type`;
CREATE TABLE `sys_job_type`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `short_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '职务类别简称',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '职务类别全称',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '职务类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父权限id',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限字符串',
  `level` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能的级别(\'menu\',\'button\')',
  `route_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `is_hide` int(2) NULL DEFAULT NULL COMMENT '是否隐藏  1:是 0：否',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  `ancestors` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_group`;
CREATE TABLE `sys_permission_group`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限组标识',
  `description` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限组说明',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission_group_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_group_permission`;
CREATE TABLE `sys_permission_group_permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `permission_group_id` bigint(20) NOT NULL COMMENT '权限组ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限组与权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `short_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位简称',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位全称',
  `job_id` bigint(20) NOT NULL COMMENT '所属职务',
  `position_duty` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '岗位职责',
  `position_qualifications` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任职资格',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_region
-- ----------------------------
DROP TABLE IF EXISTS `sys_region`;
CREATE TABLE `sys_region`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` bigint(20) NOT NULL COMMENT '父id',
  `ancestors` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '祖级列表',
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型（1：省，2：市，3：区）',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '行政区域表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `company_id` bigint(20) NOT NULL COMMENT '所属分部（公司）',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_permission_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_group`;
CREATE TABLE `sys_role_permission_group`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  `permission_group_id` bigint(20) NOT NULL COMMENT '权限组编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限组信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_security_settings
-- ----------------------------
DROP TABLE IF EXISTS `sys_security_settings`;
CREATE TABLE `sys_security_settings`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `password_min_length` int(2) NULL DEFAULT NULL COMMENT '密码最小长度',
  `password_fail_number_limit` int(2) NULL DEFAULT NULL COMMENT '密码失败次数限制',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '安全设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mail` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '帐号状态（0正常 1冻结）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` bigint(20) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `lock_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户是否被锁定(0:启用,1:锁定)',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(4) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` int(2) NOT NULL COMMENT '是否已经逻辑删除（0：未删除 1：已删除）',
  `employee_id` bigint(20) NULL DEFAULT NULL COMMENT '员工id',
  `source_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据来源类型，1- 系统添加, 2- ldap同步,默认是1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `created_time` bigint(20) NOT NULL COMMENT '创建时间',
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限组信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

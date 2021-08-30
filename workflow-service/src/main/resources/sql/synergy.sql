/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : synergy_engine

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 29/06/2021 15:19:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xdc_t_fields_that
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_fields_that`;
CREATE TABLE IF NOT EXISTS `xdc_t_fields_that`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `field_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段名',
  `ldap_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ldap 字段名',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型（1：组织，2：部门，3：人员）',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '对接字段说明' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_fields_that
-- ----------------------------

-- ----------------------------
-- Table structure for xdc_t_ldap
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_ldap`;
CREATE TABLE IF NOT EXISTS `xdc_t_ldap`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型（AD：1，OpenLdap: 2, SUN ONE :3 ）',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Ldap 地址',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `domain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同步域',
  `base_ou` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '顶级组织单元-部门',
  `sync_organization` tinyint(1) NULL DEFAULT NULL COMMENT '是否同步组织机构',
  `sync_person` tinyint(1) NULL DEFAULT NULL COMMENT '是否同步人员',
  `conditions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同步条件',
  `frequency` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同步频率 格式：秒,分,时,天,月,周,年， 没有则-1 补位',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'ldap信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_ldap
-- ----------------------------

-- ----------------------------
-- Table structure for xdc_t_mail_delivery
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_mail_delivery`;
CREATE TABLE IF NOT EXISTS `xdc_t_mail_delivery`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `sending_point` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送节点',
  `mail_subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件主题',
  `mail_from` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送人',
  `mail_to` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人',
  `mail_cc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抄送人',
  `mail_bcc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密送人',
  `mail_reply` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件发送点信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_mail_delivery
-- ----------------------------
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (1, '1', '注册成功确认', NULL, NULL, NULL, NULL, NULL, 1629863427000, NULL, 'system', NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (2, '2', '供应商账号激活', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (3, '3', '密码到期提醒', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (4, '4', '重置密码', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (5, '5', '密码更改成功', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (6, '6', '项目组成员新增', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (7, '7', '招标审批提醒', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (8, '8', '招标邀请发送通知', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (9, '9', '招标申请批复通知', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (10, '10', '招标新消息通知', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (11, '11', '报价提交通知', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (12, '12', '新竞价消息', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (13, '13', '竞价邀请', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (14, '14', '竞价终止', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (15, '15', '供应商注册信息变更', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (16, '16', '供应商分类变更', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (17, '17', '流程进度提醒', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (18, '18', '招标竞价批复到期提醒', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (19, '19', '新招标竞价审批提醒', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (20, '20', '供应商撤回RFQ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (21, '21', '供应商拒绝回应RFQ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_delivery` (`id`, `sending_point`, `mail_subject`, `mail_from`, `mail_to`, `mail_cc`, `mail_bcc`, `mail_reply`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (22, '22', 'RFQ到期提醒', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for xdc_t_mail_relation
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_mail_relation`;
CREATE TABLE IF NOT EXISTS `xdc_t_mail_relation`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `delivery_id` bigint(20) NULL DEFAULT NULL COMMENT '发送点ID',
  `template_id` bigint(20) NULL DEFAULT NULL COMMENT '模板ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件发送点-签名-模板关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_mail_relation
-- ----------------------------
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (1, 1, 2);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (2, 2, 3);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (3, 3, 4);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (4, 4, 5);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (5, 5, 6);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (6, 6, 7);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (7, 7, 8);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (8, 8, 9);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (9, 9, 10);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (10, 10, 11);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (11, 11, 12);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (12, 12, 13);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (13, 13, 14);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (14, 14, 15);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (15, 15, 16);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (16, 16, 17);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (17, 17, 18);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (18, 18, 19);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (19, 19, 20);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (20, 20, 21);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (21, 21, 22);
INSERT IGNORE INTO `xdc_t_mail_relation` (`id`, `delivery_id`, `template_id`) VALUES (22, 22, 23);

-- ----------------------------
-- Table structure for xdc_t_mail_template
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_mail_template`;
CREATE TABLE IF NOT EXISTS `xdc_t_mail_template`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '模板类型(-1->注册模板, 1->节点通知模板, 2->签名模板)',
  `template` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板',
  `default_value` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认值',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE COMMENT '类型索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件模板信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_mail_template
-- ----------------------------
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (1, '签名模板', 2, '<div class=\"template-signature\"><p style=\"margin: 30px 0 0px 20px;\">需要任何帮助或说明，请拨打我们的热线支持电话：</p><p style=\"margin: 5px 20px;\">${telephone}</p><p style=\"margin: 30px 0 0px 20px;\">或发送邮件至</p><p style=\"margin: 5px 20px;\">${email}</p></div>', '{\"telephone\":\"0510-85318523\",\"email\":\"supportchina@bravosolution.com\"}', 1629863427000, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (2, '注册成功确认', -1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的${name}:</p><p style=\"margin: 20px 0 20px 20px\">欢迎来到<a href=\"${homepage}\">${homepage}!</a></p><p style=\"margin: 20px 0 40px 20px\">您已成功注册，并可在<a href=\"${homepage}\">${homepage}</a>上进行商谈项目操作。</p><p style=\"margin: 5px 0 10px 20px;\">您的用户名：${userName}</p><p style=\"margin: 5px 0 10px 20px;\">您的密码：${password}</p></div>', NULL, 1629863427000, NULL, 'system', NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (3, '供应商账号激活', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的供应商:</p><p style=\"margin: 20px 0 20px 20px\">您的账户已在${platformName}站点上被激活。</p><p style=\"margin: 20px 0 20px 20px\">您现在可以使用您的用户名和密码来访问此平台上的所有可用区域。<span>您选择的用户名是：${userName}, 站点地址是：${homepage}</span></p><p style=\"margin: 20px 0 20px 20px\">备注：您的平台访问证书可以通过登录界面的用户名和密码修复区域获得。</p></div>', NULL, 1629863427000, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (4, '密码到期提醒', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的${name}:</p><p style=\"margin: 20px 0 0 20px\">您的${platformName}密码即将于 ${expireDate} 过期</p><p style=\"margin: 5px 20px;\">请点击如下链接设置新密码：${url}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (5, '重置密码', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">亲爱的用户:</p><p style=\"margin: 20px 0 20px 20px\">您已经请求了一个新密码来访问${platformName}在${homepage}</p><p style=\"margin: 20px 0 20px 20px\">下面的链接允许您重置密码。该链接只能使用一次，并且在有限的时间内有效(${limit}后过期)。</p><p style=\"margin: 20px 0 20px 20px\">点击下面的链接来定义一个新密码:${url}</p><p style=\"margin: 20px 0 20px 20px\">在使用这个链接之后，可以删除这封邮件，因为这个链接只能使用一次。如果您的电子邮件服务器使用的链接保护系统在您之前访问该链接，建议您将该链接从该电子邮件复制并粘贴到浏览器地址栏。</p><p style=\"margin: 20px 0 20px 20px\">注意:如果您没有请求此密码提醒，请忽略此消息。您的帐户将不会发生任何变化，您将能够像往常一样使用登录凭据登录。</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (6, '密码更改成功', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的用户:</p><p style=\"margin: 20px 0 20px 20px\">请注意，您于${time}成功修改了用户名及密码。</p><p style=\"margin: 20px 0 20px 20px\">如果您未作以上变更，或怀疑您的账户被恶意篡改，请尽快与采购方联系。</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (7, '项目组成员新增', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的${name}:</p><p style=\"margin: 20px 0 20px 20px\">在下列项目中你已经被添加为新项目组成员.</p><p style=\"margin: 20px 0 20px 20px\">- ${projectCode} - ${projectName}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (8, '招标审批提醒', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的用户:</p><p style=\"margin: 20px 0 20px 20px\">以下新提交的询价发布申请等待您的批准：</p><p style=\"margin: 20px 0 20px 20px\">类型：${sheetType}</p><p style=\"margin: 20px 0 20px 20px\">代码：${code}</p><p style=\"margin: 20px 0 20px 20px\">标题：${title}</p><p style=\"margin: 20px 0 0 20px\">请通过以下链接登陆${platformName}回复您的审批意见。</p><p style=\"margin: 5px 20px;\">${homepage}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (9, '招标邀请发送通知', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的供应商:</p><p style=\"margin: 20px 0 20px 20px\">诚邀您参与${platformName}上的以下询价：</p><p style=\"margin: 20px 0 20px 20px\">类型：${sheetType}</p><p style=\"margin: 20px 0 20px 20px\">代码：${code}</p><p style=\"margin: 20px 0 40px 20px\">标题：${title}</p><p style=\"margin: 20px 0 0 20px\">此次报价截止时间为：</p><p style=\"margin: 20px 0 40px 20px;\">日期时间： ${bidEndTime}</p><p style=\"margin: 20px 0 20px 20px;\">如需查看商谈详情，请按以下步骤操作：</p><p style=\"margin: 20px 0 20px 20px;\">- 连接至${platformName}</p><p style=\"margin: 20px 0 20px 20px;\">- 输入您的用户名和密码进入平台</p><p style=\"margin: 20px 0 20px 20px;\">延迟时间将导致无法回复，请务必于截止时间前提交报价。</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (10, '招标申请批复通知', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的用户:</p><p style=\"margin: 20px 0 20px 20px\">请注意，${platformName}上关于以下询价的发布申请已获批准：</p><p style=\"margin: 20px 0 20px 20px\">类型：${sheetType}</p><p style=\"margin: 20px 0 20px 20px\">代码：${code}</p><p style=\"margin: 20px 0 20px 20px\">标题：${title}</p><p style=\"margin: 20px 0 20px 20px\">部门：${department]</p><p style=\"margin: 20px 0 0 20px\">相关批复意见如下：</p><p style=\"margin: 5px 20px;\">${remark}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (11, '招标新消息通知', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的用户:</p><p style=\"margin: 20px 0 20px 20px\">${platformName}上已发布以下询价的最新消息：</p><p style=\"margin: 20px 0 20px 20px\">消息来自：${vendorName}</p><p style=\"margin: 20px 0 20px 20px\">类型：${sheetType}</p><p style=\"margin: 20px 0 20px 20px\">代码：${code}</p><p style=\"margin: 20px 0 40px 20px\">标题：${title}</p><p style=\"margin: 20px 0 20px 20px\">查看详情请点击以下链接，并输入您的用户名和密码：</p><p style=\"margin: 20px 0 40px 20px\">${url}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (12, '报价提交通知', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的用户:</p><p style=\"margin: 20px 0 20px 20px\">供应商\'${vendorName}\'在${platformName}上回复了以下报价：</p><p style=\"margin: 20px 0 20px 20px\">类型：${sheetType}</p><p style=\"margin: 20px 0 20px 20px\">代码：${code}</p><p style=\"margin: 20px 0 40px 20px\">标题：${title}</p><p style=\"margin: 20px 0 20px 20px\">预设的报价截止时间为：</p><p style=\"margin: 20px 0 40px 20px\">截止日期：${bidEndTime}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (13, '新竞价消息', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">新的竞价 ${code} 已于${platformName}上发布</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (14, '竞价邀请', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的供应商:</p><p style=\"margin: 20px 0 20px 20px\">诚邀您参与在${homepage}上的以下竞价：</p><p style=\"margin: 20px 0 20px 20px\">竞价代码：${code}</p><p style=\"margin: 20px 0 40px 20px\">竞价名称：${title}</p><p style=\"margin: 20px 0 20px 20px\">相关项目信息：</p><p style=\"margin: 20px 0 40px 20px\">项目代码：${projectCode}</p><p style=\"margin: 20px 0 20px 20px\">竞价安排如下：</p><p style=\"margin: 20px 0 40px 20px\">竞价开始时间：${offerStartTime}</p><p style=\"margin: 20px 0 20px 20px\">查看详情，请点击以下链接：</p><p style=\"margin: 20px 0 20px 20px\">${url}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (15, '竞价终止', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的供应商:</p><p style=\"margin: 20px 0 20px 20px\">这里通知您，决议将要终止如下的竞价：</p><p style=\"margin: 20px 0 20px 20px\">竞价代码：${code}</p><p style=\"margin: 20px 0 40px 20px\">竞价名称：${title}</p><p style=\"margin: 20px 0 20px 20px\">供应商投标已被终止。</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (16, '供应商注册信息变更', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的用户:</p><p style=\"margin: 20px 0 20px 20px\">以下受监控的供应商概况问题\'${vendorName}\'已经修改于${name}在${platformName}</p><p style=\"margin: 20px 0 20px 20px\">参数词典: \"${fieldName}\" 已由: \"${oldValue}\" 修改为: \"${newValue}\"</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (17, '供应商分类变更', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的${name}:</p><p style=\"margin: 20px 0 20px 20px\">以下供应商更改了在${platformName}上的供应商分类信息：</p><p style=\"margin: 20px 0 20px 20px\">供应商: ${vendorName}</p><p style=\"margin: 20px 0 20px 20px\">城市: ${city}</p><p style=\"margin: 20px 0 20px 20px\">国家: ${country}</p><p style=\"margin: 20px 0 20px 20px\">联系人: ${contact}</p><p style=\"margin: 20px 0 20px 20px\">邮件地址: ${enterpriseMail}</p><p style=\"margin: 20px 0 20px 20px\">供应商品类变更如下：</p><p style=\"margin: 20px 0 20px 20px\">选定类别 ${itemCode} - 服务类${itemName}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (18, '流程进度提醒', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的${name}:</p><p style=\"margin: 20px 0 20px 20px\">此封邮件是为了通知你以下任务运行状态${platformName}</p><p style=\"margin: 20px 0 20px 20px\">任务名称: ${taskName}，包括完成授权前审批</p><p style=\"margin: 20px 0 20px 20px\">任务说明: ${taskDescription}</p><p style=\"margin: 20px 0 20px 20px\">任务所有者: ${taskOwner}</p><p style=\"margin: 20px 0 40px 20px\">实际结束日期: ${actualEndDate}</p><p style=\"margin: 20px 0 20px 20px\">进入项目详情点击以下链接输入你的用户名和密码:</p><p style=\"margin: 20px 0 0 20px\">${url}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (19, '招标竞价批复到期提醒', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的${name}:</p><p style=\"margin: 20px 0 20px 20px\">${createName}提交的审核申请已到截止日期，请您完成批复：</p><p style=\"margin: 20px 0 20px 20px\">标题: ${title}，包括完成授权前审批</p><p style=\"margin: 20px 0 20px 20px\">说明: ${description}</p><p style=\"margin: 20px 0 20px 20px\">截止日期: ${expireDate}</p><p style=\"margin: 20px 0 20px 20px\">提交日期: ${submitDate}</p><p style=\"margin: 20px 0 20px 20px\">任务名称: ${taskName}，包括完成授权前审批</p><p style=\"margin: 20px 0 20px 20px\">项目代码: ${code}</p><p style=\"margin: 20px 0 40px 20px\">项目标题: ${projectTitle}</p><p style=\"margin: 20px 0 20px 20px\">进入项目详情点击以下链接输入你的用户名和密码:</p><p style=\"margin: 20px 0 20px 20px\">${url}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (20, '新招标竞价审批提醒', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的${name}:</p><p style=\"margin: 20px 0 20px 20px\">${createName}提交了一项新的审核申请，请您完成批复</p><p style=\"margin: 20px 0 20px 20px\">标题: ${title}，包括完成授权前审批</p><p style=\"margin: 20px 0 20px 20px\">说明: ${description}</p><p style=\"margin: 20px 0 20px 20px\">预期批准日期: ${expectedApprovalDate}</p><p style=\"margin: 20px 0 20px 20px\">批准类型: ${approvalType}</p><p style=\"margin: 20px 0 20px 20px\">批准顺序: ${approvalSequence}</p><p style=\"margin: 20px 0 20px 20px\">批准规则: ${approvalRule}</p><p style=\"margin: 20px 0 20px 20px\">提交日期: ${submitDate}</p><p style=\"margin: 20px 0 20px 20px\">任务名称: ${title}，包括完成授权前审批</p><p style=\"margin: 20px 0 20px 20px\">项目代码: ${code}</p><p style=\"margin: 20px 0 40px 20px\">项目标题: ${projectTitle}</p><p style=\"margin: 20px 0 20px 20px\">您可访问下方链接查看详情</p><p style=\"margin: 20px 0 20px 20px\">${url}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (21, '供应商撤回RFQ', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的用户:</p><p style=\"margin: 20px 0 20px 20px\">供应商\'${vendorName}\'已撤回其对以下${sheetType}${platformName}提交的响应</p><p style=\"margin: 20px 0 20px 20px\">代码: ${code}</p><p style=\"margin: 20px 0 20px 20px\">标题: ${title}</p><p style=\"margin: 20px 0 20px 20px\">截止日期: ${bidEndTime}</p><p style=\"margin: 20px 0 20px 20px\">项目代码: ${projectCode}</p><p style=\"margin: 20px 0 20px 20px\">项目名称: ${projectName}</p><p style=\"margin: 20px 0 20px 20px\">这并不妨碍供应商在截止日期前重新提交他们的响应</p><p style=\"margin: 20px 0 20px 20px\">要访问${sheetType}，请单击以下链接并输入您的用户名和密码:</p><p style=\"margin: 20px 0 20px 20px\">${url}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (22, '供应商拒绝回应RFQ', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的用户:</p><p style=\"margin: 20px 0 20px 20px\">在${platformName}上，供应商\'${vendorName}\'谢绝回应下列${sheetType}</p><p style=\"margin: 20px 0 20px 20px\">代码: ${code}</p><p style=\"margin: 20px 0 20px 20px\">标题: ${title}</p><p style=\"margin: 20px 0 20px 20px\">截止日期: ${bidEndTime}</p><p style=\"margin: 20px 0 20px 20px\">拒回应原因: ${rejectReason}</p><p style=\"margin: 20px 0 20px 20px\">项目名称: ${projectName}</p><p style=\"margin: 20px 0 20px 20px\">在${platformName}上可以通过项目\'${projectName}\'访问RFQ - ${sheetType}，请点击下面的链接，输入用户名和密码，然后进入项目：</p><p style=\"margin: 20px 0 20px 20px\">${url}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT IGNORE INTO `xdc_t_mail_template` (`id`, `name`, `type`, `template`, `default_value`, `created_time`, `updated_time`, `created_user`, `updated_user`, `description`) VALUES (23, 'RFQ到期提醒', 1, '<div class=\"template-body\"><p style=\"margin: 20px 0 40px 20px\">尊敬的用户:</p><p style=\"margin: 20px 0 20px 20px\">此邮件提醒您以下商谈将到预设的完成时间。</p><p style=\"margin: 20px 0 20px 20px\">商谈类型: ${sheetType}</p><p style=\"margin: 20px 0 20px 20px\">代码: ${code}</p><p style=\"margin: 20px 0 20px 20px\">标题: ${title}</p><p style=\"margin: 20px 0 20px 20px\">项目代码: ${projectCode}</p><p style=\"margin: 20px 0 40px 20px\">项目名称: ${projectName}</p><p style=\"margin: 20px 0 20px 20px\">预设完成时间为: ${projectCode}</p><p style=\"margin: 20px 0 40px 20px\">关闭日期：${closeTime}</p><p style=\"margin: 20px 0 20px 20px\">更多信息请登录${platformName}</p><p style=\"margin: 20px 0 20px 20px\">${url}</p></div>', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for xdc_t_notification_history
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_notification_history`;
CREATE TABLE IF NOT EXISTS `xdc_t_notification_history`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '通知类型，1：邮件，2：微信',
  `message_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息ID',
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件主题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知内容',
  `mail_from` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送人',
  `mail_to` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人',
  `mail_cc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抄送人',
  `mail_bcc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密送人',
  `mail_reply` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知历史' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xdc_t_notification_relation
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_notification_relation`;
CREATE TABLE IF NOT EXISTS `xdc_t_notification_relation`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT '表单ID',
  `flow_id` bigint(20) NULL DEFAULT NULL COMMENT '流转ID',
  `history_id` bigint(20) NULL DEFAULT NULL COMMENT '通知历史ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_request_id`(`request_id`) USING BTREE COMMENT '表单ID索引',
  INDEX `idx_flow_id`(`flow_id`) USING BTREE COMMENT '流转ID索引',
  UNIQUE INDEX `idx_history_id`(`history_id`) USING BTREE COMMENT '历史ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知历史关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xdc_t_bulletin_board
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_bulletin_board`;
CREATE TABLE IF NOT EXISTS `xdc_t_bulletin_board`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '公告类型',
  `bulletin_time` bigint(20) NULL DEFAULT NULL COMMENT '公告时间',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告牌信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xdc_t_discuss_group
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_discuss_group`;
CREATE TABLE IF NOT EXISTS `xdc_t_discuss_group`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT '表单ID',
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讨论主题',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_request_id`(`request_id`) USING BTREE COMMENT '表单ID索引',
  INDEX `idx_subject`(`subject`) USING BTREE COMMENT '主题索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '讨论组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xdc_t_discuss_record
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_discuss_record`;
CREATE TABLE IF NOT EXISTS `xdc_t_discuss_record`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '讨论组ID',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `from_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发信人',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_group_id`(`group_id`) USING BTREE COMMENT '讨论组ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '讨论记录' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for xdc_t_discuss_record_relation
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_discuss_record_relation`;
CREATE TABLE IF NOT EXISTS `xdc_t_discuss_record_relation`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `to_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收信人',
  `record_id` bigint(20) NULL DEFAULT NULL COMMENT '记录ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_to_user`(`to_user`) USING BTREE COMMENT '收信人索引',
  INDEX `idx_record_id`(`record_id`) USING BTREE COMMENT '记录ID索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '讨论记录关联信息' ROW_FORMAT = DYNAMIC;













SET FOREIGN_KEY_CHECKS = 1;

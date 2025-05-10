/*
 Navicat Premium Dump SQL

 Source Server         : main
 Source Server Type    : MySQL
 Source Server Version : 80404 (8.4.4)
 Source Host           : localhost:3306
 Source Schema         : bms

 Target Server Type    : MySQL
 Target Server Version : 80404 (8.4.4)
 File Encoding         : 65001

 Date: 10/05/2025 21:33:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint DEFAULT '0' COMMENT '上级部门id',
  `dept_name` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `order_num` int DEFAULT '0' COMMENT '优先级',
  `leader` varchar(64) DEFAULT NULL COMMENT '领导负责人',
  `phone_number` varchar(13) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `emp_id` bigint NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `pos_id` bigint DEFAULT NULL COMMENT '岗位id',
  `job_number` varchar(36) NOT NULL COMMENT '员工工号也是员工用户名',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '员工昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '员工邮箱',
  `phone_number` varchar(13) DEFAULT NULL COMMENT '员工手机号',
  `sex` char(1) DEFAULT '0' COMMENT '员工性别',
  `avatar` varchar(255) DEFAULT NULL COMMENT '员工头像',
  `password` varchar(255) DEFAULT NULL COMMENT '员工密码',
  `status` char(1) DEFAULT '0' COMMENT '员工账号状态',
  `del_flag` tinyint DEFAULT '0' COMMENT '员工删除标识',
  `first_login` tinyint(1) DEFAULT '0' COMMENT '是否第一次登录',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`emp_id`,`job_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of employee
-- ----------------------------
BEGIN;
INSERT INTO `employee` (`emp_id`, `dept_id`, `pos_id`, `job_number`, `nick_name`, `email`, `phone_number`, `sex`, `avatar`, `password`, `status`, `del_flag`, `first_login`, `login_time`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (1, 1, 2, 'super_admin', '超级管理员', '123456@qq.com', '18855667778', '0', 'fdaksjfdjafhdksafsdjk', '$2a$10$uog5Dn7GPp8Oqu66bHAlyeI7BMX/WgqG/pzjwxvCdROAVsbCaBIZy', '0', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `employee` (`emp_id`, `dept_id`, `pos_id`, `job_number`, `nick_name`, `email`, `phone_number`, `sex`, `avatar`, `password`, `status`, `del_flag`, `first_login`, `login_time`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (6, NULL, NULL, '20250420001', 'EatFan', 'eatfan0921@163.com', '18855668515', '0', NULL, '$2a$10$uog5Dn7GPp8Oqu66bHAlyeI7BMX/WgqG/pzjwxvCdROAVsbCaBIZy', '0', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `employee` (`emp_id`, `dept_id`, `pos_id`, `job_number`, `nick_name`, `email`, `phone_number`, `sex`, `avatar`, `password`, `status`, `del_flag`, `first_login`, `login_time`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (7, 1, 2, '20250510001', '测试账号', '12fd32456@qq.com', '12332320', '0', NULL, '$2a$10$Q1gHSvb4SGtJXA2WbyQequ4ifv/5RMtT983Mk22eCUnI2kUU3LpeG', '0', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `emp_id` bigint NOT NULL COMMENT '员工id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
BEGIN;
INSERT INTO `employee_role` (`id`, `emp_id`, `role_id`) VALUES (1, 1, 2);
INSERT INTO `employee_role` (`id`, `emp_id`, `role_id`) VALUES (2, 3, 2);
INSERT INTO `employee_role` (`id`, `emp_id`, `role_id`) VALUES (3, 7, 4);
COMMIT;

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` bigint NOT NULL COMMENT 'id',
  `job_number` varchar(36) DEFAULT NULL COMMENT '员工工号',
  `ip_address` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录地ip',
  `agent` varchar(255) DEFAULT NULL COMMENT '浏览器UA标识',
  `os` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '登录状态 1成功 0失败',
  `msg` varchar(500) DEFAULT NULL COMMENT '登录信息或失败原因',
  `log_time` datetime DEFAULT NULL COMMENT '日志创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限主键id',
  `permission_code` varchar(255) DEFAULT NULL COMMENT '权限码',
  `permission_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `status` tinyint DEFAULT '1' COMMENT '权限状态1为启用0为禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` (`permission_id`, `permission_code`, `permission_name`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (1, 'system.super.admin', '超级管理员权限', 1, '2025-04-23 20:18:24', 'System', NULL, NULL, '系统默认权限');
INSERT INTO `permission` (`permission_id`, `permission_code`, `permission_name`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (2, 'system.add.employee', '添加员工权限', 1, '2025-04-23 20:20:29', 'System', NULL, NULL, '系统默认权限');
INSERT INTO `permission` (`permission_id`, `permission_code`, `permission_name`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (3, 'system.add.permission', '添加权限的权限', 1, '2025-04-23 20:21:12', 'System', NULL, NULL, '系统默认权限');
INSERT INTO `permission` (`permission_id`, `permission_code`, `permission_name`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (4, 'system.add.role', '添加角色权限', 1, '2025-04-23 20:22:22', 'System', NULL, NULL, '系统默认权限');
INSERT INTO `permission` (`permission_id`, `permission_code`, `permission_name`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (5, 'system.add', '添加权限', 1, '2025-04-23 20:32:57', 'System', NULL, NULL, '系统默认权限');
INSERT INTO `permission` (`permission_id`, `permission_code`, `permission_name`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (6, 'system.add', '添加权限', 1, '2025-04-26 14:33:52', 'System', NULL, NULL, '系统默认权限');
COMMIT;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `pos_id` bigint NOT NULL COMMENT '岗位id',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `pos_name` varchar(50) DEFAULT NULL COMMENT '岗位名字',
  `level` varchar(20) DEFAULT NULL COMMENT '岗位等级',
  ` status` char(1) DEFAULT '0' COMMENT '岗位状态',
  `description` varchar(500) DEFAULT NULL COMMENT '岗位介绍',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`pos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of post
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `role_key` varchar(100) DEFAULT NULL COMMENT '角色key',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除标识',
  `status` tinyint DEFAULT NULL COMMENT '状态，0为禁用，1为启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`role_id`, `role_name`, `role_key`, `del_flag`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (1, '系统', 'System', 0, 1, '2025-04-24 18:47:49', 'System', NULL, NULL, '系统中最高等级的角色');
INSERT INTO `role` (`role_id`, `role_name`, `role_key`, `del_flag`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (2, '超级管理员', 'SuperAdmin', 0, 1, '2025-04-24 18:48:32', 'System', NULL, NULL, '系统中超级管理员的角色');
INSERT INTO `role` (`role_id`, `role_name`, `role_key`, `del_flag`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (3, '管理员', 'Admin', 0, 1, '2025-04-24 18:48:49', 'System', NULL, NULL, '系统中管理员的角色');
INSERT INTO `role` (`role_id`, `role_name`, `role_key`, `del_flag`, `status`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (4, '普通用户', 'User', 0, 1, '2025-04-24 18:49:06', 'System', NULL, NULL, '系统中普通用户的角色');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint NOT NULL,
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '配置文件id',
  `config_name` varchar(100) DEFAULT NULL COMMENT '配置名字',
  `config_key` varchar(255) DEFAULT NULL COMMENT '配置key',
  `config_value` varchar(255) DEFAULT NULL COMMENT '配置value',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `text` varchar(30) DEFAULT NULL COMMENT '菜单选项文本',
  `icon` varchar(30) DEFAULT NULL COMMENT '菜单选项图标',
  `link` varchar(100) DEFAULT NULL COMMENT '菜单选项link',
  `order_number` int DEFAULT '1' COMMENT '排序数字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` int DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
BEGIN;
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (1, '仪表盘', 'bx bx-home-alt', '/dashboard', 1, '2025-04-26 16:34:31', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (2, '项目', 'bx bx-user', '/project', 2, '2025-04-26 16:35:09', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (3, '任务', 'bx bx-notepad', '/task', 3, '2025-04-26 16:35:36', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (4, '日历', 'bx bx-dock-bottom', '/calendar', 4, '2025-04-26 16:49:02', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (5, '假期', 'bx bx-buildings', '/holiday', 5, '2025-04-26 16:49:55', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (6, '考勤', 'bx bx-calendar-check', '/attendance', 6, '2025-04-26 16:50:36', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (7, '消息', 'bx bx-task', '/notify', 7, '2025-04-26 16:54:24', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (8, '员工管理', 'bx bxs-user-account', '/employee', 8, '2025-05-10 21:30:27', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (9, '角色管理', 'bx bx-group', '/role', 9, '2025-05-10 21:31:07', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (10, '权限管理', 'bx bx-sitemap', '/permission', 10, '2025-05-10 21:31:43', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (11, '项目管理', 'bx bx-user', '/PManager', 11, '2025-05-10 21:32:26', 'System', NULL, NULL, '系统默认的菜单');
INSERT INTO `system_menu` (`menu_id`, `text`, `icon`, `link`, `order_number`, `create_time`, `create_by`, `update_time`, `update_by`, `remark`) VALUES (12, '任务管理', 'bx bx-notepad', '/PManager', 12, '2025-05-10 21:33:16', 'System', NULL, NULL, '系统默认的菜单');
COMMIT;

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `menu_id` bigint NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

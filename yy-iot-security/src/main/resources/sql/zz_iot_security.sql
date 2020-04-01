/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : zz_iot_security

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 01/04/2020 08:59:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_company
-- ----------------------------
INSERT INTO `sys_company` VALUES ('1', '2020-03-05 13:35:59', '中泽电子', '初始化公司');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `en_visible` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1235436071289221120', 'sys:user:list', '2020-03-05 13:24:47', '查看企业下的所有用户', 1, '查看所有用户', NULL);
INSERT INTO `sys_permission` VALUES ('1235436353167421440', 'sys:user:info', '2020-03-05 13:25:55', '根据id查询用户信息', 1, '根据id查询', NULL);
INSERT INTO `sys_permission` VALUES ('1235436464077402112', 'sys:user:save', '2020-03-05 13:26:21', '添加用户', 1, '添加用户', NULL);
INSERT INTO `sys_permission` VALUES ('1235436529336578048', 'sys:user:update', '2020-03-05 13:26:37', '修改用户信息', 1, '修改用户信息', NULL);
INSERT INTO `sys_permission` VALUES ('1235436700464181248', 'sys:user:delete', '2020-03-05 13:27:17', '删除用户', 1, '删除用户', NULL);
INSERT INTO `sys_permission` VALUES ('1235437094288355328', 'sys:user:addRoles', '2020-03-05 13:28:51', '给用户分配角色', 1, '分配角色', NULL);
INSERT INTO `sys_permission` VALUES ('1235437216288075776', 'sys:role:list', '2020-03-05 13:29:20', '查看所有角色', 1, '查看所有角色', NULL);
INSERT INTO `sys_permission` VALUES ('1235437749451223040', 'sys:role:info', '2020-03-05 13:31:27', '根据id查询角色', 1, '根据id查询角色', NULL);
INSERT INTO `sys_permission` VALUES ('1235437802546917376', 'sys:role:save', '2020-03-05 13:31:40', '添加角色', 1, '添加角色', NULL);
INSERT INTO `sys_permission` VALUES ('1235437845286875136', 'sys:role:update', '2020-03-05 13:31:50', '修改角色', 1, '修改角色', NULL);
INSERT INTO `sys_permission` VALUES ('1235437920830484480', 'sys:role:delete', '2020-03-05 13:32:08', '删除角色', 1, '删除角色', NULL);
INSERT INTO `sys_permission` VALUES ('1235438096429215744', 'sys:role:addPermissions', '2020-03-05 13:32:50', '给角色分配权限', 1, '给角色分配权限', NULL);
INSERT INTO `sys_permission` VALUES ('1235443028775428096', 'sys:permission:list', '2020-03-05 13:52:26', '查询所有权限信息', 1, '查询所有权限信息', NULL);
INSERT INTO `sys_permission` VALUES ('1235443075378339840', 'sys:permission:info', '2020-03-05 13:52:37', '根据id查询权限信息', 1, '根据id查询权限信息', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `company_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'role:sys:admin', '1', '2020-03-05 16:41:50', '权限最高的用户，拥有所有权限', '系统管理员');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `permission_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `FKomxrs8a388bknvhjokh440waq`(`permission_id`) USING BTREE,
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKomxrs8a388bknvhjokh440waq` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1235436071289221120');
INSERT INTO `sys_role_permission` VALUES ('1', '1235436353167421440');
INSERT INTO `sys_role_permission` VALUES ('1', '1235436464077402112');
INSERT INTO `sys_role_permission` VALUES ('1', '1235436529336578048');
INSERT INTO `sys_role_permission` VALUES ('1', '1235436700464181248');
INSERT INTO `sys_role_permission` VALUES ('1', '1235437094288355328');
INSERT INTO `sys_role_permission` VALUES ('1', '1235437216288075776');
INSERT INTO `sys_role_permission` VALUES ('1', '1235437749451223040');
INSERT INTO `sys_role_permission` VALUES ('1', '1235437802546917376');
INSERT INTO `sys_role_permission` VALUES ('1', '1235437845286875136');
INSERT INTO `sys_role_permission` VALUES ('1', '1235437920830484480');
INSERT INTO `sys_role_permission` VALUES ('1', '1235438096429215744');
INSERT INTO `sys_role_permission` VALUES ('1', '1235443028775428096');
INSERT INTO `sys_role_permission` VALUES ('1', '1235443075378339840');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `company_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `department_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '2020-03-05 16:41:50', '1', '870508366@qq.com', '18683808703', 'admin', '9aa75c4d70930277f59d117ce19188b0', 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKhh52n8vd4ny9ff4x9fb8v65qx`(`role_id`) USING BTREE,
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');

SET FOREIGN_KEY_CHECKS = 1;

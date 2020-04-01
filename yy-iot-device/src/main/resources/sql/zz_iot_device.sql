/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : zz_iot_device

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 01/04/2020 09:00:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device_group_info
-- ----------------------------
DROP TABLE IF EXISTS `device_group_info`;
CREATE TABLE `device_group_info`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组id',
  `company_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属公司id',
  `model` smallint(2) NOT NULL COMMENT '分组模式：\r\n1:控制柜分组\r\n2:rtu分组\r\n3:网关分组\r\n4:灯杆分组\r\n5:单灯分组',
  `type` smallint(2) NOT NULL COMMENT '分组类型:\r\n1:默认:通常用于树状图默认的加载\r\n2:其他值:扩展类型,例如用户自定义的分组。',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名，默认使用地址中的区县名（district）信息',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `gmt_creat` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分组信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device_info
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备id',
  `company_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属公司id',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上一级设备id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `hardware_version` smallint(2) NULL DEFAULT NULL COMMENT '硬件版本:\r\nrtu:\r\n1:目前只有一个版本\r\ngateway:\r\n1:老版本的\r\n2:机场版本\r\ncontroller:\r\n1:老版本\r\n2:NB单灯\r\n3:机场单灯',
  `software_version` smallint(2) NULL DEFAULT NULL COMMENT '软件版本:暂时未使用，都用版本1',
  `enabled_flag` tinyint(1) NOT NULL COMMENT '设备是否启用',
  `device_type` smallint(2) NOT NULL COMMENT '设备类型：\r\n1.控制柜\r\n2:rtu\r\n3:网关\r\n4:灯杆\r\n5:单灯控制器',
  `comm_type` smallint(2) NULL DEFAULT NULL COMMENT '通讯方式:\r\n0:无需通讯，通常为虚拟设备或灯杆\r\n1:GPRS\r\n2:NB',
  `usage_type` smallint(2) NULL DEFAULT NULL COMMENT '使用类型:\r\nrtu:\r\n1:路灯 \r\n2:光彩\r\n单灯控制器:\r\n1:主灯 \r\n2:副灯 \r\n3:灯带 \r\n4:机场高杆灯\r\n',
  `imei` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'imei或者手机号,查费使用',
  `device_sn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备编号:\r\n1:老版本未区分项目的用companyid+dtuid+mcuid\r\n2:机场设备直接填写对应的id\r\n3:nb设备填写平台返回的uuid\r\n4:控制柜可填写控制柜编号\r\n5:灯杆可填写灯杆编号',
  `lng` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备地址',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `gmt_creat` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_device_rel
-- ----------------------------
DROP TABLE IF EXISTS `group_device_rel`;
CREATE TABLE `group_device_rel`  (
  `group_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`group_id`, `device_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分组信息和设备信息关系表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

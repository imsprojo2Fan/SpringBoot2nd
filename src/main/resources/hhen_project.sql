/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50551
Source Host           : localhost:3306
Source Database       : hhen_project

Target Server Type    : MYSQL
Target Server Version : 50551
File Encoding         : 65001

Date: 2018-07-06 13:39:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `basic_setting`
-- ----------------------------
DROP TABLE IF EXISTS `basic_setting`;
CREATE TABLE `basic_setting` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `key` varchar(64) DEFAULT '' COMMENT '设置key',
  `value` varchar(64) DEFAULT '' COMMENT '设置value',
  `unit` varchar(32) DEFAULT '' COMMENT '单位',
  `remark` varchar(1024) DEFAULT '' COMMENT '备注',
  `updated` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统基础配置表';

-- ----------------------------
-- Records of basic_setting
-- ----------------------------

-- ----------------------------
-- Table structure for `data_point`
-- ----------------------------
DROP TABLE IF EXISTS `data_point`;
CREATE TABLE `data_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT '' COMMENT '数据节点名称',
  `longitude` decimal(10,6) DEFAULT '0.000000' COMMENT '经度',
  `latitude` decimal(10,6) DEFAULT '0.000000' COMMENT '纬度',
  `province` varchar(32) DEFAULT '' COMMENT '省',
  `city` varchar(32) DEFAULT '' COMMENT '市',
  `city_code` varchar(16) DEFAULT '' COMMENT '城市代码',
  `district` varchar(32) DEFAULT '' COMMENT '区',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  `data_ip` varchar(64) DEFAULT '' COMMENT 'plcIP',
  `data_port` varchar(32) DEFAULT '' COMMENT 'plc端口',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据节点信息表';

-- ----------------------------
-- Records of data_point
-- ----------------------------

-- ----------------------------
-- Table structure for `dp_alarm_setting`
-- ----------------------------
DROP TABLE IF EXISTS `dp_alarm_setting`;
CREATE TABLE `dp_alarm_setting` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `dpid` int(16) DEFAULT '0' COMMENT '数据节点id',
  `device_id` int(16) DEFAULT '0' COMMENT '设备id',
  `type` tinyint(2) DEFAULT '0' COMMENT '-1小于 0等于 1大于',
  `updated` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报警条件设置表';

-- ----------------------------
-- Records of dp_alarm_setting
-- ----------------------------

-- ----------------------------
-- Table structure for `dp_data_device`
-- ----------------------------
DROP TABLE IF EXISTS `dp_data_device`;
CREATE TABLE `dp_data_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dpid` int(11) DEFAULT '0' COMMENT '数据节点id',
  `name` varchar(64) DEFAULT '' COMMENT '设备名称',
  `device_code` varchar(64) DEFAULT '' COMMENT '设备代码',
  `unit` varchar(16) DEFAULT '' COMMENT '数据单位',
  `enabled` tinyint(2) DEFAULT '1' COMMENT '设备是否启用 1开 0关',
  `type` tinyint(2) DEFAULT '0' COMMENT '设备类型 0数据设备 1plc设备',
  `data_type` varchar(16) DEFAULT '' COMMENT 'plc读取传感器数据类型',
  `port` varchar(16) DEFAULT '' COMMENT '传感器端口',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点数据设备信息表';

-- ----------------------------
-- Records of dp_data_device
-- ----------------------------

-- ----------------------------
-- Table structure for `dp_history_alarm`
-- ----------------------------
DROP TABLE IF EXISTS `dp_history_alarm`;
CREATE TABLE `dp_history_alarm` (
  `id` int(16) NOT NULL,
  `dpid` int(16) DEFAULT '0' COMMENT '节点id',
  `device_id` int(16) DEFAULT '0' COMMENT '设备id',
  `value` decimal(8,3) DEFAULT '0.000' COMMENT '设备值',
  `standard` decimal(8,3) DEFAULT '0.000' COMMENT '标准值',
  `type` tinyint(2) DEFAULT '0' COMMENT '比较类型 -1小于 0等于 1大于',
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报警历史记录表';

-- ----------------------------
-- Records of dp_history_alarm
-- ----------------------------

-- ----------------------------
-- Table structure for `dp_history_data`
-- ----------------------------
DROP TABLE IF EXISTS `dp_history_data`;
CREATE TABLE `dp_history_data` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `dpid` int(16) DEFAULT '0' COMMENT '数据节点id',
  `device_code` varchar(64) DEFAULT '' COMMENT '节点设备code',
  `value` decimal(10,10) DEFAULT '0.0000000000' COMMENT '设备数值',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index01` (`dpid`,`device_code`,`created`) USING BTREE,
  KEY `index02` (`dpid`,`created`) USING BTREE,
  KEY `index03` (`device_code`,`created`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点历史数据表';

-- ----------------------------
-- Records of dp_history_data
-- ----------------------------

-- ----------------------------
-- Table structure for `dp_history_video`
-- ----------------------------
DROP TABLE IF EXISTS `dp_history_video`;
CREATE TABLE `dp_history_video` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `dpid` int(16) DEFAULT NULL COMMENT '数据节点id',
  `channel` int(16) DEFAULT '0' COMMENT '通道号',
  `file_name` varchar(64) DEFAULT '' COMMENT '文件名 视频/图片名',
  `file_type` int(4) DEFAULT '0' COMMENT '文件类型 0图片 1视频',
  `operated` datetime DEFAULT NULL COMMENT '操作时间',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index01` (`dpid`,`operated`) USING BTREE,
  KEY `index02` (`dpid`,`file_type`,`operated`) USING BTREE,
  KEY `index03` (`dpid`,`created`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点历史监控信息表';

-- ----------------------------
-- Records of dp_history_video
-- ----------------------------

-- ----------------------------
-- Table structure for `dp_real_data`
-- ----------------------------
DROP TABLE IF EXISTS `dp_real_data`;
CREATE TABLE `dp_real_data` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `dpid` int(16) DEFAULT '0' COMMENT '数据节点id',
  `device_code` varchar(64) DEFAULT NULL COMMENT '节点设备code',
  `value` decimal(10,10) DEFAULT '0.0000000000' COMMENT '设备数据',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点实时数据表';

-- ----------------------------
-- Records of dp_real_data
-- ----------------------------

-- ----------------------------
-- Table structure for `dp_video_device`
-- ----------------------------
DROP TABLE IF EXISTS `dp_video_device`;
CREATE TABLE `dp_video_device` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `dpid` int(16) DEFAULT NULL COMMENT '数据节点id',
  `name` varchar(64) DEFAULT '' COMMENT '设备名称',
  `type` varchar(64) DEFAULT '' COMMENT '设备型号',
  `enabled` tinyint(2) DEFAULT '0' COMMENT '是否启用',
  `ip` varchar(64) DEFAULT '' COMMENT 'ip',
  `port` varchar(16) DEFAULT '' COMMENT '端口',
  `account` varchar(64) DEFAULT '' COMMENT '账号',
  `password` varchar(64) DEFAULT '' COMMENT '密码',
  `channel` int(16) DEFAULT '1' COMMENT '通道号',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ip2port` (`ip`,`port`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频设备信息表';

-- ----------------------------
-- Records of dp_video_device
-- ----------------------------

-- ----------------------------
-- Table structure for `plc_operate_log`
-- ----------------------------
DROP TABLE IF EXISTS `plc_operate_log`;
CREATE TABLE `plc_operate_log` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `uid` int(16) DEFAULT '0' COMMENT '操作人id',
  `device_id` int(16) DEFAULT NULL,
  `type` int(4) DEFAULT '0' COMMENT '操作类型 0关闭设备 1打开设备 2设置值',
  `value` varchar(16) DEFAULT '' COMMENT '设置值',
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='PLC反向控制记录表';

-- ----------------------------
-- Records of plc_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT '' COMMENT '角色',
  `authority` varchar(64) DEFAULT '' COMMENT '权限',
  `created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) NOT NULL COMMENT '用户账号',
  `password` varchar(255) NOT NULL COMMENT '加密用户密码',
  `name` varchar(64) DEFAULT '' COMMENT '姓名',
  `type` tinyint(2) DEFAULT '1' COMMENT '账号类型(1.普通用户 2.管理员 3.高级管理员 4.超级管理员)',
  `phone` varchar(32) DEFAULT '' COMMENT '手机号码',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像文件路径',
  `wxinfo_id` int(11) DEFAULT '0' COMMENT '微信用户信息id',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='账号信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', 'admin', '542607bd827f2335c06743fc0cdf7d74', '范仲淹', '3', '13646541653', null, '0', null, '2018-07-06 09:52:22');
INSERT INTO `user` VALUES ('8', 'test01', '542607bd827f2335c06743fc0cdf7d74', '', '1', '', null, '0', null, '2018-07-05 14:08:30');
INSERT INTO `user` VALUES ('9', 'test02', '542607bd827f2335c06743fc0cdf7d74', '', '1', '', null, '0', null, '2018-07-05 14:08:52');
INSERT INTO `user` VALUES ('10', 'test03', '542607bd827f2335c06743fc0cdf7d74', '', '1', '', null, '0', null, '2018-07-05 14:09:02');
INSERT INTO `user` VALUES ('11', 'test04', '542607bd827f2335c06743fc0cdf7d74', '', '1', '', null, '0', null, '2018-07-05 14:09:19');
INSERT INTO `user` VALUES ('13', 'test06', '542607bd827f2335c06743fc0cdf7d74', '', '1', '', null, '0', null, '2018-07-05 14:10:09');
INSERT INTO `user` VALUES ('15', 'test08', '17bba962a903971b6c37faaaf6893d95', '梁朝伟', '2', '13664546541', null, '0', '2018-07-06 11:04:25', '2018-07-06 11:04:25');
INSERT INTO `user` VALUES ('16', 'test09', '542607bd827f2335c06743fc0cdf7d74', '张学友', '3', '13623564153', null, '0', '2018-07-06 11:01:10', '2018-07-06 11:01:10');
INSERT INTO `user` VALUES ('17', 'test10', '542607bd827f2335c06743fc0cdf7d74', '', '1', '', null, '0', null, '2018-07-05 14:10:58');
INSERT INTO `user` VALUES ('19', 'test11', '17bba962a903971b6c37faaaf6893d95', '', '1', '', '', '0', null, '2018-07-06 12:00:50');
INSERT INTO `user` VALUES ('20', 'test13', '17bba962a903971b6c37faaaf6893d95', '', '1', '', '', '0', null, '2018-07-06 13:33:38');
INSERT INTO `user` VALUES ('21', 'test14', '542607bd827f2335c06743fc0cdf7d74', '李华', '3', '13646541364', '', '0', '2018-07-06 13:34:31', '2018-07-06 13:34:31');

-- ----------------------------
-- Table structure for `user2role`
-- ----------------------------
DROP TABLE IF EXISTS `user2role`;
CREATE TABLE `user2role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user2role
-- ----------------------------

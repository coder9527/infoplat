/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : iyue_info

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-01-20 20:07:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for infos_columns
-- ----------------------------
DROP TABLE IF EXISTS `infos_columns`;
CREATE TABLE `infos_columns` (
  `id` varchar(32) NOT NULL,
  `column_name` varchar(200) DEFAULT NULL,
  `delete_flag` varchar(1) DEFAULT NULL,
  `column_type` varchar(1) DEFAULT NULL COMMENT '是否是固定栏目，0是，1不是',
  `order_num` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of infos_columns
-- ----------------------------

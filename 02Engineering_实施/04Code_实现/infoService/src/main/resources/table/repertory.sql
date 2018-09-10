/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : iyue_info

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-08-22 17:38:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for repertory
-- ----------------------------
DROP TABLE IF EXISTS `repertory`;
CREATE TABLE `repertory` (
  `id` varchar(32) NOT NULL,
  `productName` varchar(200) DEFAULT NULL,
  `productNo` varchar(50) DEFAULT NULL,
  `countNum` varchar(10) DEFAULT NULL,
  `repertoryNum` varchar(50) DEFAULT NULL,
  `corperatrion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

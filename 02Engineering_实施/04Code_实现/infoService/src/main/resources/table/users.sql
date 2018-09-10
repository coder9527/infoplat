/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : iyue_info

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-06-05 10:54:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `headerUrl` varchar(255) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `identityType` varchar(255) DEFAULT NULL,
  `singn` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `extends1` varchar(255) DEFAULT NULL,
  `extends2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

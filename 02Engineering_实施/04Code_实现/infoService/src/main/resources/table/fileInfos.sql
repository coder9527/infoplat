/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : iyue_info

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-06-01 13:02:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fileinfos
-- ----------------------------
DROP TABLE IF EXISTS `fileinfos`;
CREATE TABLE `fileinfos` (
  `id` varchar(32) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `authorize` varchar(255) DEFAULT NULL,
  `softType` varchar(255) DEFAULT NULL,
  `softLanguage` varchar(255) DEFAULT NULL,
  `updateTime` varchar(255) DEFAULT NULL,
  `applyPlat` varchar(255) DEFAULT NULL,
  `softWebsite` varchar(255) DEFAULT NULL,
  `softSize` varchar(255) DEFAULT NULL,
  `monthDownCount` varchar(255) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `softDesc` varchar(255) DEFAULT NULL,
  `softPics` varchar(255) DEFAULT NULL,
  `downUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fileinfos
-- ----------------------------

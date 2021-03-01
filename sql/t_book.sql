/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2017-01-12 10:29:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_book`
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `bookId` int(11) NOT NULL auto_increment COMMENT '书号',
  `bookName` varchar(255) default NULL COMMENT '书名',
  `price` int(11) default NULL COMMENT '书的单价',
  PRIMARY KEY  (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('1', '福尔摩斯', '100');
INSERT INTO `t_book` VALUES ('2', '心理罪', '80');
INSERT INTO `t_book` VALUES ('3', '三体', '120');

/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2017-01-12 10:30:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_stock`
-- ----------------------------
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock` (
  `id` int(11) NOT NULL auto_increment COMMENT '库存id',
  `stock` int(11) default NULL COMMENT '库存量',
  `bookId` int(11) default NULL COMMENT '书号',
  PRIMARY KEY  (`id`),
  KEY `fk_book_id` (`bookId`),
  CONSTRAINT `fk_book_id` FOREIGN KEY (`bookId`) REFERENCES `t_book` (`bookId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stock
-- ----------------------------
INSERT INTO `t_stock` VALUES ('1', '7', '1');
INSERT INTO `t_stock` VALUES ('2', '10', '2');
INSERT INTO `t_stock` VALUES ('3', '10', '3');

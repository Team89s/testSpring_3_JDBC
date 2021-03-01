/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2017-01-11 10:05:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_emp`
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `id` int(11) NOT NULL auto_increment COMMENT '员工id',
  `name` varchar(255) default NULL COMMENT '员工姓名',
  `email` varchar(255) default NULL COMMENT '员工email',
  `dept_id` int(11) default NULL COMMENT '部门号',
  PRIMARY KEY  (`id`),
  KEY `fk_dept_id` (`dept_id`),
  CONSTRAINT `fk_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES ('1', '高强', 'gaoqiang@123.com', '1');
INSERT INTO `t_emp` VALUES ('2', '李四', 'lisi@123.com', '2');
INSERT INTO `t_emp` VALUES ('3', '王五', 'wangwu@123.com', '3');
INSERT INTO `t_emp` VALUES ('4', '陈六', 'chenliu@123.com', '4');
INSERT INTO `t_emp` VALUES ('5', 'aa', 'aa@123.com', '1');
INSERT INTO `t_emp` VALUES ('6', 'bb', 'bb@123.com', '2');
INSERT INTO `t_emp` VALUES ('7', 'cc', 'cc@123.com', '3');

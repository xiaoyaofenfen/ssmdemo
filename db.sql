/*
 Navicat Premium Data Transfer

 Source Server         : mysql.localhost
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost
 Source Database       : ssm_demo

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : utf-8

 Date: 07/14/2016 23:06:27 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `education`
-- ----------------------------
DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `level` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `education`
-- ----------------------------
BEGIN;
INSERT INTO `education` VALUES ('1', '本科'), ('2', '博士'), ('3', '专科'), ('4', '高中'), ('5', '未知');
COMMIT;

-- ----------------------------
--  Table structure for `gender`
-- ----------------------------
DROP TABLE IF EXISTS `gender`;
CREATE TABLE `gender` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `gender`
-- ----------------------------
BEGIN;
INSERT INTO `gender` VALUES ('1', 'female'), ('2', 'male'), ('3', 'unkown'), ('4', 'hybird');
COMMIT;

-- ----------------------------
--  Table structure for `origin`
-- ----------------------------
DROP TABLE IF EXISTS `origin`;
CREATE TABLE `origin` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `city` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `origin`
-- ----------------------------
BEGIN;
INSERT INTO `origin` VALUES ('1', '广州'), ('2', '深圳'), ('3', '湛江'), ('4', '其他');
COMMIT;

-- ----------------------------
--  Table structure for `register_info`
-- ----------------------------
DROP TABLE IF EXISTS `register_info`;
CREATE TABLE `register_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `gender_id` int(11) NOT NULL,
  `education_id` int(11) NOT NULL,
  `origin_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gender_id` (`gender_id`),
  KEY `fk_education_id` (`education_id`),
  KEY `fk_origin_id` (`origin_id`),
  KEY `user_id` (`user_id`),
  KEY `user_id_2` (`user_id`),
  KEY `user_id_3` (`user_id`),
  CONSTRAINT `fk_education_id` FOREIGN KEY (`education_id`) REFERENCES `education` (`id`),
  CONSTRAINT `fk_gender_id` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `fk_origin_id` FOREIGN KEY (`origin_id`) REFERENCES `origin` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `register_info`
-- ----------------------------
BEGIN;
INSERT INTO `register_info` VALUES ('1', 'liangfen', '1', '3', '1', '1'), ('2', 'lf', '2', '4', '4', '2'), ('3', 'kkkkkkkk', '2', '4', '4', '2'), ('4', 'kkkkkkkk', '2', '4', '4', '2'), ('5', 'kkkkkkkk', '2', '4', '4', '2'), ('6', 'kkkkkkkk1354982', '2', '4', '4', '2');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'df', 'dsgsdgd'), ('2', 'lf', 'lffffff'), ('3', 'liangfen', 'lf');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

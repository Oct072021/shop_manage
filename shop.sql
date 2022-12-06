/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 06/12/2022 02:50:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '商品的id',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的名字',
  `goods_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的图片',
  `price` double(10, 2) NOT NULL COMMENT '商品价格',
  `count` int NOT NULL COMMENT '商品的数量',
  `on_sale` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的是否在销售',
  `goods_type` int NOT NULL COMMENT '商品类型id与商品类型表进行关联',
  PRIMARY KEY (`goods_id`, `goods_type`) USING BTREE,
  INDEX `goods_id`(`goods_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (29, 'RTX 3090', '/images/front/numberCode/3090Ti.jpg', 9999.00, 10, 'yes', 1);
INSERT INTO `goods` VALUES (31, 'RTX 3090Ti', '/images/front/numberCode/3090.jpg', 10999.00, 20, 'yes', 1);
INSERT INTO `goods` VALUES (32, 'RTX 4080', '/images/front/numberCode/4080.jpg', 16999.00, 5, 'yes', 1);
INSERT INTO `goods` VALUES (33, 'RTX 4090', '/images/front/numberCode/4090.jpg', 20999.00, 0, 'no', 1);
INSERT INTO `goods` VALUES (34, 'GTX 1050 Ti', '/images/front/numberCode/1050 Ti.jpg', 1099.00, 6, 'yes', 1);

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type`  (
  `goods_type_id` int NOT NULL AUTO_INCREMENT COMMENT '商品类型的id,跟商品进行关联',
  `goods_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类型的名字',
  INDEX `goods_type_id`(`goods_type_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES (1, '数码产品');
INSERT INTO `goods_type` VALUES (2, '生活用品');
INSERT INTO `goods_type` VALUES (3, '食品饮料');
INSERT INTO `goods_type` VALUES (5, '衣装服饰');
INSERT INTO `goods_type` VALUES (6, '水果蔬菜');
INSERT INTO `goods_type` VALUES (22, '线上充值');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单的id',
  `user_id` int NOT NULL COMMENT '订单属于那个用户',
  `goods_id` int NOT NULL COMMENT '订单中包含商品',
  `order_count` int NOT NULL COMMENT '订单数量',
  `order_price` double(10, 2) NOT NULL COMMENT '订单金额',
  `order_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `order_ibfk_1`(`user_id` ASC) USING BTREE,
  INDEX `order_ibfk_2`(`goods_id` ASC) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (15, 16, 32, 2, 33998.00, 'xxx');
INSERT INTO `order` VALUES (16, 20, 29, 1, 9999.00, 'xxxx');

-- ----------------------------
-- Table structure for shop_cart
-- ----------------------------
DROP TABLE IF EXISTS `shop_cart`;
CREATE TABLE `shop_cart`  (
  `shop_cart_id` int NOT NULL AUTO_INCREMENT COMMENT '购物车Id',
  `user_id` int NULL DEFAULT NULL COMMENT '购物车属于某个用户',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品Id方便找到商品',
  `count` int NULL DEFAULT NULL COMMENT '购物车中要购买商品的数量',
  `shop_price` double(10, 2) NULL DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`shop_cart_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `goods_id`(`goods_id` ASC) USING BTREE,
  CONSTRAINT `shop_cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `shop_cart_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shop_cart
-- ----------------------------
INSERT INTO `shop_cart` VALUES (18, 16, 31, 1, 10999.00);
INSERT INTO `shop_cart` VALUES (19, 20, 33, 2, 41998.00);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户的id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号码',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱,用于找回密码',
  `sign_in_data` date NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone` ASC) USING BTREE COMMENT '手机号码唯一性',
  UNIQUE INDEX `user_email`(`user_email` ASC) USING BTREE COMMENT '邮箱的唯一性'
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (16, '123', '123', '123', '123', '2022-09-11');
INSERT INTO `users` VALUES (20, '1234', '1234', '1234', '1234', NULL);

SET FOREIGN_KEY_CHECKS = 1;

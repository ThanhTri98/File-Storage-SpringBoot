/*
 Navicat Premium Data Transfer

 Source Server         : 2021_Hk8
 Source Server Type    : MySQL
 Source Server Version : 100418
 Source Host           : localhost:3306
 Source Schema         : file_storage

 Target Server Type    : MySQL
 Target Server Version : 100418
 File Encoding         : 65001

 Date: 01/05/2021 03:29:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accountpackage
-- ----------------------------
DROP TABLE IF EXISTS `accountpackage`;
CREATE TABLE `accountpackage`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `total_size` bigint NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accountpackage
-- ----------------------------

-- ----------------------------
-- Table structure for billhistory
-- ----------------------------
DROP TABLE IF EXISTS `billhistory`;
CREATE TABLE `billhistory`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `pkg_id` int NOT NULL,
  `createDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `expireDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `pkg_id`) USING BTREE,
  INDEX `fk_bill_pkg`(`pkg_id`) USING BTREE,
  CONSTRAINT `fk_bill_pkg` FOREIGN KEY (`pkg_id`) REFERENCES `accountpackage` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_bill_u` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of billhistory
-- ----------------------------

-- ----------------------------
-- Table structure for musicextension
-- ----------------------------
DROP TABLE IF EXISTS `musicextension`;
CREATE TABLE `musicextension`  (
  `extension` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  PRIMARY KEY (`extension`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of musicextension
-- ----------------------------
INSERT INTO `musicextension` VALUES ('audio');
INSERT INTO `musicextension` VALUES ('FOLDER');
INSERT INTO `musicextension` VALUES ('mp3');

-- ----------------------------
-- Table structure for musicfile
-- ----------------------------
DROP TABLE IF EXISTS `musicfile`;
CREATE TABLE `musicfile`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_sk` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `size` int NULL DEFAULT 0,
  `extension` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT '',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `state` tinyint NULL DEFAULT 1,
  `length` int NULL DEFAULT 0,
  `bit_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_m`(`creator`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `parent`(`parent`, `name`) USING BTREE,
  INDEX `fk_m_ext`(`extension`) USING BTREE,
  CONSTRAINT `fk_m` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_m_ext` FOREIGN KEY (`extension`) REFERENCES `musicextension` (`extension`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of musicfile
-- ----------------------------
INSERT INTO `musicfile` VALUES (1, '2e312caa-1312-5555-84c9-110702067e0b', 'DanNguyen7', 654632113, 'mp3', '', 'thanhtri98', '2021-05-01', 1, 254, '320kpbs');
INSERT INTO `musicfile` VALUES (2, '2e312caa-1312-5555-84c9-122702067e0b', 'DapVoCayDan', 13144234, 'mp3', '', 'thanhtri98', '2021-04-28', 1, 211, '320kpbs');
INSERT INTO `musicfile` VALUES (3, '2e312caa-27d4-4925-84c9-110702067e0b', 'DanNguyen', 0, 'FOLDER', '', 'thanhtri98', '2021-05-01', 1, 0, NULL);
INSERT INTO `musicfile` VALUES (4, '2e312caa-27d4-4925-84c9-310702067e0b', 'SongGioCuocDoi22', 53453345, 'mp3', 'DanNguyen', 'thanhtri98', '2021-05-01', 1, 123, '128kpbs');
INSERT INTO `musicfile` VALUES (5, '2e312caa-27d4-5555-84c9-110702067e0b', 'cat-bui-cuoc-doi', 6455645, 'mp3', 'DanNguyen', 'thanhtri98', '2021-05-01', 1, 443, '128kpbs');
INSERT INTO `musicfile` VALUES (6, '2e312caa-27d4-5555-84c9-110882067e0b', 'Hayqua', 0, 'FOLDER', 'DanNguyen', 'thanhtri98', '2021-05-01', 1, 0, NULL);
INSERT INTO `musicfile` VALUES (7, '2e312caa-27d4-5555-84c9-165702067e0b', 'DapMoCuocTinh', 53353454, 'mp3', 'Hayqua', 'thanhtri98', '2021-04-27', 1, 212, '320kpbs');
INSERT INTO `musicfile` VALUES (14, 'dggrgrrhrhr', 'DanNguyen', 0, 'FOLDER', 'Hayqua', 'thanhtri98', '2021-04-27', 1, 0, NULL);
INSERT INTO `musicfile` VALUES (15, 'b5c0c942-3d40-4e8e-bde2-e1cae80ff2e3', 'DapVoCayDan22', 654632113, 'mp3', '', 'thanhtri98', '2021-04-30', 1, 254, '320kpbs');
INSERT INTO `musicfile` VALUES (16, '47ced547-fd66-4299-9e52-e71771ad57d8', 'DapVoCayDan22', 654632113, 'mp3', 'Hayqua', 'thanhtri98', '2021-04-30', 1, 254, '320kpbs');

-- ----------------------------
-- Table structure for musicfileshared
-- ----------------------------
DROP TABLE IF EXISTS `musicfileshared`;
CREATE TABLE `musicfileshared`  (
  `file_id` int NOT NULL,
  `owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  PRIMARY KEY (`file_id`, `owner`, `receiver`) USING BTREE,
  INDEX `fk_o_u`(`owner`) USING BTREE,
  INDEX `fk_r_u`(`receiver`) USING BTREE,
  CONSTRAINT `fk_file_id` FOREIGN KEY (`file_id`) REFERENCES `musicfile` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_o_u` FOREIGN KEY (`owner`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_r_u` FOREIGN KEY (`receiver`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of musicfileshared
-- ----------------------------
INSERT INTO `musicfileshared` VALUES (2, 'thanhtri98', 'kienga');

-- ----------------------------
-- Table structure for paths
-- ----------------------------
DROP TABLE IF EXISTS `paths`;
CREATE TABLE `paths`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paths
-- ----------------------------
INSERT INTO `paths` VALUES (1, 'C:\\ServerLocal\\Musics');
INSERT INTO `paths` VALUES (2, 'C:\\ServerLocal\\Pictures');
INSERT INTO `paths` VALUES (3, 'C:\\ServerLocal\\Videos');

-- ----------------------------
-- Table structure for pictureextension
-- ----------------------------
DROP TABLE IF EXISTS `pictureextension`;
CREATE TABLE `pictureextension`  (
  `extension` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  PRIMARY KEY (`extension`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pictureextension
-- ----------------------------
INSERT INTO `pictureextension` VALUES ('FOLDER');
INSERT INTO `pictureextension` VALUES ('jpeg');
INSERT INTO `pictureextension` VALUES ('jpg');
INSERT INTO `pictureextension` VALUES ('png');

-- ----------------------------
-- Table structure for picturefile
-- ----------------------------
DROP TABLE IF EXISTS `picturefile`;
CREATE TABLE `picturefile`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_sk` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `size` int NULL DEFAULT 0,
  `extension` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT '',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `state` tinyint NULL DEFAULT 1,
  `width` int NULL DEFAULT 0,
  `height` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_p`(`creator`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `fk_p_parent`(`parent`) USING BTREE,
  INDEX `fk_p_ext`(`extension`) USING BTREE,
  CONSTRAINT `fk_p` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_p_ext` FOREIGN KEY (`extension`) REFERENCES `pictureextension` (`extension`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of picturefile
-- ----------------------------
INSERT INTO `picturefile` VALUES (1, 'pslpsldsldlspld', 'triproquane2', 123123, 'jpeg', '', 'thanhtri98', '2021-05-13', 1, 324, 666);
INSERT INTO `picturefile` VALUES (2, 'pslpsldsldlspld', 'triproquane', 82189, 'jpg', '', 'thanhtri98', '2021-04-15', 1, 365, 1024);
INSERT INTO `picturefile` VALUES (3, 'ijflkdlklkdlkslk', 'Ahi hi đồ ngốc', 84564, 'png', '', 'thanhtri98', '2021-04-17', 1, 2111, 3655);
INSERT INTO `picturefile` VALUES (4, 'xmkxmmmxmlxm', 'Hinh Đệp', 0, 'FOLDER', '', 'thanhtri98', '2021-04-26', 1, 0, 0);
INSERT INTO `picturefile` VALUES (5, 'iojfodjsojdoskj', 'cái lon coca', 1354, 'jpeg', '', 'thanhtri98', '2021-04-04', 1, 5454, 4646);
INSERT INTO `picturefile` VALUES (6, 'soksdllsf21k3l2l322k', 'a', 123, 'jpg', 'Hinh Đệp', 'thanhtri98', '2021-04-03', 1, 5213, 1321);
INSERT INTO `picturefile` VALUES (7, '244246432441223', 'abc', 5466, 'jpg', 'Hinh Đệp', 'thanhtri98', '2021-04-03', 1, 8545, 6466);
INSERT INTO `picturefile` VALUES (8, '2634423413231425344', 'hình 69k', 56465, 'png', 'Hinh Đệp', 'thanhtri98', '2021-04-08', 1, 545, 133);
INSERT INTO `picturefile` VALUES (9, '112313121122w2e12', 'aaaaa', 0, 'FOLDER', 'Hinh Đệp', 'thanhtri98', '2021-04-03', 0, 0, 0);
INSERT INTO `picturefile` VALUES (10, '6124514243g14g431g41g', 'cồn cát bự quá', 6532, 'png', 'aaaaa', 'thanhtri98', '2021-04-05', 0, 1221, 7754);

-- ----------------------------
-- Table structure for picturefileshared
-- ----------------------------
DROP TABLE IF EXISTS `picturefileshared`;
CREATE TABLE `picturefileshared`  (
  `file_id` int NOT NULL,
  `owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  PRIMARY KEY (`file_id`, `owner`, `receiver`) USING BTREE,
  INDEX `fk_o_u`(`owner`) USING BTREE,
  INDEX `fk_r_u`(`receiver`) USING BTREE,
  CONSTRAINT `fk_o_u_p` FOREIGN KEY (`owner`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_p_file_id` FOREIGN KEY (`file_id`) REFERENCES `picturefile` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_r_u_p` FOREIGN KEY (`receiver`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of picturefileshared
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT 0,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('kienga', '123456', 'Ha Ngoc Kien', 'kiengaga@gmail.com', 0);
INSERT INTO `user` VALUES ('thanhtri98', '123456', 'Vo Thanh Tri', NULL, 1);

-- ----------------------------
-- Table structure for videoextension
-- ----------------------------
DROP TABLE IF EXISTS `videoextension`;
CREATE TABLE `videoextension`  (
  `extension` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  PRIMARY KEY (`extension`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of videoextension
-- ----------------------------
INSERT INTO `videoextension` VALUES ('FOLDER');
INSERT INTO `videoextension` VALUES ('mp4');

-- ----------------------------
-- Table structure for videofile
-- ----------------------------
DROP TABLE IF EXISTS `videofile`;
CREATE TABLE `videofile`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_sk` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `size` int NULL DEFAULT 0,
  `extension` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT '',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `modify_date` date NULL DEFAULT NULL,
  `state` tinyint NULL DEFAULT 1,
  `length` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_v`(`creator`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `fk_parent_v`(`parent`) USING BTREE,
  INDEX `fk_v_ext`(`extension`) USING BTREE,
  CONSTRAINT `fk_v` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_v_ext` FOREIGN KEY (`extension`) REFERENCES `videoextension` (`extension`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of videofile
-- ----------------------------

-- ----------------------------
-- Table structure for videofileshared
-- ----------------------------
DROP TABLE IF EXISTS `videofileshared`;
CREATE TABLE `videofileshared`  (
  `file_id` int NOT NULL,
  `owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  PRIMARY KEY (`file_id`, `owner`, `receiver`) USING BTREE,
  INDEX `fk_o_u`(`owner`) USING BTREE,
  INDEX `fk_r_u`(`receiver`) USING BTREE,
  CONSTRAINT `fk_o_u_v` FOREIGN KEY (`owner`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_r_u_v` FOREIGN KEY (`receiver`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_v_file_id` FOREIGN KEY (`file_id`) REFERENCES `videofile` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of videofileshared
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

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

 Date: 24/05/2021 02:00:02
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
  `total_size` bigint NULL DEFAULT 0,
  `price` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accountpackage
-- ----------------------------
INSERT INTO `accountpackage` VALUES (1, 'Normal', 16106127360, 0);
INSERT INTO `accountpackage` VALUES (2, 'Vip 1', 53687091200, 169000);
INSERT INTO `accountpackage` VALUES (3, 'Vip 2', 268435456000, 1690000);

-- ----------------------------
-- Table structure for billhistory
-- ----------------------------
DROP TABLE IF EXISTS `billhistory`;
CREATE TABLE `billhistory`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `pkg_id` int NOT NULL,
  `createDate` date NULL DEFAULT NULL,
  `expireDate` date NULL DEFAULT NULL,
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
  `modify_date` datetime NULL DEFAULT NULL,
  `state` tinyint NULL DEFAULT 1,
  `length` int NULL DEFAULT 0,
  `bit_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `parent`(`parent`, `name`) USING BTREE,
  INDEX `fk_m`(`creator`) USING BTREE,
  INDEX `fk_m_ext`(`extension`) USING BTREE,
  CONSTRAINT `fk_m` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_ext` FOREIGN KEY (`extension`) REFERENCES `musicextension` (`extension`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of musicfile
-- ----------------------------
INSERT INTO `musicfile` VALUES (2, '2e312caa-1312-5555-84c9-122702067e0b', 'DapVoCayDan', 13144234, 'mp3', '', 'thanhtri98', '2021-05-15 15:35:48', 1, 211, '320kpbs');
INSERT INTO `musicfile` VALUES (3, '2e312caa-27d4-4925-84c9-110702067e0b', 'DanNguyen999', 0, 'FOLDER', '', 'thanhtri98', '2021-05-15 16:10:42', 1, 0, NULL);
INSERT INTO `musicfile` VALUES (4, '2e312caa-27d4-4925-84c9-310702067e0b', 'SongGioCuocDoi22adasd', 53453345, 'mp3', 'DanNguyen999', 'thanhtri98', '2021-05-19 16:15:50', 1, 123, '128kpbs');
INSERT INTO `musicfile` VALUES (5, '2e312caa-27d4-5555-84c9-110702067e0b', 'cat-bui-cuoc-doi', 6455645, 'mp3', 'DanNguyen999', 'thanhtri98', '2021-05-15 16:10:42', 1, 443, '128kpbs');
INSERT INTO `musicfile` VALUES (6, '2e312caa-27d4-5555-84c9-110882067e0b', 'Hayqua', 0, 'FOLDER', 'DanNguyen999', 'thanhtri98', '2021-05-15 16:10:42', 1, 0, NULL);
INSERT INTO `musicfile` VALUES (7, '2e312caa-27d4-5555-84c9-165702067e0b', 'DapMoCuocTinh', 53353454, 'mp3', 'Hayqua', 'thanhtri98', '2021-04-27 00:00:00', 1, 212, '320kpbs');
INSERT INTO `musicfile` VALUES (14, 'dggrgrrhrhr', 'DanNguyen', 0, 'FOLDER', 'Hayqua', 'thanhtri98', '2021-04-27 00:00:00', 1, 0, NULL);
INSERT INTO `musicfile` VALUES (15, 'b5c0c942-3d40-4e8e-bde2-e1cae80ff2e3', 'DapVoCayDan22', 654632113, 'mp3', '', 'kienga', '2021-04-30 00:00:00', 1, 254, '320kpbs');
INSERT INTO `musicfile` VALUES (16, '47ced547-fd66-4299-9e52-e71771ad57d8', 'DapVoCayDan', 654632113, 'mp3', 'Hayqua', 'thanhtri98', '2021-04-30 00:00:00', 1, 254, '320kpbs');
INSERT INTO `musicfile` VALUES (17, '1ed46095-c3e1-4271-80c8-948748f59d55', 'DanNguyen27', 654632113, 'mp3', '', 'kienga', '2021-05-02 00:00:00', 1, 254, '320kpbs');
INSERT INTO `musicfile` VALUES (18, '29ea1131-6e89-417d-9abe-c5c76fd81518', 'e259e4ca-6662-428d-99b7-43a8b934834c', 3814448, 'mp3', '', 'thanhtri98', '2021-05-19 16:15:18', 1, 232, NULL);
INSERT INTO `musicfile` VALUES (19, 'f3dccadd-963a-4d09-ad48-2bb586ba7e4b', 'AnhThuongEmMa-AnhRongTvkAnhHao-6981836', 3814448, 'mp3', '', 'thanhtri98', '2021-05-19 16:15:18', 1, 232, NULL);
INSERT INTO `musicfile` VALUES (20, '3e8624b5-45f2-41f6-9167-553384d4c8f2', '(1) DapVoCayDan', 3814448, 'mp3', '', 'thanhtri98', '2021-05-19 16:15:18', 1, 232, NULL);
INSERT INTO `musicfile` VALUES (21, 'c7dc4247-0c14-45fc-ad5b-6ca065c3051f', 'SongGioCuocDoi', 3814448, 'mp3', 'DanNguyen999', 'thanhtri98', '2021-05-19 16:15:38', 1, 232, NULL);
INSERT INTO `musicfile` VALUES (22, 'adwefefssdfs', 'new parent ne', 0, 'FOLDER', '', 'thanhtri98', '2021-05-06 02:10:04', 0, 0, NULL);
INSERT INTO `musicfile` VALUES (27, '2e312caa-27d4-4925-84c9-110702067e0b', 'DanNguyen999', 0, 'FOLDER', '', 'thanhtri98', '2021-05-15 16:10:42', 1, 0, NULL);
INSERT INTO `musicfile` VALUES (28, 'b5c0c942-3d40-4e8e-bde2-e1cae80ff2e3', 'DapVoCayDan22', 654632113, 'mp3', '', 'kienga', '2021-04-30 00:00:00', 1, 254, '320kpbs');
INSERT INTO `musicfile` VALUES (29, '2e312caa-1312-5555-84c9-122702067e0b', 'DapVoCayDan', 13144234, 'mp3', 'new parent ne', 'thanhtri98', '2021-05-15 15:35:48', 0, 211, '320kpbs');
INSERT INTO `musicfile` VALUES (30, '2e312caa-1312-5555-84c9-122702067e0b', '(1) DapVoCayDan', 13144234, 'mp3', 'new parent ne', 'thanhtri98', '2021-05-15 15:35:48', 0, 211, '320kpbs');
INSERT INTO `musicfile` VALUES (31, '29ea1131-6e89-417d-9abe-c5c76fd81518', '(1) e259e4ca-6662-428d-99b7-43a8b934834', 3814448, 'mp3', '', 'thanhtri98', '2021-05-21 12:52:56', 1, 232, NULL);
INSERT INTO `musicfile` VALUES (32, '29ea1131-6e89-417d-9abe-c5c76fd81518', 'Copy(1) e259e4ca-6662-428d-99b7-43a8b934834c', 3814448, 'mp3', '', 'thanhtri98', '2021-05-19 16:15:18', 1, 232, NULL);

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

-- ----------------------------
-- Table structure for otp_code
-- ----------------------------
DROP TABLE IF EXISTS `otp_code`;
CREATE TABLE `otp_code`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `code` int NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  `expire_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_opt_u`(`uuid`) USING BTREE,
  INDEX `fk_em_us`(`email`) USING BTREE,
  CONSTRAINT `fk_em_us` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of otp_code
-- ----------------------------
INSERT INTO `otp_code` VALUES (63, '68c39537-3366-408d-80c8-56b5e7069169', 643910, NULL, '2021-05-21 22:30:38');
INSERT INTO `otp_code` VALUES (64, '3948be34-6593-4488-86af-26015960bd2b', 997423, NULL, '2021-05-21 22:30:46');
INSERT INTO `otp_code` VALUES (65, 'e04510ad-dac7-457a-b64b-16692b2a669e', 873844, NULL, '2021-05-21 22:35:10');
INSERT INTO `otp_code` VALUES (66, 'c2a703d1-4c8c-455d-a5b1-024777a2564d', 710232, NULL, '2021-05-21 22:42:39');

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
INSERT INTO `pictureextension` VALUES ('svg');

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
  `modify_date` datetime NULL DEFAULT NULL,
  `state` tinyint NULL DEFAULT 1,
  `width` int NULL DEFAULT 0,
  `height` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_p`(`creator`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `fk_p_ext`(`extension`) USING BTREE,
  INDEX `fk_p_parent`(`parent`) USING BTREE,
  CONSTRAINT `fk_p` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_p_ext` FOREIGN KEY (`extension`) REFERENCES `pictureextension` (`extension`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 161 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of picturefile
-- ----------------------------
INSERT INTO `picturefile` VALUES (1, '1Lx58513Ger6j53vp1ztAdqvAotvPtmk2m', 'Mungos mungo', 0, 'FOLDER', '', 'thanhtri98', '2020-10-21 20:59:35', 0, 0, 0);
INSERT INTO `picturefile` VALUES (2, '15buyoS5yP6JfEQgUsSQq8MFPLLFSiw61J', 'Album VIP', 0, 'FOLDER', '', 'thanhtri98', '2021-03-15 14:16:11', 1, 0, 0);
INSERT INTO `picturefile` VALUES (3, '1GqYXEncqS1deo3qiD4Hv6GYTTkRv84Mzy', 'Columba livia', 5288756, 'png', '', 'thanhtri98', '2020-05-17 13:05:02', 1, 2442, 2186);
INSERT INTO `picturefile` VALUES (4, '1EjZczMR6S1msSf3X9aMY4Ecm5hY1pFtg4', 'Limosa haemastica', 3507177, 'jpg', 'Mungos mungo', 'thanhtri98', '2021-04-07 18:45:30', 0, 1388, 2197);
INSERT INTO `picturefile` VALUES (5, '1C2Zrw1G1wTg8HdEyX3p5KyHsN5gvcArHK', 'Spermophilus parryii', 4062160, 'jpg', 'Mungos mungo', 'thanhtri98', '2021-02-05 02:24:30', 0, 1546, 1697);
INSERT INTO `picturefile` VALUES (142, '8133e4a5-7ffe-4c7a-b191-0671edfefdfd', 'pexels-creativebin-135940', 177334, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:29:57', 1, 1939, 1374);
INSERT INTO `picturefile` VALUES (143, '72fc2432-c56c-4a03-b295-e188a2a5e23d', 'pexels-jonathan-petersson-1237119', 1723111, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:04', 1, 3008, 2000);
INSERT INTO `picturefile` VALUES (144, 'e4a20287-7b67-45c8-a2eb-2f5db6ac7110', 'pexels-jill-wellington-40192', 1087038, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:13', 1, 3962, 2641);
INSERT INTO `picturefile` VALUES (145, '9f531dc2-9117-4092-b9ba-bb2651db5b25', 'pexels-david-besh-884788', 1718462, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:13', 1, 4608, 3072);
INSERT INTO `picturefile` VALUES (146, '4b4fb886-b6f4-42c9-98d1-2a4feba00cdf', 'pexels-chevanon-photography-325044', 670141, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:17', 1, 4879, 3253);
INSERT INTO `picturefile` VALUES (147, '984e1a7a-5055-44dd-bb74-e8e2f409a1c4', 'pexels-pixabay-414061', 739481, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:20', 1, 2001, 1065);
INSERT INTO `picturefile` VALUES (148, 'ae6711fc-e2ee-41d0-810a-ed5c2d47920c', 'pexels-jovana-nesic-593655', 2373243, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:20', 1, 4608, 3072);
INSERT INTO `picturefile` VALUES (149, 'b3b3584b-5130-4d61-8885-938f31e50cd6', 'pexels-dominic-m-contreras-1188214', 2970009, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:23', 1, 4996, 3331);
INSERT INTO `picturefile` VALUES (150, '1271648c-c642-46bd-bc40-24fbff3bfad0', 'pexels-pixabay-207247', 1085073, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:24', 1, 3861, 2574);
INSERT INTO `picturefile` VALUES (151, '15fe2629-2ac2-4ce4-96d4-1b5dbeca6daf', 'pexels-pixabay-414102', 228758, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:25', 1, 1999, 1333);
INSERT INTO `picturefile` VALUES (152, '7f22110b-9a99-455c-a073-3cf44b45fa83', 'pexels-pixabay-460635', 1040430, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:28', 1, 2240, 1680);
INSERT INTO `picturefile` VALUES (153, '740eb02a-3b3c-4241-a07c-1a44877ee5c8', 'pexels-pixabay-45853', 2257492, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:30', 1, 5315, 3555);
INSERT INTO `picturefile` VALUES (154, '317c4d4f-7d3e-44f5-b6c0-cbff5ed30b24', 'pexels-pixabay-235734', 3946908, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:42', 1, 5184, 3456);
INSERT INTO `picturefile` VALUES (155, '37ccd378-126d-4b6f-9c4b-cf03beea4e60', 'pexels-tuấn-kiệt-jr-1386604', 390537, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:46', 1, 1280, 1791);
INSERT INTO `picturefile` VALUES (156, 'f38625ef-0d15-4322-9fd4-e954f0e1777d', 'pexels-pixabay-415708', 1861047, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:49', 1, 5184, 3456);
INSERT INTO `picturefile` VALUES (157, '96eeb8f7-d406-40d4-9860-b64fad6be18f', 'pexels-wow', 3288169, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:32:25', 1, 5472, 3648);
INSERT INTO `picturefile` VALUES (158, 'c1cfa4d8-2013-4936-a76e-2890e46bc849', 'pexels-tuấn-kiệt-jr-1382731', 4636129, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:55', 1, 3840, 5760);
INSERT INTO `picturefile` VALUES (159, '35fef75d-b8c3-49fe-807e-b55f278b71b0', 'pexels-tuấn-kiệt-jr-1321909', 6563780, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:30:58', 1, 3840, 5760);
INSERT INTO `picturefile` VALUES (160, '56e110ce-0a13-4335-8171-95633fc7430f', 'pexels-tuấn-kiệt-jr-1308881', 6304130, 'jpg', 'Album VIP', 'thanhtri98', '2021-05-23 02:31:00', 1, 6720, 4480);

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
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_USER');
INSERT INTO `role` VALUES (2, 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 0,
  `acc_pkg` int NULL DEFAULT 1,
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `email`(`email`) USING BTREE,
  INDEX `fk_acc_pkg`(`acc_pkg`) USING BTREE,
  CONSTRAINT `fk_acc_pkg` FOREIGN KEY (`acc_pkg`) REFERENCES `accountpackage` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('adu123', '$2a$10$ZQ3EJlosFq1Ivap8rFv1LupLzgi6SCEpD3eXm9L/noARaayU1lu/e', 'vothanhtri9xpro@gmail.com', 'vothanhtri9xpro@gmail.com', 1, 1);
INSERT INTO `user` VALUES ('adu123s', '$2a$10$7O4gg3cveGjJabhB35FBX.NiMMJCq4fphCH/H4OVHmSv6tk4wEesa', 'vothanhtri9xpro@gmail.com', 's', 1, NULL);
INSERT INTO `user` VALUES ('kienga', '$2a$04$GYGsaJj9l6kH2GikK6QVzO0v3sOCxt3vdkiA2/tcoSw8erI85ZYDG', 'Ha Ngoc Kien', 'kiengaga@gmail.com', 1, 1);
INSERT INTO `user` VALUES ('thanhtri98', '$2a$04$GYGsaJj9l6kH2GikK6QVzO0v3sOCxt3vdkiA2/tcoSw8erI85ZYDG', 'Vo Thanh Tri', 'tri9xprovip@gmail.com', 1, 1);
INSERT INTO `user` VALUES ('trine', '$2a$10$Xay1qDzlgNISkbl7.FupseR.2kQuL.I8kI8WJScmy40jGI8NOK2au', 'vothanhtri9xpro@gmail.com', 'vothanhtri9xsspro@gmail.com', 1, 2);
INSERT INTO `user` VALUES ('trine123', '$2a$10$.4nkjgKgWMVz0hvIhmMzKO8LMcni4RvDJ5oa3Qw4R4iOwvQtHh90K', 'a2131@gmai.com', 'a2131@gmai.com', 1, 3);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `fk_r_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_r_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_r_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('adu123', 1);
INSERT INTO `user_role` VALUES ('adu123s', 1);
INSERT INTO `user_role` VALUES ('kienga', 1);
INSERT INTO `user_role` VALUES ('thanhtri98', 1);
INSERT INTO `user_role` VALUES ('thanhtri98', 2);
INSERT INTO `user_role` VALUES ('trine', 1);
INSERT INTO `user_role` VALUES ('trine123', 1);

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
  `modify_date` datetime NULL DEFAULT NULL,
  `state` tinyint NULL DEFAULT 1,
  `length` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_v`(`creator`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `fk_parent_v`(`parent`) USING BTREE,
  INDEX `fk_v_ext`(`extension`) USING BTREE,
  CONSTRAINT `fk_v` FOREIGN KEY (`creator`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_v_ext` FOREIGN KEY (`extension`) REFERENCES `videoextension` (`extension`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of videofile
-- ----------------------------
INSERT INTO `videofile` VALUES (1, 'cbc4c67f-f44d-4d8a-8f53-4efffe48adc3', 'DapVoCayDan1saaas', 654632113, 'mp4', '', 'thanhtri98', '2021-05-15 00:00:00', 1, 254);
INSERT INTO `videofile` VALUES (2, '6a53a00f-992c-432e-a8c7-c256ef40207f', 'DapVoCayDan', 13144234, 'mp4', '', 'thanhtri98', '2021-05-15 00:00:00', 1, 211);
INSERT INTO `videofile` VALUES (3, '8c751f35-021e-4710-9349-f2699d46462e', 'DanNguyen999', 0, 'FOLDER', '', 'thanhtri98', '2021-05-15 00:00:00', 1, 0);
INSERT INTO `videofile` VALUES (4, 'c353d666-be75-47d6-b5fd-9c35287bd1b5', 'SongGioCuocDoi22', 53453345, 'mp4', 'DanNguyen999', 'thanhtri98', '2021-05-15 00:00:00', 0, 123);
INSERT INTO `videofile` VALUES (5, '472c4cef-5b52-4746-8cc4-e092ce3a041a', 'cat-bui-cuoc-doi', 6455645, 'mp4', 'DanNguyen999', 'thanhtri98', '2021-05-15 00:00:00', 1, 443);
INSERT INTO `videofile` VALUES (6, 'b6064ad3-d2ae-496f-a446-248b75723104', 'Hayqua', 0, 'FOLDER', 'DanNguyen999', 'thanhtri98', '2021-05-15 00:00:00', 0, 0);
INSERT INTO `videofile` VALUES (7, ' 8d114045-b4a9-4180-bdf0-c693d1790aa7', 'DapMoCuocTinh', 53353454, 'mp4', 'Hayqua', 'thanhtri98', '2021-04-27 00:00:00', 0, 212);
INSERT INTO `videofile` VALUES (14, ' 7b27f61b-9c08-4413-9fd3-b27a5968c25e', 'DanNguyen', 0, 'FOLDER', 'Hayqua', 'thanhtri98', '2021-04-27 00:00:00', 0, 0);
INSERT INTO `videofile` VALUES (15, '5c3928ea-1efd-455b-89bd-e9cb130d086e', 'DapVoCayDan22', 654632113, 'mp4', '', 'kienga', '2021-04-30 00:00:00', 1, 254);
INSERT INTO `videofile` VALUES (16, '8b565e2b-8a1c-4fa8-93bf-11a84de212c7', 'DapVoCayDan', 654632113, 'mp4', 'Hayqua', 'thanhtri98', '2021-04-30 00:00:00', 0, 254);
INSERT INTO `videofile` VALUES (17, '92b78942-60f2-4ee5-aa64-aa997c15b080', 'DanNguyen27', 654632113, 'mp4', '', 'kienga', '2021-05-02 00:00:00', 1, 254);
INSERT INTO `videofile` VALUES (18, 'ebb01ed0-a80e-4c43-8e67-700a47bae443', 'Quên Một Người Từng Yêu _ Châu Khải Phong _ Official Music Video', 58033614, 'mp4', '', 'thanhtri98', '2021-05-19 00:00:00', 1, 637);
INSERT INTO `videofile` VALUES (19, '7bfd7e02-19a1-46fc-a9d9-67e4da5d5985', 'abc', 2147483647, 'mp4', '', 'thanhtri98', '2021-05-19 13:08:44', 1, 274);
INSERT INTO `videofile` VALUES (22, 'adwefefssdfs', 'new parent ne', 0, 'FOLDER', 'DanNguyen', 'thanhtri98', '2021-05-06 02:10:04', 0, 0);
INSERT INTO `videofile` VALUES (88, '98481eab-1540-4524-ae8b-34cf5d8ac88c', 'Copy(1) Quên Một Người Từng Yêu _ Châu Khải Phong _ Official Music Video', 58033614, 'mp4', 'DanNguyen', 'thanhtri98', '2021-05-21 22:24:15', 0, 637);
INSERT INTO `videofile` VALUES (89, 'e50b36ca-05f2-491b-9bab-6dfed40881bb', 'absssc', 21470155, 'mp4', '', 'adu123', '2021-05-21 22:48:08', 1, 274);
INSERT INTO `videofile` VALUES (90, '24492306-30b3-445d-83fa-8772423ebd06', 'Copy(2) Quên Một Người Từng Yêu _ Châu Khải Phong _ Official Music Video', 58033614, 'mp4', '', 'thanhtri98', '2021-05-22 12:37:56', 1, 637);
INSERT INTO `videofile` VALUES (91, 'b7378620-438d-445c-b080-a91d752afba5', 'Copy(1) abc', 21470155, 'mp4', 'new parent ne', 'thanhtri98', '2021-05-22 22:40:07', 0, 274);
INSERT INTO `videofile` VALUES (92, '55ca99c3-e4d5-481a-8bb8-90515611416e', 'Copy(3) Quên Một Người Từng Yêu _ Châu Khải Phong _ Official Music Video', 58033614, 'mp4', 'DanNguyen111', 'thanhtri98', '2021-05-23 12:48:24', 0, 637);
INSERT INTO `videofile` VALUES (93, '8c751f35-021e-4710-9349-f2699d46462e', 'DanNguyen111', 0, 'FOLDER', 'DanNguyen999', 'thanhtri98', '2021-05-15 00:00:00', 0, 0);

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

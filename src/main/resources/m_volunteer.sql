
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_province` varchar(255) NOT NULL COMMENT '地址 省',
  `address_city` varchar(255) NOT NULL COMMENT '地址 市',
  `address_district` varchar(255) NOT NULL COMMENT '地址 区',
  `address_street` varchar(255) NOT NULL COMMENT '地址 街道或路',
  `address` varchar(255) NOT NULL COMMENT '地址 详细',
  `coord_long` double NOT NULL COMMENT '经度',
  `coord_lat` double NOT NULL COMMENT '纬度',
  `service_type` varchar(255) NOT NULL,
  `service_object` varchar(255) NOT NULL COMMENT '服务对象（儿童、老人……）',
  `recruit_time` date NOT NULL,
  `recruit_person_number` int(11) NOT NULL,
  `description` text NOT NULL,
  `time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `volunteer_security` text NOT NULL,
  `principal_contact` varchar(255) NOT NULL,
  `activity_status_id` int(11) NOT NULL COMMENT '活动状态',
  `working_hours` double NOT NULL COMMENT '记工时数',
  `sign_in_start` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '签到开始时间',
  `sign_in_end` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sign_out_start` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sign_out_end` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `picture` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `activity_activity_status_id` (`activity_status_id`),
  CONSTRAINT `activity_activity_status_id` FOREIGN KEY (`activity_status_id`) REFERENCES `activity_status` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------

-- ----------------------------
-- Table structure for activity_status
-- ----------------------------
DROP TABLE IF EXISTS `activity_status`;
CREATE TABLE `activity_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_status
-- ----------------------------

-- ----------------------------
-- Table structure for activity_user
-- ----------------------------
DROP TABLE IF EXISTS `activity_user`;
CREATE TABLE `activity_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `activity_user_status_id` int(11) NOT NULL,
  `sigh_in_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sigh_out_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `star` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_user_activity_id` (`activity_id`),
  KEY `activity_user_user_id` (`user_id`),
  KEY `activity_user_activity_user_status_id` (`activity_user_status_id`),
  CONSTRAINT `activity_user_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE,
  CONSTRAINT `activity_user_activity_user_status_id` FOREIGN KEY (`activity_user_status_id`) REFERENCES `activity_user_status` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `activity_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_user
-- ----------------------------

-- ----------------------------
-- Table structure for activity_user_status
-- ----------------------------
DROP TABLE IF EXISTS `activity_user_status`;
CREATE TABLE `activity_user_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_user_status
-- ----------------------------

-- ----------------------------
-- Table structure for certificate
-- ----------------------------
DROP TABLE IF EXISTS `certificate`;
CREATE TABLE `certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `certificate_status_id` int(11) NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `certificate_certificate_status_id` (`certificate_status_id`),
  CONSTRAINT `certificate_certificate_status_id` FOREIGN KEY (`certificate_status_id`) REFERENCES `certificate_status` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of certificate
-- ----------------------------

-- ----------------------------
-- Table structure for certificate_status
-- ----------------------------
DROP TABLE IF EXISTS `certificate_status`;
CREATE TABLE `certificate_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of certificate_status
-- ----------------------------

-- ----------------------------
-- Table structure for organization_info
-- ----------------------------
DROP TABLE IF EXISTS `organization_info`;
CREATE TABLE `organization_info` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '组织名称',
  `booking_status` int(11) NOT NULL COMMENT '登记状态',
  `organization_code` varchar(255) DEFAULT NULL COMMENT '组织机构代码',
  `social_organization_type` varchar(255) DEFAULT NULL COMMENT '社会组织类型',
  `governing_body_name` varchar(255) DEFAULT NULL COMMENT '主管单位名称',
  `address_province` varchar(255) NOT NULL COMMENT '地址 省',
  `address_city` varchar(255) NOT NULL COMMENT '地址 市',
  `address_district` varchar(255) NOT NULL COMMENT '地址 区',
  `address_street` varchar(255) NOT NULL COMMENT '地址 街道或路',
  `address` varchar(255) NOT NULL COMMENT '地址 详细',
  `coord_long` double NOT NULL COMMENT '经度',
  `coord_lat` double NOT NULL COMMENT '纬度',
  `zip_code` varchar(255) NOT NULL COMMENT '邮编',
  `founding_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '成立日期',
  `volunteer_number` int(11) NOT NULL,
  `service_type` text NOT NULL COMMENT '服务类别',
  `principal_name` varchar(255) NOT NULL,
  `principal_telephone` varchar(255) NOT NULL,
  `principal_telephone_public` int(255) NOT NULL COMMENT '联系电话是否公开',
  `principal_phone` varchar(255) NOT NULL,
  `principal_phone_public` int(11) NOT NULL,
  `principal_qq` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) NOT NULL DEFAULT '/public/img/default_avatar.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization_info
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hello', 'world', 'hello', '0');

-- ----------------------------
-- Table structure for volunteer_info
-- ----------------------------
DROP TABLE IF EXISTS `volunteer_info`;
CREATE TABLE `volunteer_info` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `id_card` varchar(255) NOT NULL,
  `sex` int(255) NOT NULL DEFAULT '0',
  `age` int(255) NOT NULL COMMENT '年龄',
  `birthday` date NOT NULL COMMENT '出生年月',
  `political_status` varchar(255) NOT NULL DEFAULT '' COMMENT '政治面貌',
  `address` varchar(255) NOT NULL COMMENT '详细地址',
  `address_province` varchar(255) NOT NULL COMMENT '省',
  `address_city` varchar(255) NOT NULL COMMENT '市',
  `service_category` varchar(255) NOT NULL COMMENT '服务类型',
  `working_hours` double NOT NULL COMMENT '志愿者工时',
  `avatar` varchar(255) NOT NULL DEFAULT 'public/img/default_avatar.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of volunteer_info
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;

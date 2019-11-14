/*
Navicat MySQL Data Transfer

Source Server         : 139.159.142.50重构运维云数据库
Source Server Version : 80015
Source Host           : 139.159.142.50:7002
Source Database       : chaisdb2

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-11-14 15:40:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cf_log_parameterlog
-- ----------------------------
DROP TABLE IF EXISTS `cf_log_parameterlog`;
CREATE TABLE `cf_log_parameterlog` (
  `pk_id` int(11) NOT NULL COMMENT '主键ID',
  `ti_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '终端ID',
  `hi_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '酒店ID',
  `p_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '参数ID',
  `p_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '参数类型',
  `p_category` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '参数类别',
  `p_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '参数名',
  `o_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作状态( I:新增,U:修改,D:删除)',
  `c_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='参数日志记录表 log record';

-- ----------------------------
-- Records of cf_log_parameterlog
-- ----------------------------

-- ----------------------------
-- Table structure for cf_log_warninglog
-- ----------------------------
DROP TABLE IF EXISTS `cf_log_warninglog`;
CREATE TABLE `cf_log_warninglog` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ti_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '终端ID',
  `monitor_time` datetime DEFAULT NULL COMMENT '时间',
  `log_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '日志压缩文件存储路径',
  `log_isdispose` int(11) DEFAULT NULL COMMENT '是否处理',
  `c_user_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='异常日志历史表  warning_logrecord';

-- ----------------------------
-- Records of cf_log_warninglog
-- ----------------------------
INSERT INTO `cf_log_warninglog` VALUES ('1', 'dc95cec7a247463ca303153667b5ff71', '2019-01-10 20:59:35', 'D:\\logs', '0', '1', '1', '1', '1', '2019-01-10 22:36:52', '2019-01-10 22:36:54', '0');
INSERT INTO `cf_log_warninglog` VALUES ('2', '3a63d11992ce431e83b04004b9afd910', '2019-01-11 09:36:35', 'D:\\logs', '0', '1', '超级管理员1', '1', '超级管理员1', '2018-11-27 00:00:00', '2018-11-27 00:00:00', '1');
INSERT INTO `cf_log_warninglog` VALUES ('3', '3a63d11992ce431e83b04004b9afd910', '2019-01-11 09:37:36', 'D:\\logs', '0', '1', '超级管理员1', '1', '超级管理员1', '2018-11-27 00:00:00', '2018-11-27 00:00:00', '1');


-- ----------------------------
-- Table structure for cf_pt_config
-- ----------------------------
DROP TABLE IF EXISTS `cf_pt_config`;
CREATE TABLE `cf_pt_config` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ti_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '终端ID',
  `temp_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '模板ID',
  `p_id` bigint(20) DEFAULT NULL COMMENT '参数ID',
  `p_value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '参数值',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='参数配置信息';

-- ----------------------------
-- Records of cf_pt_config
-- ----------------------------
INSERT INTO `cf_pt_config` VALUES ('3', '07090', '25ab4172290c403a9481c6c30ee5d9a6', '1031', '上海', null, null, null, null, '2019-11-07 13:53:46', '2019-11-07 15:40:06', '0');
INSERT INTO `cf_pt_config` VALUES ('4', '07090', '25ab4172290c403a9481c6c30ee5d9a6', '1032', '云南', null, null, null, null, '2019-11-07 13:53:51', '2019-11-07 15:40:06', '0');
INSERT INTO `cf_pt_config` VALUES ('5', '07090', '25ab4172290c403a9481c6c30ee5d9a6', '1033', '四川', null, null, null, null, '2019-11-07 13:53:51', '2019-11-07 15:40:07', '0');
INSERT INTO `cf_pt_config` VALUES ('6', '07090', '25ab4172290c403a9481c6c30ee5d9a6', '1034', '111', null, null, null, null, '2019-11-07 13:53:51', '2019-11-07 15:40:07', '0');
INSERT INTO `cf_pt_config` VALUES ('7', '07090', '25ab4172290c403a9481c6c30ee5d9a6', '1035', '222', null, null, null, null, '2019-11-07 13:53:51', '2019-11-07 15:40:07', '0');
INSERT INTO `cf_pt_config` VALUES ('8', '07090', '25ab4172290c403a9481c6c30ee5d9a6', '1036', '333', null, null, null, null, '2019-11-07 13:53:51', '2019-11-07 15:40:07', '0');
INSERT INTO `cf_pt_config` VALUES ('9', '07090', '25ab4172290c403a9481c6c30ee5d9a6', '1027', '666', null, null, null, null, '2019-11-07 13:53:51', '2019-11-07 15:40:07', '0');
INSERT INTO `cf_pt_config` VALUES ('30', '520000076161', 'a381c4246cfc4534948941fb81cdc6a2', '1028', 'wqe', null, null, null, null, '2019-11-11 15:50:57', '2019-11-11 16:03:04', '0');
INSERT INTO `cf_pt_config` VALUES ('31', '520000076161', 'a381c4246cfc4534948941fb81cdc6a2', '1029', '123', null, null, null, null, '2019-11-11 15:50:58', '2019-11-11 16:03:04', '0');
INSERT INTO `cf_pt_config` VALUES ('32', '520000076161', 'a381c4246cfc4534948941fb81cdc6a2', '1030', '123', null, null, null, null, '2019-11-11 15:50:58', '2019-11-11 16:03:04', '0');
INSERT INTO `cf_pt_config` VALUES ('33', '520000076161', '25ab4172290c403a9481c6c30ee5d9a6', '1031', 'test', null, null, null, null, '2019-11-11 15:51:37', '2019-11-11 16:02:20', '0');
INSERT INTO `cf_pt_config` VALUES ('34', '520000076161', '12c3ec2803914dc294a3d0c6c441ce9c', '1027', '123123', null, null, null, null, '2019-11-11 15:58:18', '2019-11-11 16:01:59', '0');
INSERT INTO `cf_pt_config` VALUES ('35', '520000076161', '12c3ec2803914dc294a3d0c6c441ce9c', '1028', '22222', null, null, null, null, '2019-11-11 15:58:18', '2019-11-11 16:01:59', '0');
INSERT INTO `cf_pt_config` VALUES ('36', '520000076161', '12c3ec2803914dc294a3d0c6c441ce9c', '1029', null, null, null, null, null, '2019-11-11 15:58:18', '2019-11-11 16:01:59', '0');
INSERT INTO `cf_pt_config` VALUES ('37', '520000076161', '12c3ec2803914dc294a3d0c6c441ce9c', '1030', null, null, null, null, null, '2019-11-11 15:58:18', '2019-11-11 16:01:59', '0');
INSERT INTO `cf_pt_config` VALUES ('38', '10000006486', '25ab4172290c403a9481c6c30ee5d9a6', '1031', 'aaa', null, null, null, null, '2019-11-11 16:03:48', '2019-11-11 16:16:07', '0');
INSERT INTO `cf_pt_config` VALUES ('39', '10000006486', '25ab4172290c403a9481c6c30ee5d9a6', '1032', 'bbb', null, null, null, null, '2019-11-11 16:03:48', '2019-11-11 16:16:07', '0');
INSERT INTO `cf_pt_config` VALUES ('40', '10000006486', '25ab4172290c403a9481c6c30ee5d9a6', '1033', 'ccc', null, null, null, null, '2019-11-11 16:03:48', '2019-11-11 16:16:07', '0');
INSERT INTO `cf_pt_config` VALUES ('41', '10000006486', '25ab4172290c403a9481c6c30ee5d9a6', '1034', 'ddd', null, null, null, null, '2019-11-11 16:03:48', '2019-11-11 16:16:08', '0');
INSERT INTO `cf_pt_config` VALUES ('42', '10000006486', '25ab4172290c403a9481c6c30ee5d9a6', '1035', 'eee', null, null, null, null, '2019-11-11 16:03:48', '2019-11-11 16:16:08', '0');


-- ----------------------------
-- Table structure for cf_pt_structure
-- ----------------------------
DROP TABLE IF EXISTS `cf_pt_structure`;
CREATE TABLE `cf_pt_structure` (
  `p_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '参数ID',
  `p_type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '参数类型',
  `p_category` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '参数类别',
  `p_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '参数名称',
  `p_name_cn` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `p_comment` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '参数注释',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标识',
  `is_dict` tinyint(4) DEFAULT NULL COMMENT '是否字典',
  `dict_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '字典类型',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1066 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='参数结构表';

-- ----------------------------
-- Records of cf_pt_structure
-- ----------------------------
INSERT INTO `cf_pt_structure` VALUES ('1027', '520607', '', 'PAYWAY', '支付方式', '支付方式,银联填写Union,工行填写Icbc', '1', 'admin', null, null, '2019-11-02 14:47:08', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1028', '520607', '520635', 'ICBCMERID', '工行商户号', '由工行提供，每家酒店不一样', '1', 'admin', null, null, '2019-11-02 14:48:22', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1029', '520607', '520635', 'ICBCSTORECODE', '工行e生活档案号', '由工行提供,每家酒店不一样', '1', 'admin', null, null, '2019-11-02 14:49:04', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1030', '520607', '520635', 'CARDEXPIRE', '银行卡有效期', '默认填写：4912', '1', 'admin', null, null, '2019-11-02 14:49:40', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1031', '520607', '520636', 'UNIONYHKZDH', '银行卡终端号', '银联提供，', '1', 'admin', null, null, '2019-11-02 14:50:52', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1032', '520607', '520636', 'UNIONYHKSHH', '银行卡商户号', '银联提供', '1', 'admin', null, null, '2019-11-02 14:51:22', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1033', '520607', '520636', 'UNIONB2CZDH', 'B扫C终端号', '银联提供', '1', 'admin', null, null, '2019-11-02 14:52:40', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1034', '520607', '520636', 'UNIONB2CSHH', 'B扫C商户号', '银联提供', '1', 'admin', null, null, '2019-11-02 14:53:12', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1035', '520607', '520636', 'UNIONC2BZDH', 'C扫B终端号', '银联提供', '1', 'admin', '1', 'admin', '2019-11-02 14:53:45', '2019-11-02 14:54:38', '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1036', '520607', '520636', 'UNIONC2BSHH', 'C扫B商户号', '银联提供', '1', 'admin', null, null, '2019-11-02 14:54:09', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1038', '520609', '', 'MAIN', '是否主服务', '是否为主服务,主服务需安装数据库服务及开启重传服务，取值：true/false', '1', 'admin', null, null, '2019-11-02 18:08:20', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1039', '520609', '', 'LOCALDBPATH', '本地数据库路径', '用于存放上传入住数据', '1', 'admin', null, null, '2019-11-02 18:09:20', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1040', '520609', '', 'ZZJBS', '自助机标示', '自助机标示，形式:ZZJ+编号，如ZZJ001,ZZJ002', '1', 'admin', null, null, '2019-11-02 18:09:56', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1041', '520609', '', 'HOTELID', '酒店编号', 'PSB厂商提供', '1', 'admin', null, null, '2019-11-02 18:10:41', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1042', '520609', '', 'SERVICEURL', '接口地址', 'PSB数据上传接口,各厂商不一样，各地区不一样', '1', 'admin', null, null, '2019-11-02 18:13:59', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1043', '520609', '', 'CONNSTRING', '中间库地址', '', '1', 'admin', null, null, '2019-11-02 18:14:26', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1044', '520609', '520624', 'POLICEDJCODE', '厂商编号', '默认值:I4SGCQXWSEOZ8KBLZ6OF4OH96WZJWW11', '1', 'admin', '1', 'admin', '2019-11-02 18:15:06', '2019-11-02 18:15:50', '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1045', '520609', '520624', 'HOTELSTATIONCODE', '酒店机器号', '默认值：520151JDBV100002', '1', 'admin', null, null, '2019-11-02 18:16:25', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1046', '520609', '520644', 'SUPPLIERCODE', '厂商编号', '', '1', 'admin', null, null, '2019-11-02 18:17:29', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1047', '520609', '520644', 'CHECKINGURL', '入住接口', '仅湖北武汉航信使用', '1', 'admin', null, null, '2019-11-02 18:17:56', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1048', '520609', '520642', 'PDAFLAG', '终端类型', '', '1', 'admin', null, null, '2019-11-02 18:18:38', null, '1', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1049', '520609', '', 'SCHEDULE', '重传任务频次', '', '1', 'admin', null, null, '2019-11-02 18:19:04', null, '1', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1050', '520609', '', 'PROVICENAME', '省份名称', '如：guizhou，需根据文档配置', '1', 'admin', null, null, '2019-11-02 18:19:49', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1051', '520609', '', 'PROVICECODE', '省份行政区划', '如：520000，需根据文档配置', '1', 'admin', null, null, '2019-11-02 18:20:21', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1052', '520609', '', 'CITYNAME', '城市名称', '如：guiyang', '1', 'admin', null, null, '2019-11-02 18:20:53', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1053', '520609', '', 'CITYCODE', '城市行政区划', '如：520100', '1', 'admin', null, null, '2019-11-02 18:21:13', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1054', '520609', '', 'MANUFACTURER', 'PSB厂商名称', '如：guizhouhangxing', '1', 'admin', null, null, '2019-11-02 18:21:57', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1055', '520609', '', 'TYPE', '接口类型', '如：WEBSERVICE|INTERMEDIATEDB', '1', 'admin', null, null, '2019-11-02 18:22:40', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1056', '520609', '520644', 'AESKEY', '秘钥', '', '1', 'admin', null, null, '2019-11-02 18:23:03', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1057', '520609', '', 'APPKEY', '请求签名密钥', '', '1', 'admin', null, null, '2019-11-02 18:23:25', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1058', '520609', '', 'REQUESTID', '请求ID', '', '1', 'admin', null, null, '2019-11-02 18:23:47', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1059', '520609', '520642', 'TOKENINFO', '签名', '', '1', 'admin', null, null, '2019-11-02 18:24:12', null, '1', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1060', '520609', '', 'ISBASE', '使用基础包', '是否采用省基础包进行处理,取值：true/false', '1', 'admin', null, null, '2019-11-02 18:24:59', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1061', '520609', '', 'USER', '用户名', '用于登录数据', '1', 'admin', null, null, '2019-11-02 18:25:27', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1062', '520609', '', 'PWD', '密码', '用于登录数据库', '1', 'admin', null, null, '2019-11-02 18:25:52', null, '0', '0', '');
INSERT INTO `cf_pt_structure` VALUES ('1063', '520607', '520635', 'test', '测试', '', '1', 'admin', '1', 'admin', '2019-11-11 10:38:40', '2019-11-14 14:30:07', '0', '1', 'code_if');
INSERT INTO `cf_pt_structure` VALUES ('1064', '520607', '520635', 'test2', '测试2', '', '1', 'admin', null, null, '2019-11-11 11:09:36', null, '1', '0', 'indexs_log');
INSERT INTO `cf_pt_structure` VALUES ('1065', '520607', '520635', 'test2', '测试2', '', '1', 'admin', '1', 'admin', '2019-11-11 12:29:15', '2019-11-11 14:33:26', '0', '1', 'indexs_log');

-- ----------------------------
-- Table structure for cf_pt_template
-- ----------------------------
DROP TABLE IF EXISTS `cf_pt_template`;
CREATE TABLE `cf_pt_template` (
  `temp_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '模板ID',
  `p_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `temp_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '模板名称',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`temp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模板信息表';

-- ----------------------------
-- Records of cf_pt_template
-- ----------------------------
INSERT INTO `cf_pt_template` VALUES ('12c3ec2803914dc294a3d0c6c441ce9c', '520607', '测试模板', null, null, '1', 'admin', '2019-11-07 15:45:38', '2019-11-11 17:33:25', '0');
INSERT INTO `cf_pt_template` VALUES ('25ab4172290c403a9481c6c30ee5d9a6', '520607', '银联支付模板', '1', 'admin', '1', 'admin', '2019-11-02 18:04:50', '2019-11-02 18:28:38', '0');
INSERT INTO `cf_pt_template` VALUES ('a381c4246cfc4534948941fb81cdc6a2', '520607', '工行支付模板', '1', 'admin', '1', 'admin', '2019-11-02 18:05:44', '2019-11-14 14:27:41', '0');

-- ----------------------------
-- Table structure for cf_pt_template_paras
-- ----------------------------
DROP TABLE IF EXISTS `cf_pt_template_paras`;
CREATE TABLE `cf_pt_template_paras` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `p_id` bigint(20) DEFAULT NULL COMMENT '参数ID',
  `temp_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '模板ID',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模板参数信息表';

-- ----------------------------
-- Records of cf_pt_template_paras
-- ----------------------------
INSERT INTO `cf_pt_template_paras` VALUES ('53', '1031', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:04:50', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('54', '1032', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:04:50', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('55', '1033', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:04:50', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('56', '1034', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:04:50', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('57', '1035', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:04:50', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('58', '1036', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:04:50', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('59', '1028', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-02 18:05:44', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('60', '1029', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-02 18:05:44', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('61', '1030', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-02 18:05:44', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('62', '1031', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:28:38', null, '0');
INSERT INTO `cf_pt_template_paras` VALUES ('63', '1032', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:28:38', null, '0');
INSERT INTO `cf_pt_template_paras` VALUES ('64', '1033', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:28:38', null, '0');
INSERT INTO `cf_pt_template_paras` VALUES ('65', '1034', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:28:38', null, '0');
INSERT INTO `cf_pt_template_paras` VALUES ('66', '1035', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:28:38', null, '0');
INSERT INTO `cf_pt_template_paras` VALUES ('67', '1036', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:28:38', null, '0');
INSERT INTO `cf_pt_template_paras` VALUES ('68', '1027', '25ab4172290c403a9481c6c30ee5d9a6', '1', 'admin', null, null, '2019-11-02 18:28:38', null, '0');
INSERT INTO `cf_pt_template_paras` VALUES ('69', '1028', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-02 18:28:56', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('70', '1029', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-02 18:28:56', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('71', '1030', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-02 18:28:56', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('72', '1027', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-02 18:28:56', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('73', '1028', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-04 19:10:09', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('74', '1029', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-04 19:10:09', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('75', '1030', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-04 19:10:09', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('76', '1027', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-04 19:10:09', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('77', '1028', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-05 18:45:08', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('78', '1029', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-05 18:45:08', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('79', '1030', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-05 18:45:08', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('80', '1027', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-05 18:45:08', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('81', '1031', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-05 18:45:09', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('82', '1028', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-05 18:45:21', null, '1');
INSERT INTO `cf_pt_template_paras` VALUES ('83', '1029', 'a381c4246cfc4534948941fb81cdc6a2', '1', 'admin', null, null, '2019-11-05 18:45:21', null, '1');


-- ----------------------------
-- Table structure for cf_pt_template_terminal
-- ----------------------------
DROP TABLE IF EXISTS `cf_pt_template_terminal`;
CREATE TABLE `cf_pt_template_terminal` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ti_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '终端ID',
  `temp_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '模板ID',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='终端模板信息表';

-- ----------------------------
-- Records of cf_pt_template_terminal
-- ----------------------------
INSERT INTO `cf_pt_template_terminal` VALUES ('4', '07090', '25ab4172290c403a9481c6c30ee5d9a6', '1', null, null, null, '2019-11-04 17:19:52', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('5', '07090', 'a381c4246cfc4534948941fb81cdc6a2', '1', null, null, null, '2019-11-04 17:51:22', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('6', '123155', '123', '1', null, null, null, '2019-11-04 18:00:09', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('7', '123156', '123', '1', null, null, null, '2019-11-04 18:01:05', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('8', '123157', '123', null, null, null, null, '2019-11-04 18:33:35', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('9', '123158', '25ab4172290c403a9481c6c30ee5d9a6', null, null, null, null, '2019-11-04 18:52:17', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('10', '123159', '25ab4172290c403a9481c6c30ee5d9a6', null, null, null, null, '2019-11-05 09:14:28', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('11', '123qeqweqw160', '25ab4172290c403a9481c6c30ee5d9a6', null, null, null, null, '2019-11-05 09:23:31', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('12', '520000076161', '25ab4172290c403a9481c6c30ee5d9a6', null, null, null, null, '2019-11-05 10:06:53', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('13', '520000076162', '5210', null, null, null, null, '2019-11-05 10:21:54', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('14', '520000076162', '5320', null, null, null, null, '2019-11-05 10:22:00', null, '1');
INSERT INTO `cf_pt_template_terminal` VALUES ('15', '520000076162', '5536', null, null, null, null, '2019-11-05 10:22:07', null, '1');


-- ----------------------------
-- Table structure for cf_ti_batch
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_batch`;
CREATE TABLE `cf_ti_batch` (
  `bh_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '批次ID',
  `bh_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '批次名称',
  `bh_number` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '批次号',
  `bh_count` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '数量',
  `bh_time` datetime DEFAULT NULL COMMENT '批次创建时间',
  `com_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '合同编号',
  `com_ci_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '合同经办人',
  `com_time` datetime DEFAULT NULL COMMENT '合同签订时间',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`bh_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='批次信息表 Terminal_Message_Batch_Information';

-- ----------------------------
-- Records of cf_ti_batch
-- ----------------------------

-- ----------------------------
-- Table structure for cf_ti_bloc
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_bloc`;
CREATE TABLE `cf_ti_bloc` (
  `bloc_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '酒店集团ID',
  `bloc_legalperson` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '酒店集团法人',
  `bloc_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '酒店集团名称',
  `bloc_country_area_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '国家区号代码',
  `bloc_type_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '集团类型（海外、国内）代码',
  `bloc_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '集团总部地址',
  `bloc_districts_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '集团总部县级行政区划代码',
  `c_user_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(6) DEFAULT '0',
  PRIMARY KEY (`bloc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集团信息表';

-- ----------------------------
-- Records of cf_ti_bloc
-- ----------------------------
INSERT INTO `cf_ti_bloc` VALUES ('0039000064', '熊笠宇', '熊笠宇别样红正式环境测试', '39', '1', '', '', '1', '超级管理员1', '1', '超级管理员1', '2019-10-09 10:28:27', '2019-10-09 10:28:27', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086000055', '李某某', '索菲集团', '86', '1', '贵阳市', '', '1', '超级管理员1', '1', '超级管理员1', '2019-09-23 18:10:42', '2019-09-23 18:10:42', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086100000000044', '贾超', '北京乐创酒店管理有限公司', '86', '1', '北京市密云区溪翁庄镇环湖路66号镇政府1号楼110室', '', '1', '超级管理员1', '1', '超级管理员', '2019-09-03 00:00:00', '2019-11-02 16:15:55', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086100000000056', 'wu', '纽宾凯集团', '86', '1', '武汉', '100000', '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 11:57:57', '2019-09-24 11:57:57', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086100000000057', 'ee', '绿地集团', '86', '1', '上海', '100000', '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:00:47', '2019-09-24 12:00:47', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086100000000058', '111', '上善水文化集团', '86', '1', '贵阳', '100000', '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:02:30', '2019-09-24 12:02:30', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086100000000059', '66', '江口凤栖集团', '86', '1', '铜仁', '100000', '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:04:41', '2019-09-24 12:04:41', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086100000000060', '123', '武汉欧亚会展集团', '86', '1', '武汉', '100000', '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:06:09', '2019-11-02 17:27:11', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086100000000063', '王永', '阿勒泰地区旅投集团', '86', '1', '新疆阿勒泰地区阿勒泰公园路33号', '100000', '1', '超级管理员1', '1', '超级管理员1', '2019-09-29 13:43:40', '2019-09-29 13:43:40', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086100000000065', '苏家宇 ', '广西圣展集团', '86', '1', '南宁市金湖南路49号', '100000', '1', '超级管理员1', '1', '超级管理员1', '2019-10-17 15:35:06', '2019-11-02 17:26:35', '0');
INSERT INTO `cf_ti_bloc` VALUES ('008644010210089', null, '999', '86', '1', null, '520102', null, null, null, null, '2019-11-04 15:29:02', '2019-11-04 15:46:23', '1');
INSERT INTO `cf_ti_bloc` VALUES ('0086520000000061', '吴晓红', '都匀通利万利酒店管理有限公司', '86', '1', '贵州省都匀市', '520000', '1', '超级管理员1', '1', '超级管理员1', '2019-09-26 12:27:55', '2019-09-26 12:27:55', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520000000068', '彭伟', '六盘水时代假日酒店有限公司', '86', '1', '六盘水市钟山区钟山中路66号', '520102', '1', '超级管理员1', '1', '超级管理员1', '2019-10-21 00:00:00', '2019-11-02 17:26:00', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520100000050', '伍端萍', '贵阳水云天旅游有限公司', '86', '1', '贵阳市南明区翠微巷60号', '', '1', '超级管理员1', '1', '超级管理员1', '2019-09-10 00:00:00', '2019-09-10 10:40:17', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520101000042', '袁金娥', '贵州索菲智慧酒店管理有限公司', '86', '1', '贵州省贵阳市观山湖区富力中心A5栋负一楼', '', '1', '超级管理员1', '1', '超级管理员1', '2019-08-28 00:00:00', '2019-08-28 16:34:04', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520101000043', '袁银娥', '汉唐佳华酒店集团', '86', '1', '贵州省贵阳市', '520101', '1', '超级管理员1', '1', '超级管理员1', '2019-09-01 14:23:23', '2019-09-01 14:23:23', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520101000045', '袁斌', '林城假日', '86', '1', 'guiyang ', '', '1', '超级管理员1', '1', '超级管理员', '2019-09-04 00:00:00', '2019-11-02 16:15:02', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520101000048', '袁小斌', '贵州尚客集团', '86', '1', '贵阳', '520101', '1', '超级管理员1', '1', '超级管理员1', '2019-09-06 10:41:37', '2019-09-06 10:41:37', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520101000051', '刘观平', '贵州壹加捌酒店管理有限公司', '86', '1', '贵阳', '520101', '1', '超级管理员1', '1', '超级管理员1', '2019-09-11 11:50:47', '2019-09-11 11:50:47', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520101000062', '李继明', '贵州都市怡景酒店', '86', '1', '贵州省贵阳市', '520101', '1', '超级管理员1', '1', '超级管理员1', '2019-09-27 10:45:23', '2019-09-27 10:45:23', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520102000046', '刘观平', '贵州壹加捌酒店管理有限公司', '86', '1', '贵州省贵阳市', '520102', '1', '超级管理员1', '1', '超级管理员1', '2019-09-04 14:56:55', '2019-09-04 14:56:55', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520102000047', '张一', '米家智能酒店', '86', '1', '贵州省贵阳市', '', '1', '超级管理员1', '1', '超级管理员1', '2019-09-06 00:00:00', '2019-09-06 19:47:04', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520102000054', '陈智', '贵州贵龙饭店酒店管理有限责任公司', '86', '1', '贵州省贵阳市', '', '1', '超级管理员1', '1', '超级管理员1', '2019-09-19 00:00:00', '2019-09-19 15:14:49', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520102100092', null, '123', '86', '1', null, '520102', null, null, null, null, '2019-11-08 09:18:49', '2019-11-08 09:18:53', '1');
INSERT INTO `cf_ti_bloc` VALUES ('0086520102100094', '杨先生', '杨锰测试集团', '86', '1', '希尔顿花园酒店5楼', '520102', '1', '超级管理员', '1', '超级管理员', '2019-11-12 14:52:58', '2019-11-12 14:53:21', '0');
INSERT INTO `cf_ti_bloc` VALUES ('008652010210087', '杨锰', '六盘水时代假日酒店有限公司', '86', '1', '希尔顿酒店5楼', '520102', null, null, '1', '超级管理员', '2019-11-04 11:35:55', '2019-11-08 14:59:21', '1');
INSERT INTO `cf_ti_bloc` VALUES ('008652010210088', 'zhang', '123', '86', '1', '123', '520102', null, null, null, null, '2019-11-04 11:37:35', '2019-11-04 12:02:02', '1');
INSERT INTO `cf_ti_bloc` VALUES ('008652010210090', null, '000', '86', '1', null, '520102', null, null, null, null, '2019-11-04 15:46:07', '2019-11-04 15:46:20', '1');
INSERT INTO `cf_ti_bloc` VALUES ('008652010210091', null, '123', '86', '1', null, '520102', null, null, null, null, '2019-11-04 15:50:43', '2019-11-04 15:50:52', '1');
INSERT INTO `cf_ti_bloc` VALUES ('0086520103000041', '龚朝明', '贵州汉唐佳华酒店管理有限公司', '86', '1', '贵州省贵阳市', '520103', '1', '超级管理员1', '1', '超级管理员1', '2019-08-26 00:00:00', '2019-08-26 20:21:34', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520103000049', '孙勇', '贵阳悠游酒店有限公司', '86', '1', '贵阳 云岩区 太平路111号三联大厦', '', '1', '超级管理员1', '1', '超级管理员1', '2019-09-10 00:00:00', '2019-09-10 09:58:59', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520103000052', '马总', '野马集团', '86', '1', '昆明路', '520103', '1', '超级管理员1', '1', '超级管理员1', '2019-09-14 10:31:40', '2019-09-14 10:31:40', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520103000053', '韩埼', '贵州雅迪尔大酒店有限公司', '86', '1', '大十字', '520102', '1', '超级管理员1', '1', '超级管理员1', '2019-09-14 00:00:00', '2019-09-16 11:00:17', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520103100093', '杨锰', '运维云测试集团', '86', '1', '希尔顿花园酒店5楼', '520103', '1', '超级管理员', '1', '超级管理员', '2019-11-08 15:03:53', '2019-11-08 15:06:20', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520111000066', 'xx', '贵州群升豪生集团', '86', '1', '贵阳贵安新区大学城思雅路与思孟路交叉口', '520111', '1', '超级管理员1', '1', '超级管理员1', '2019-10-18 00:00:00', '2019-11-02 17:26:32', '0');
INSERT INTO `cf_ti_bloc` VALUES ('0086520111000067', 'xx', '贵州群升豪生集团', '86', '1', '贵阳贵安新区大学城思雅路与思孟路交叉口', '520111', '1', '超级管理员1', '1', '超级管理员1', '2019-10-18 10:12:53', '2019-11-02 17:26:29', '0');

-- ----------------------------
-- Table structure for cf_ti_facility_model
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_facility_model`;
CREATE TABLE `cf_ti_facility_model` (
  `m_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '设备型号ID',
  `mi_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '镜像ID',
  `bh_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '批次ID',
  `p_name` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '产品名称',
  `m_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '产品型号',
  `m_code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '型号编码',
  `m_count` int(11) DEFAULT NULL COMMENT '型号数量/台',
  `m_comment` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备型号信息表';

-- ----------------------------
-- Records of cf_ti_facility_model
-- ----------------------------

-- ----------------------------
-- Table structure for cf_ti_hardwareinfo
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_hardwareinfo`;
CREATE TABLE `cf_ti_hardwareinfo` (
  `hw_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '硬件ID',
  `m_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '设备型号ID',
  `hw_type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '硬件类型 (标准化硬件、客供硬件)',
  `hw_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '硬件名称',
  `hw_manufacturer` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '硬件厂商',
  `hw_tel` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人电话',
  `hw_model` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '硬件型号',
  `hw_comment` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '其他描述信息',
  `hw_ar` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '英文缩写',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`hw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='硬件信息表 Terminal_Message_Hardware_Information';

-- ----------------------------
-- Records of cf_ti_hardwareinfo
-- ----------------------------

-- ----------------------------
-- Table structure for cf_ti_hotel
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_hotel`;
CREATE TABLE `cf_ti_hotel` (
  `hi_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '酒店ID',
  `bloc_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '酒店集团ID',
  `hi_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '酒店名称',
  `hi_legalperson` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '酒店工商法人名称',
  `hi_tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '服务电话',
  `hi_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '酒店类型',
  `hi_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '酒店地址',
  `hi_districts_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '县级行政区划',
  `hi_longitude` decimal(10,5) DEFAULT NULL COMMENT '酒店地址经度',
  `hi_latitude` decimal(10,5) DEFAULT NULL COMMENT '酒店地址纬度',
  `hi_ifdemohotel` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否示范酒店',
  `is_open_rule` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否自动配置门锁',
  `c_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(6) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `hi_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`hi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='酒店信息表';

-- ----------------------------
-- Records of cf_ti_hotel
-- ----------------------------
INSERT INTO `cf_ti_hotel` VALUES ('068', '0086520101000043', '豪威大酒店', '袁泉', '0851-84857999', '2', '观山湖区碧海南路绿地联盛国际5号楼', '', '0.00000', '0.00000', '', null, null, null, '1', '超级管理员1', null, '2019-09-29 10:47:53', '1', null);
INSERT INTO `cf_ti_hotel` VALUES ('069', '0086520101000043', '豪威大酒店', '袁泉', '0851-84857999', '', '贵州省贵阳市南明区遵义路105号智亿大厦5层0号', '', '0.00000', '0.00000', '', null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-29 10:49:13', '2019-09-29 10:49:13', '1', null);
INSERT INTO `cf_ti_hotel` VALUES ('070', '0086520101000043', '豪威大酒店', '袁泉', '0851-84857999', '', '贵州省贵阳市南明区遵义路105号智亿大厦5层0号', '', '0.00000', '0.00000', '', null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-29 10:50:48', '2019-09-29 10:50:48', '1', null);
INSERT INTO `cf_ti_hotel` VALUES ('073', '0039000064', '熊笠宇别样红正式环境测试1', '', '', '', '', '', '0.00000', '0.00000', '', null, '1', '超级管理员1', '1', '超级管理员1', '2019-10-09 10:29:18', '2019-10-09 10:29:18', '1', null);
INSERT INTO `cf_ti_hotel` VALUES ('100000060', '0086100000000056', '武汉纽宾凯楚玉酒店', 'e', '333', '3', 'wuahn ', '100000', '43.40000', '35.50000', '1', null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 11:59:11', '2019-09-24 11:59:11', '0', null);
INSERT INTO `cf_ti_hotel` VALUES ('100000061', '0086100000000057', '武汉绿地铂派酒店', '33', '566', '2', '武汉', '100000', '54.70000', '45.70000', '1', null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:01:25', '2019-09-24 12:01:25', '0', null);
INSERT INTO `cf_ti_hotel` VALUES ('100000063', '0086100000000059', '铜仁江口凤栖酒店', '444', '555', '4', '铜仁', '100000', '65.70000', '54.40000', '1', null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:05:23', '2019-09-24 12:05:23', '0', null);
INSERT INTO `cf_ti_hotel` VALUES ('100000064', '0086100000000060', '武汉欧亚会展酒店', 'fe', '65465', '1', '武汉', '100000', '65.70000', '43.70000', '1', null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:06:42', '2019-09-24 12:06:42', '0', null);
INSERT INTO `cf_ti_hotel` VALUES ('100000072', '0086100000000063', '阿勒泰地区旅游宾馆', '王永', '0906-2123804', '1', '新疆阿勒泰地区公园路33号', '100000', '32.70000', '65.70000', '1', null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-29 13:44:45', '2019-09-29 13:44:45', '0', null);
INSERT INTO `cf_ti_hotel` VALUES ('440101044', '0086100000000044', '广州有戏电影酒店', '贾超', '020-38273017', '2', '广州天河区龙怡路117号银汇大厦二楼', '440101', '0.00000', '0.00000', '1', null, null, null, '1', '超级管理员1', null, '2019-09-05 12:46:51', '0', null);
INSERT INTO `cf_ti_hotel` VALUES ('450100074', '0086100000000065', '圣展酒店', '苏家宇', '18907713611', '2', '南宁市金湖南路49号', '450100', '43.50000', '45.70000', '1', null, '1', '超级管理员1', '1', '超级管理员1', '2019-10-17 15:38:07', '2019-11-02 17:31:49', '0', null);
INSERT INTO `cf_ti_hotel` VALUES ('520000066', '0086520000000061', '都匀港龙大酒店', '吴晓红', '0854-8885999', '1', '贵州省都匀市滨河路134号', '520000', '0.00000', '0.00000', '0', null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-26 12:32:12', '2019-09-26 12:32:12', '0', null);
INSERT INTO `cf_ti_hotel` VALUES ('520000076', '0086520000000068', '六盘水时代假日酒店', '彭伟', '0858-8108888', '1', '六盘水市钟山区钟山中路66号', '520000', '21.00000', '21.00000', '1', null, null, null, '1', '超级管理员1', null, '2019-10-21 13:10:06', '0', null);

-- ----------------------------
-- Table structure for cf_ti_lock_configuration
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_lock_configuration`;
CREATE TABLE `cf_ti_lock_configuration` (
  `pk_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hi_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '酒店ID',
  `joiner` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '锁号连接符',
  `max_num` int(11) DEFAULT NULL COMMENT '锁号长度',
  `fo_rule` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '楼层号规则',
  `is_fobechar_add` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '楼层号是否首尾补充字符',
  `fo_add_begchar` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '楼层号需要补充的首字符',
  `fo_add_endchar` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '楼层号需要补充的尾字符',
  `is_fobechar_red` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '楼层号是否需要截取房间号',
  `fo_red_begchar` int(11) DEFAULT NULL COMMENT '楼层号截取房间号的开始下标',
  `fo_red_endchar` int(11) DEFAULT NULL COMMENT '楼层号截取房间号的结尾下标',
  `ro_rule` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房间号规则',
  `is_robechar` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '房间号是否首尾补充字符',
  `ro_add_begchar` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房间号需要补充的首字符',
  `ro_add_endchar` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房间号需要补充的尾字符',
  `bu_rule` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '楼栋号规则',
  `is_bubechar` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否首尾补充字符',
  `bu_add_begchar` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '需要补充的首字符',
  `bu_add_endchar` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '需要补充的尾字符',
  `is_bubechar_red` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否需要截取房间号',
  `bu_red_begchar` int(11) DEFAULT NULL COMMENT '房间号的开始下标',
  `bu_red_endchar` int(11) DEFAULT NULL COMMENT '房间号的结尾下标',
  `lock_replace` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '锁号替换',
  `is_lock_replace` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '是否开启锁号替换',
  `re_keyvalue` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '替换的键值对',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='有规则门锁';

-- ----------------------------
-- Records of cf_ti_lock_configuration
-- ----------------------------
INSERT INTO `cf_ti_lock_configuration` VALUES ('9', '520103118', '0', '110', '110', '0', '110', '0', '110', '110', '0', '110', '5', '110', '0', '110', '110', '110', '0', null, null, null, null, null, null, null, null, null, null, '2019-11-13 15:43:48', null, '0');

-- ----------------------------
-- Table structure for cf_ti_lock_keyvalue
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_lock_keyvalue`;
CREATE TABLE `cf_ti_lock_keyvalue` (
  `pk_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hi_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '酒店id',
  `ro_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '房间号',
  `lock_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '锁号',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='无规则门锁';

-- ----------------------------
-- Records of cf_ti_lock_keyvalue
-- ----------------------------
INSERT INTO `cf_ti_lock_keyvalue` VALUES ('15', '520103118', '202', '3568', null, null, null, null, '2019-11-13 11:26:19', null, '0');
INSERT INTO `cf_ti_lock_keyvalue` VALUES ('16', '520103118', '203', '3568', null, null, null, null, '2019-11-13 11:26:19', null, '0');
INSERT INTO `cf_ti_lock_keyvalue` VALUES ('17', '520103118', '204', '3568', null, null, null, null, '2019-11-13 11:26:19', null, '0');
INSERT INTO `cf_ti_lock_keyvalue` VALUES ('18', '520103118', '205', '3568', null, null, null, null, '2019-11-13 11:26:19', null, '0');
INSERT INTO `cf_ti_lock_keyvalue` VALUES ('19', '520103118', '206', '3568', null, null, null, null, '2019-11-13 11:26:19', null, '0');
INSERT INTO `cf_ti_lock_keyvalue` VALUES ('20', '520103118', '207', '3568', null, null, null, null, '2019-11-13 11:26:19', null, '0');
INSERT INTO `cf_ti_lock_keyvalue` VALUES ('21', '520103118', '208', '3568', null, null, null, null, '2019-11-13 11:26:19', null, '0');

-- ----------------------------
-- Table structure for cf_ti_maintain
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_maintain`;
CREATE TABLE `cf_ti_maintain` (
  `pk_id` bigint(20) NOT NULL COMMENT '主键ID',
  `ti_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '终端ID',
  `hi_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '酒店ID',
  `maintain_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '维修人',
  `maintain_time` datetime DEFAULT NULL COMMENT '维修时间',
  `maintain_comment` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '维修备注',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='终端维修记录表';

-- ----------------------------
-- Records of cf_ti_maintain
-- ----------------------------

-- ----------------------------
-- Table structure for cf_ti_main_version
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_main_version`;
CREATE TABLE `cf_ti_main_version` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version_no` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '版本号',
  `version_comments` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(6) DEFAULT '0' COMMENT '删除标志',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cf_ti_main_version
-- ----------------------------
INSERT INTO `cf_ti_main_version` VALUES ('2', 'V1.2.9_Release', '2019年1月12日发布', '0', null, '2019-11-06 15:15:37');
INSERT INTO `cf_ti_main_version` VALUES ('3', '1.3.12', '测试2', '0', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('4', '1.3.0.10', '', '0', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('5', '1.3.0.11', '', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('8', '1.2.0', '', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('9', '1', '1', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('10', '2', '', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('11', '3', '3版本', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('12', '1', '1', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('13', '2', '2', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('14', '1.1', '', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('15', '1.3.0.30', '无修改', '0', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('16', '1.3.0.31', '', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('17', '1.3.0.32', '打发十分', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('18', '1.2.0.40', '大幅度发', '0', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('19', '1.3.01', '', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('20', '1.3.0', '', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('21', 'V11_05_Release', '2019年11月05日发布', '1', null, null);
INSERT INTO `cf_ti_main_version` VALUES ('78', 'V11_05_Release', '2019年11月04日发布1', '0', '2019-11-05 17:08:32', null);
INSERT INTO `cf_ti_main_version` VALUES ('79', 'V12_05_Release', '测试cs', '0', '2019-11-06 14:38:16', '2019-11-06 15:16:26');

-- ----------------------------
-- Table structure for cf_ti_mirror_image
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_mirror_image`;
CREATE TABLE `cf_ti_mirror_image` (
  `mi_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '镜像ID',
  `mi_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '镜像名称',
  `mi_size` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '镜像大小',
  `mi_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '下载地址',
  `mi_type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '镜像类型 1.光盘映像文件 2.压缩文件',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`mi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='镜像表 mirror image';

-- ----------------------------
-- Records of cf_ti_mirror_image
-- ----------------------------

-- ----------------------------
-- Table structure for cf_ti_softwareinfo
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_softwareinfo`;
CREATE TABLE `cf_ti_softwareinfo` (
  `ti_sw_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '终端软件ID',
  `ti_sw_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '软件名称',
  `ti_sw_path` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '软件路径',
  `ti_sw_version` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '软件版本号',
  `ti_sw_introduction` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '功能简介',
  `ti_sw_ar` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '英文缩写',
  `ti_sw_comment` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '其他描述信息',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`ti_sw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='终端软件信息表 Terminal_Software_Information';

-- ----------------------------
-- Records of cf_ti_softwareinfo
-- ----------------------------

-- ----------------------------
-- Table structure for cf_ti_softwareup
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_softwareup`;
CREATE TABLE `cf_ti_softwareup` (
  `pk_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `ti_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '终端ID',
  `ti_sw_version_now` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '软件版本号',
  `ti_sw_version_up` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '软件升级版本号',
  `ti_sw_status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '软件当前状态 1.升级成功 2.升级中 3升级失败',
  `ti_sw_open_date` datetime DEFAULT NULL COMMENT '软件升级开启时间',
  `ti_sw_finish_date` datetime DEFAULT NULL COMMENT '软件升级完成时间',
  `ti_sw_comment` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '其他描述信息',
  `c_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='终端软件升级信息表';

-- ----------------------------
-- Records of cf_ti_softwareup
-- ----------------------------

-- ----------------------------
-- Table structure for cf_ti_terminal
-- ----------------------------
DROP TABLE IF EXISTS `cf_ti_terminal`;
CREATE TABLE `cf_ti_terminal` (
  `ti_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '终端ID',
  `hi_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '酒店ID',
  `m_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '设备型号ID',
  `ti_sw_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '终端软件ID',
  `ti_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '终端编号',
  `bh_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '批次编号',
  `ti_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '终端类型',
  `ti_up_time` datetime DEFAULT NULL COMMENT '终端上架时间',
  `ti_down_time` datetime DEFAULT NULL COMMENT '终端下架时间',
  `ti_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '维护人员ID',
  `ti_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '维护人员名称',
  `em_mode` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '设备型号EquipmentMode',
  `em_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '设备状态EquipmentStatus',
  `c_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `c_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `m_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人ID',
  `m_user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名称',
  `c_time` datetime DEFAULT NULL COMMENT '创建时间',
  `m_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(6) DEFAULT '0',
  PRIMARY KEY (`ti_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='终端信息表 Terminal_Message_Information';

-- ----------------------------
-- Records of cf_ti_terminal
-- ----------------------------
INSERT INTO `cf_ti_terminal` VALUES ('064164', '064', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-07 14:04:04', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('064171', '064', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-08 09:21:46', null, '0');
INSERT INTO `cf_ti_terminal` VALUES ('065170', '065', null, null, null, null, '2', null, null, null, null, null, null, null, null, null, null, '2019-11-07 17:13:17', null, '0');
INSERT INTO `cf_ti_terminal` VALUES ('065172', '065', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-08 09:22:09', null, '0');
INSERT INTO `cf_ti_terminal` VALUES ('070169', '070', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2019-11-07 16:31:21', null, '0');
INSERT INTO `cf_ti_terminal` VALUES ('07090', '070', null, null, null, null, '1', '2019-09-29 00:00:00', '2019-09-29 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-11-07 10:51:31', '2019-11-07 15:46:43', '0');
INSERT INTO `cf_ti_terminal` VALUES ('07394', '073', null, null, null, null, '', null, null, '', '', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-10-09 10:30:29', '2019-10-09 10:30:29', '0');
INSERT INTO `cf_ti_terminal` VALUES ('083163', '083', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-05 16:04:07', '2019-11-05 18:19:13', '1');
INSERT INTO `cf_ti_terminal` VALUES ('10000006082', '100000060', null, null, null, null, '1', null, null, '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 11:59:22', '2019-09-24 11:59:22', '0');
INSERT INTO `cf_ti_terminal` VALUES ('10000006183', '100000061', null, null, null, null, '1', null, null, '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:01:38', '2019-09-24 12:01:38', '0');
INSERT INTO `cf_ti_terminal` VALUES ('10000006385', '100000063', null, null, null, null, '1', null, null, '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:05:33', '2019-09-24 12:05:33', '0');
INSERT INTO `cf_ti_terminal` VALUES ('10000006486', '100000064', null, null, null, null, '1', null, null, '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-24 12:06:52', '2019-11-11 17:33:45', '0');
INSERT INTO `cf_ti_terminal` VALUES ('10000007292', '100000072', null, null, null, null, '1', null, null, '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-29 13:45:09', '2019-09-29 13:45:09', '0');
INSERT INTO `cf_ti_terminal` VALUES ('10000007293', '100000072', null, null, null, null, '1', null, null, '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-30 11:33:23', '2019-09-30 11:33:23', '0');
INSERT INTO `cf_ti_terminal` VALUES ('123150', '123', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-04 17:19:28', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123151', '123', '123', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-04 17:19:52', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123152', '123', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-04 17:50:57', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123153', '123', '213', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-04 17:51:18', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123154', '123', '213', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-04 17:51:22', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123155', '123', '123', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-04 18:00:09', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123156', '123', '123', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-04 18:01:05', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123157', '123', '123', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-04 18:33:35', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123158', '123', '123', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-04 18:52:17', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123159', '123', '123', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-05 09:14:28', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('123qeqweqw160', '123qeqweqw', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-05 09:23:31', null, '1');
INSERT INTO `cf_ti_terminal` VALUES ('44010104457', '440101044', null, null, null, null, '1', '2019-09-03 00:00:00', '2020-09-16 00:00:00', '176', '许峰', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-03 00:00:00', '2019-09-05 12:51:08', '0');
INSERT INTO `cf_ti_terminal` VALUES ('45010007497', '450100074', null, null, null, null, '1', null, null, '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-10-17 15:38:22', '2019-10-17 15:38:22', '0');
INSERT INTO `cf_ti_terminal` VALUES ('45010007498', '450100074', null, null, null, null, '1', null, null, '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-10-17 15:38:35', '2019-10-17 15:38:35', '0');
INSERT INTO `cf_ti_terminal` VALUES ('52000006688', '520000066', null, null, null, null, '1', '2019-09-26 00:00:00', '2019-09-27 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-26 12:32:32', '2019-09-26 12:32:32', '0');
INSERT INTO `cf_ti_terminal` VALUES ('520000076101', '520000076', null, null, null, null, '1', '2019-10-21 00:00:00', '2023-10-21 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-10-21 10:32:58', '2019-11-05 17:27:33', '0');
INSERT INTO `cf_ti_terminal` VALUES ('520000076161', '520000076', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2019-11-05 10:06:53', '2019-11-11 16:02:51', '0');
INSERT INTO `cf_ti_terminal` VALUES ('520000076162', '520000076', null, null, null, null, '2', null, null, null, null, null, null, null, null, null, null, '2019-11-05 10:21:03', '2019-11-06 15:34:30', '0');
INSERT INTO `cf_ti_terminal` VALUES ('52010104255', '520101042', null, null, null, null, '1', '2019-08-28 00:00:00', '2023-02-28 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-08-28 16:34:37', '2019-08-28 16:34:37', '0');
INSERT INTO `cf_ti_terminal` VALUES ('52010104356', '520101043', null, null, null, null, '1', '2019-09-01 00:00:00', '2021-09-15 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-01 00:00:00', '2019-09-01 15:18:46', '0');
INSERT INTO `cf_ti_terminal` VALUES ('52010104358', '520101043', null, null, null, null, '2', '2019-09-04 00:00:00', '2021-09-16 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-04 10:50:11', '2019-09-04 10:50:11', '1');
INSERT INTO `cf_ti_terminal` VALUES ('52010104359', '520101043', null, null, null, null, '1', '2019-09-04 00:00:00', '2021-09-09 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-04 10:50:57', '2019-09-04 10:50:57', '1');
INSERT INTO `cf_ti_terminal` VALUES ('52010104370', '520101043', null, null, null, null, '1', '2019-09-10 00:00:00', '2022-11-10 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-10 12:39:58', '2019-09-10 12:39:58', '1');
INSERT INTO `cf_ti_terminal` VALUES ('52010104371', '520101043', null, null, null, null, '1', '2019-09-10 00:00:00', '2021-09-10 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-10 12:40:53', '2019-09-10 12:40:53', '1');
INSERT INTO `cf_ti_terminal` VALUES ('52010104395', '520101043', null, null, null, null, '2', '2019-10-12 00:00:00', '2021-11-12 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-10-12 13:54:14', '2019-10-12 13:54:14', '0');
INSERT INTO `cf_ti_terminal` VALUES ('52010104661', '520101046', null, null, null, null, '1', '2019-10-17 00:00:00', '2020-10-17 00:00:00', '204', '运维普通用户', null, null, '1', '超级管理员1', '1', '超级管理员1', '2019-09-04 00:00:00', '2019-10-17 10:31:00', '0');

-- ----------------------------
-- Table structure for core_dict
-- ----------------------------
DROP TABLE IF EXISTS `core_dict`;
CREATE TABLE `core_dict` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `VALUE` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `TYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `TYPE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型描述',
  `SORT` int(6) DEFAULT NULL COMMENT '排序',
  `PARENT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `DEL_FLAG` int(6) DEFAULT NULL COMMENT '删除标记',
  `REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime(6) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  KEY `idx_code` (`TYPE`) USING BTREE,
  KEY `idx_pid` (`PARENT`) USING BTREE,
  KEY `idx_value` (`VALUE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=520659 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of core_dict
-- ----------------------------
INSERT INTO `core_dict` VALUES ('520549', '1', '国内', 'bloc_type', '酒店管理集团类型', '1', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520550', '2', '国外', 'bloc_type', '酒店管理集团类型', '2', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520551', '100000', '中国', 'districts_code', '行政区划', '2947', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520552', '150000', '内蒙古', 'districts_code', '行政区划', '150000', '100000', '0', '', null);
INSERT INTO `core_dict` VALUES ('520553', '150100', '内蒙古呼和浩特市', 'districts_code', '行政区划', '150100', '150000', '0', '', null);
INSERT INTO `core_dict` VALUES ('520554', '150101', '内蒙古呼和浩特市市辖区', 'districts_code', '行政区划', '150101', '150100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520555', '150102', '内蒙古呼和浩特市新城区', 'districts_code', '行政区划', '150102', '150100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520556', '150103', '内蒙古呼和浩特市回民区', 'districts_code', '行政区划', '150103', '150100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520557', '150104', '内蒙古呼和浩特市玉泉区', 'districts_code', '行政区划', '150104', '150100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520558', '150105', '内蒙古呼和浩特市赛罕区', 'districts_code', '行政区划', '150105', '150100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520559', '440000', '广东省', 'districts_code', '行政区划', '2960', '100000', '0', '', null);
INSERT INTO `core_dict` VALUES ('520560', '440100', '广东广州市', 'districts_code', '行政区划', '2961', '440000', '0', '', null);
INSERT INTO `core_dict` VALUES ('520561', '440101', '广东广州市市辖区', 'districts_code', '行政区划', '2962', '440100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520562', '440102', '广东广州市东山区', 'districts_code', '行政区划', '2963', '440100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520563', '440103', '广东广州市荔湾区', 'districts_code', '行政区划', '2964', '440100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520564', '440104', '广东广州市越秀区', 'districts_code', '行政区划', '2965', '440100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520565', '440105', '广东广州市海珠区', 'districts_code', '行政区划', '2966', '440100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520566', '440106', '广东广州市天河区', 'districts_code', '行政区划', '2967', '440100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520567', '440107', '广东广州市芳村区', 'districts_code', '行政区划', '2968', '440100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520568', '440111', '广东广州市白云区', 'districts_code', '行政区划', '2969', '440100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520569', '440112', '广东广州市黄埔区', 'districts_code', '行政区划', '2970', '440100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520570', '450000', '广西', 'districts_code', '行政区划', '450000', '100000', '0', '', null);
INSERT INTO `core_dict` VALUES ('520571', '450100', '广西南宁市', 'districts_code', '行政区划', '450100', '450000', '0', '', null);
INSERT INTO `core_dict` VALUES ('520572', '450101', '广西南宁市市辖区', 'districts_code', '行政区划', '450101', '450100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520573', '450102', '广西南宁市兴宁区', 'districts_code', '行政区划', '450102', '450100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520574', '450103', '广西南宁市青秀区', 'districts_code', '行政区划', '450103', '450100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520575', '450104', '广西南宁市城北区', 'districts_code', '行政区划', '450104', '450100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520576', '450105', '广西南宁市江南区', 'districts_code', '行政区划', '450105', '450100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520577', '450106', '广西南宁市永新区', 'districts_code', '行政区划', '450106', '450100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520578', '450107', '广西南宁市南湖区', 'districts_code', '行政区划', '450107', '450100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520579', '450108', '广西南宁高新区', 'districts_code', '行政区划', '450108', '450100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520580', '450111', '广西南宁市市郊区', 'districts_code', '行政区划', '450111', '450100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520581', '452100', '广西南宁地区', 'districts_code', '行政区划', '452100', '450000', '0', '', null);
INSERT INTO `core_dict` VALUES ('520582', '452101', '广西南宁地区凭祥市', 'districts_code', '行政区划', '452101', '452100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520583', '520000', '贵州', 'districts_code', '行政区划', '2948', '100000', '0', '', null);
INSERT INTO `core_dict` VALUES ('520584', '520100', '贵州贵阳市', 'districts_code', '行政区划', '2949', '520000', '0', '', null);
INSERT INTO `core_dict` VALUES ('520585', '520101', '贵州贵阳市市辖区', 'districts_code', '行政区划', '2950', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520586', '520102', '贵州贵阳市南明区', 'districts_code', '行政区划', '2951', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520587', '520103', '贵州贵阳市云岩区', 'districts_code', '行政区划', '2952', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520588', '520111', '贵州贵阳市花溪区', 'districts_code', '行政区划', '2953', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520589', '520112', '贵州贵阳市乌当区', 'districts_code', '行政区划', '2954', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520590', '520113', '贵州贵阳市白云区', 'districts_code', '行政区划', '2955', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520591', '520114', '贵州贵阳市小河区', 'districts_code', '行政区划', '2956', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520592', '520121', '贵州开阳县', 'districts_code', '行政区划', '2957', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520593', '520122', '贵州息烽县', 'districts_code', '行政区划', '2958', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520594', '520123', '贵州修文县', 'districts_code', '行政区划', '2959', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520595', '520181', '贵州清镇市', 'districts_code', '行政区划', '2960', '520100', '0', '', null);
INSERT INTO `core_dict` VALUES ('520596', '1', '星级酒店', 'hotel_type', '酒店类型', '1', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520597', '2', '精品酒店', 'hotel_type', '酒店类型', '2', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520598', '3', '经济连锁酒店', 'hotel_type', '酒店类型', '3', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520599', '4', '度假酒店', 'hotel_type', '酒店类型', '4', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520600', '0', '否', 'code_if', '是否', null, '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520601', '1', '是', 'code_if', '是否', null, '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520602', '1', '柜式', 'terminal_type', '终端类型', '1', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520603', '2', '桌面型', 'terminal_type', '终端类型', '2', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520604', 'MAIN', '主程序使用', 'para_type', '终端参数分类', '1', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520605', 'PMS', 'PMS使用', 'para_type', '终端参数分类', '2', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520606', 'LOCK', '门锁使用', 'para_type', '终端参数分类', '3', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520607', 'POS', '支付使用', 'para_type', '终端参数分类', '4', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520608', 'INVOICE', '税控使用', 'para_type', '终端参数分类', '5', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520609', 'POLICE', '公安上传', 'para_type', '终端参数分类', '6', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520610', 'HAAS', '硬件服务配置', 'para_type', '终端参数分类', '7', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520611', 'JDBMSMQ', '消息推送使用', 'para_type', '终端参数分类', '8', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520612', 'REFUND', '退款服务', 'para_type', '终端参数分类', '9', '', '0', '', null);
INSERT INTO `core_dict` VALUES ('520613', '009', '千里马', 'para_category', '终端参数小类', '1', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520614', '008', '住哲', 'para_category', '终端参数小类', '2', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520615', '001', '金天鹅', 'para_category', '终端参数小类', '3', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520616', '002', '别样红', 'para_category', '终端参数小类', '4', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520617', '003', 'Opera', 'para_category', '终端参数小类', '5', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520618', '004', '西软', 'para_category', '终端参数小类', '6', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520619', '005', '中软众荟C/S', 'para_category', '终端参数小类', '7', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520620', '006', '格林', 'para_category', '终端参数小类', '8', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520621', '007', '万维', 'para_category', '终端参数小类', '9', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520622', '010', '绿云', 'para_category', '终端参数小类', '10', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520623', '011', '雅斯特', 'para_category', '终端参数小类', '11', '520605', '0', '', null);
INSERT INTO `core_dict` VALUES ('520624', 'HX', '航信', 'para_category', '终端参数小类', '1', '520609', '0', '', null);
INSERT INTO `core_dict` VALUES ('520625', 'WW', '万维', 'para_category', '终端参数小类', '2', '520609', '0', '', null);
INSERT INTO `core_dict` VALUES ('520626', 'PLD', '普兰德', 'para_category', '终端参数小类', '1', '520606', '0', '', null);
INSERT INTO `core_dict` VALUES ('520627', 'JW', '劲卫', 'para_category', '终端参数小类', '2', '520606', '0', '', null);
INSERT INTO `core_dict` VALUES ('520628', 'BD2', '必达', 'para_category', '终端参数小类', '3', '520606', '0', '', null);
INSERT INTO `core_dict` VALUES ('520629', 'BZ', '奔智', 'para_category', '终端参数小类', '4', '520606', '0', '', null);
INSERT INTO `core_dict` VALUES ('520630', 'XR', '鑫雅', 'para_category', '终端参数小类', '5', '520606', '0', '', null);
INSERT INTO `core_dict` VALUES ('520631', 'ADEL', '爱迪尔', 'para_category', '终端参数小类', '6', '520606', '0', '', null);
INSERT INTO `core_dict` VALUES ('520632', 'DJE2', '第吉尔2代', 'para_category', '终端参数小类', '7', '520606', '0', '', null);
INSERT INTO `core_dict` VALUES ('520633', 'DJE3', '第吉尔3代', 'para_category', '终端参数小类', '8', '520606', '0', '', null);
INSERT INTO `core_dict` VALUES ('520634', 'YW', '远维', 'para_category', '终端参数小类', '9', '520606', '0', '', null);
INSERT INTO `core_dict` VALUES ('520635', 'ICBC', '工行', 'para_category', '终端参数小类', null, '520607', '0', '工行为Icbc，银联为Union', null);
INSERT INTO `core_dict` VALUES ('520636', 'UNION', '银联', 'para_category', '终端参数小类', null, '520607', '0', '工行为Icbc，银联为Union', null);
INSERT INTO `core_dict` VALUES ('520637', 'K750Port', '发卡机配置', 'para_category', '终端参数小类', null, '520610', '0', '', null);
INSERT INTO `core_dict` VALUES ('520638', 'IDREAD', '身份证阅读器', 'para_category', '终端参数小类', null, '520610', '0', '', null);
INSERT INTO `core_dict` VALUES ('520639', 'DPRINT', '桌面小票机', 'para_category', '终端参数小类', null, '520610', '0', '', null);
INSERT INTO `core_dict` VALUES ('520640', 'QRCODE', '扫码枪使用', 'para_category', '终端参数小类', null, '520610', '0', '', null);
INSERT INTO `core_dict` VALUES ('520641', 'PRINT', '小票机', 'para_category', '终端参数小类', null, '520610', '0', '', null);
INSERT INTO `core_dict` VALUES ('520642', 'DY', '达因', 'para_category', '终端参数小类', null, '520609', '0', '', '2019-11-02 16:10:15.541000');
INSERT INTO `core_dict` VALUES ('520643', 'HDTY', '华动泰岳', 'para_category', '终端参数小类', null, '520609', '0', '', '2019-11-02 17:39:30.556000');
INSERT INTO `core_dict` VALUES ('520644', 'HBHX', '湖北航信', 'para_category', '终端参数小类', null, '520609', '0', '', '2019-11-02 17:48:33.169000');
INSERT INTO `core_dict` VALUES ('520645', 'JG', '金圭', 'para_category', '终端参数小类', null, '520609', '0', '', '2019-11-04 10:31:58.273000');
INSERT INTO `core_dict` VALUES ('520646', 'business-pms', 'PMS日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:33:19.443000');
INSERT INTO `core_dict` VALUES ('520647', 'business-police', 'PSB日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:35:11.574000');
INSERT INTO `core_dict` VALUES ('520648', 'business-tomcat-refund', 'TomcatRefund日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:37:03.419000');
INSERT INTO `core_dict` VALUES ('520649', 'business-check', '自检服务日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:37:27.264000');
INSERT INTO `core_dict` VALUES ('520650', 'business-refund', 'Refund自动退款日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:38:45.442000');
INSERT INTO `core_dict` VALUES ('520651', 'business-pay', '支付服务日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:39:54.890000');
INSERT INTO `core_dict` VALUES ('520652', 'business-tomcat', 'Tomcat日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:40:21.464000');
INSERT INTO `core_dict` VALUES ('520653', 'business-updata', '自动升级服务日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:40:42.994000');
INSERT INTO `core_dict` VALUES ('520654', 'business-haas', '硬件服务日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:40:59.800000');
INSERT INTO `core_dict` VALUES ('520655', 'business-main', '主程序日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:41:18.366000');
INSERT INTO `core_dict` VALUES ('520656', 'business-metricbeat', '性能检测日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:42:39.275000');
INSERT INTO `core_dict` VALUES ('520657', 'business-pms-refund', 'PmsRefund日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:43:13.614000');
INSERT INTO `core_dict` VALUES ('520658', 'metricbeat', '服务性能日志', 'indexs_log', '日志索引名', null, '', '0', '', '2019-11-05 10:43:30.166000');

-- ----------------------------
-- Table structure for core_sequence
-- ----------------------------
DROP TABLE IF EXISTS `core_sequence`;
CREATE TABLE `core_sequence` (
  `sequence_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '序列名称',
  `value` bigint(20) NOT NULL DEFAULT '0' COMMENT '当前值',
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_sequence
-- ----------------------------
INSERT INTO `core_sequence` VALUES ('BLOC_SEQUENCE', '94');
INSERT INTO `core_sequence` VALUES ('HOTEL_SEQUENCE', '118');
INSERT INTO `core_sequence` VALUES ('TEMPLATE_PARA_SEQUENCE', '13');
INSERT INTO `core_sequence` VALUES ('TERMINAL_SEQUENCE', '173');

-- ----------------------------
-- Table structure for sys_announcement
-- ----------------------------
DROP TABLE IF EXISTS `sys_announcement`;
CREATE TABLE `sys_announcement` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `hi_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '酒店id',
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `content` varchar(10000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `deltag` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '删除标识',
  `createuser` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyuser` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `modifytime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_announcement
-- ----------------------------
INSERT INTO `sys_announcement` VALUES ('1', 'HOTEL1474449797', '星期一', '多岁的发生的发生', '1', null, '2019-03-04 16:34:22', null, null);
INSERT INTO `sys_announcement` VALUES ('2', 'HOTEL1474449797', '天气很好', '多岁的发生的发生', '1', null, '2019-03-04 16:34:22', null, '2019-03-04 16:37:10');
INSERT INTO `sys_announcement` VALUES ('3', 'HOTEL1474449797', '好嗨哦', '多岁的发生的发生', '1', null, '2019-03-04 16:34:22', null, '2019-03-04 16:35:49');
INSERT INTO `sys_announcement` VALUES ('4', 'HOTEL1474449797', '工作', '多岁的发生的发生', '1', null, '2019-03-04 16:34:22', null, '2019-03-04 16:36:28');
INSERT INTO `sys_announcement` VALUES ('5', 'HOTEL1474449797', '测试测试测试', '<h1 style=\"text-align: center;\">测试测试测试</h1><p><br></p><p><span style=\"color: rgb(194, 79, 74);\">阿萨德阿萨德</span></p><p><span style=\"color: rgb(194, 79, 74);\">阿萨德</span></p><p style=\"text-align: center;\"><img src=\"http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif\" alt=\"[神马]\" data-w-e=\"1\"><img src=\"http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif\" alt=\"[神马]\" data-w-e=\"1\"><span style=\"color: rgb(194, 79, 74);\"><br></span></p>', '1', '超级管理员', '2019-03-07 14:56:02', '超级管理员', '2019-03-07 15:16:44');
INSERT INTO `sys_announcement` VALUES ('6', 'HOTEL1474449797', '啊啊啊啊啊', '<h1>啊啊啊啊啊</h1><p>阿萨德</p><p><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\"><br></p>', '1', '超级管理员', '2019-03-07 15:19:28', '超级管理员', '2019-03-07 15:35:27');
INSERT INTO `sys_announcement` VALUES ('8', 'HOTEL1474449797', '习近平这样关心少数民族群众', '<h1 style=\"text-align: center;\">习近平这样关心少数民族群众</h1><p>中华民族一家亲，同心共筑中国梦。3月5日，习近平在参加十三届全国人大二次会议内蒙古代表团审议时指出，内蒙古生态状况如何，不仅关系全区各族群众生存和发展，而且关系华北、东北、西北乃至全国生态安全。解决好人民群众反映强烈的突出环境问题，不断增强人民群众获得感、幸福感、安全感，这些只是习近平长期关心少数民族群众的缩影。党的十八大以来，他连续7年两会“到团组”，多次与少数民族代表共话民族团结、共商脱贫大计、共谋发展之策。一系列重要讲话中，不乏通俗易懂、入耳入心的精辟话语，央视网特进行梳理，与您一起学习。</p><h1><p></p></h1>', '0', '超级管理员', '2019-03-07 16:06:22', '超级管理员', '2019-03-21 18:58:27');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `deltag` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `sort` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=979 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('778', '性别', 'code_gender', '2', '女', '0', '', null);
INSERT INTO `sys_dict` VALUES ('779', '性别', 'code_gender', '2', '男', '0', '', null);
INSERT INTO `sys_dict` VALUES ('780', '否', 'code_if', '0', '否', '0', '', '1');
INSERT INTO `sys_dict` VALUES ('781', '是', 'code_if', '1', '是', '0', '', '0');
INSERT INTO `sys_dict` VALUES ('782', '订单状态', 'code_ordstatus', 'X', '已取消', '0', '', null);
INSERT INTO `sys_dict` VALUES ('783', '订单状态', 'code_ordstatus', 'R', '预定中', '0', '', null);
INSERT INTO `sys_dict` VALUES ('784', '账务类型', 'code_accountstype', '0', '消费账', '', '', null);
INSERT INTO `sys_dict` VALUES ('785', '账务类型', 'code_accountstype', '1', '结算账', '', '', null);
INSERT INTO `sys_dict` VALUES ('786', '房间类型', 'code_roomtype', '1', '单人间', '', '', null);
INSERT INTO `sys_dict` VALUES ('787', '房间类型', 'code_roomtype', '2', '双人间', '', '', null);
INSERT INTO `sys_dict` VALUES ('788', '房间类型', 'code_roomtype', '3', '三人间', '', '', null);
INSERT INTO `sys_dict` VALUES ('789', '房间类型', 'code_roomtype', '4', '商务标间', '', '', null);
INSERT INTO `sys_dict` VALUES ('790', '担保类型', 'code_assuretype', '0', '无担保', '', '', null);
INSERT INTO `sys_dict` VALUES ('791', '担保类型', 'code_assuretype', '1', '担保到达', '', '', null);
INSERT INTO `sys_dict` VALUES ('792', '担保类型', 'code_assuretype', '2', '全程担保', '', '', null);
INSERT INTO `sys_dict` VALUES ('793', '入住单状态', 'code_checkinstatus', 'I', '在住', '', '', null);
INSERT INTO `sys_dict` VALUES ('794', '入住单状态', 'code_checkinstatus', 'S', '已退未结', '', '', null);
INSERT INTO `sys_dict` VALUES ('795', '入住单状态', 'code_checkinstatus', 'O', '已退已结', '', '', null);
INSERT INTO `sys_dict` VALUES ('796', '入住单状态', 'code_checkinstatus', 'WR', '待查房', '', '', null);
INSERT INTO `sys_dict` VALUES ('797', '房间状态', 'code_roomstatus', 'VC', '空净房', '', '', null);
INSERT INTO `sys_dict` VALUES ('798', '房间状态', 'code_roomstatus', 'VD', '空脏房', '', '', null);
INSERT INTO `sys_dict` VALUES ('799', '房间状态', 'code_roomstatus', 'OC', '在住净房', '', '', null);
INSERT INTO `sys_dict` VALUES ('800', '房间状态', 'code_roomstatus', 'OD', '在住脏房', '', '', null);
INSERT INTO `sys_dict` VALUES ('801', '房间状态', 'code_roomstatus', 'OO', '维修中', '', '', null);
INSERT INTO `sys_dict` VALUES ('802', '支付方式', 'code_payment', 'CHH_ALIPAY', '支付宝支付', '', '', '1');
INSERT INTO `sys_dict` VALUES ('803', '支付方式', 'code_payment', 'CHH_WECHATPAY', '微信支付', '', '', '2');
INSERT INTO `sys_dict` VALUES ('804', '支付方式', 'code_payment', 'CHH_YHK', '银行卡支付', '', '', '3');
INSERT INTO `sys_dict` VALUES ('805', '支付方式', 'code_payment', 'CHH_CASH', '现金支付', '', '', '4');
INSERT INTO `sys_dict` VALUES ('806', '入住方式', 'code_checkinmode', '1', '全天房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('807', '入住方式', 'code_checkinmode', '10', '免费房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('808', '入住方式', 'code_checkinmode', '11', '长包房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('809', '入住方式', 'code_checkinmode', '12', '旅游', '0', '', null);
INSERT INTO `sys_dict` VALUES ('810', '入住方式', 'code_checkinmode', '132', '会议', '0', '', null);
INSERT INTO `sys_dict` VALUES ('811', '入住方式', 'code_checkinmode', '14', '自用房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('812', '入住方式', 'code_checkinmode', '15', '特殊房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('813', '入住方式', 'code_checkinmode', '2', '钟点房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('814', '入住方式', 'code_checkinmode', '21', '一小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('815', '入住方式', 'code_checkinmode', '22', '二小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('816', '入住方式', 'code_checkinmode', '23', '三小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('817', '入住方式', 'code_checkinmode', '24', '四小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('818', '入住方式', 'code_checkinmode', '25', '五小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('819', '入住方式', 'code_checkinmode', '26', '六小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('820', '入住方式', 'code_checkinmode', '27', '七小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('821', '入住方式', 'code_checkinmode', '28', '八小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('822', '入住方式', 'code_checkinmode', '29', '九小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('823', '入住方式', 'code_checkinmode', '210', '十小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('824', '入住方式', 'code_checkinmode', '211', '十一小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('825', '入住方式', 'code_checkinmode', '212', '十二小时房', '0', '', null);
INSERT INTO `sys_dict` VALUES ('826', '入账项目编码', 'code_accountscategory', 'CHH_CASH_SK', '人民币现金收款', '', '1', null);
INSERT INTO `sys_dict` VALUES ('827', '入账项目编码', 'code_accountscategory', 'CHH_CASH_YJ', '人民币现金押金', '', '1', null);
INSERT INTO `sys_dict` VALUES ('828', '入账项目编码', 'code_accountscategory', 'CHH_CASH_TK', '人民币现金退款', '', '1', null);
INSERT INTO `sys_dict` VALUES ('829', '入账项目编码', 'code_accountscategory', 'CHH_WECHAT_SK', '微信支付收款', '', '1', null);
INSERT INTO `sys_dict` VALUES ('830', '入账项目编码', 'code_accountscategory', 'CHH_WECHAT_YJ', '微信支付押金', '', '1', null);
INSERT INTO `sys_dict` VALUES ('831', '入账项目编码', 'code_accountscategory', 'CHH_WECHAT_TK', '微信支付退款', '', '1', null);
INSERT INTO `sys_dict` VALUES ('832', '入账项目编码', 'code_accountscategory', 'CHH_ALIPAY_SK', '支付宝收款', '', '1', null);
INSERT INTO `sys_dict` VALUES ('833', '入账项目编码', 'code_accountscategory', 'CHH_ALIPAY_YJ', '支付宝押金', '', '1', null);
INSERT INTO `sys_dict` VALUES ('834', '入账项目编码', 'code_accountscategory', 'CHH_ALIPAY_TK', '支付宝退款', '', '1', null);
INSERT INTO `sys_dict` VALUES ('835', '入账项目编码', 'code_accountscategory', 'CHH_YHK_SK', '银行卡收款', '', '1', null);
INSERT INTO `sys_dict` VALUES ('836', '入账项目编码', 'code_accountscategory', 'CHH_YHK_YJ', '银行卡押金', '', '1', null);
INSERT INTO `sys_dict` VALUES ('837', '入账项目编码', 'code_accountscategory', 'CHH_YHK_TK', '银行卡退款', '', '1', null);
INSERT INTO `sys_dict` VALUES ('838', '入账项目编码', 'code_accountscategory', 'CHH_QRZ_FF', '全日租', '', '0', null);
INSERT INTO `sys_dict` VALUES ('839', '入账项目编码', 'code_accountscategory', 'CHH_BRZ_FF', '半日租', '', '0', null);
INSERT INTO `sys_dict` VALUES ('840', '入账项目编码', 'code_accountscategory', 'CHH_YJ', '押金', '', '0', null);
INSERT INTO `sys_dict` VALUES ('841', '入账项目编码', 'code_accountscategory', 'CHH_DAYRENT', '夜审房费', '', '0', null);
INSERT INTO `sys_dict` VALUES ('842', '入账项目编码', 'code_accountscategory', 'CHH_CONSUME', '消费品', '', '0', null);
INSERT INTO `sys_dict` VALUES ('843', '入账项目编码', 'code_accountscategory', 'CHH_BROKEN', '损毁', '', '0', null);
INSERT INTO `sys_dict` VALUES ('844', '入账项目编码', 'code_accountscategory', 'CHH_TICKET', '餐券', '', '0', null);
INSERT INTO `sys_dict` VALUES ('845', '入账项目编码', 'code_accountscategory', 'CHH_CLOTHES', '洗衣费', '', '0', null);
INSERT INTO `sys_dict` VALUES ('846', '入账项目编码', 'code_accountscategory', 'CHH_OTHER', '其他', '', '0', null);
INSERT INTO `sys_dict` VALUES ('847', '入账项目编码', 'code_accountscategory', 'CHH_VIP', '会员', '', '0', null);
INSERT INTO `sys_dict` VALUES ('848', '时间', 'code_time', '0', '0点', '', '', null);
INSERT INTO `sys_dict` VALUES ('849', '时间', 'code_time', '1', '1点', '', '', null);
INSERT INTO `sys_dict` VALUES ('850', '时间', 'code_time', '2', '2点', '', '', null);
INSERT INTO `sys_dict` VALUES ('851', '时间', 'code_time', '3', '3点', '', '', null);
INSERT INTO `sys_dict` VALUES ('852', '时间', 'code_time', '4', '4点', '', '', null);
INSERT INTO `sys_dict` VALUES ('853', '时间', 'code_time', '5', '5点', '', '', null);
INSERT INTO `sys_dict` VALUES ('854', '时间', 'code_time', '6', '6点', '', '', null);
INSERT INTO `sys_dict` VALUES ('855', '时间', 'code_time', '7', '7点', '', '', null);
INSERT INTO `sys_dict` VALUES ('856', '时间', 'code_time', '8', '8点', '', '', null);
INSERT INTO `sys_dict` VALUES ('857', '时间', 'code_time', '9', '9点', '', '', null);
INSERT INTO `sys_dict` VALUES ('858', '时间', 'code_time', '10', '10点', '', '', null);
INSERT INTO `sys_dict` VALUES ('859', '时间', 'code_time', '11', '11点', '', '', null);
INSERT INTO `sys_dict` VALUES ('860', '时间', 'code_time', '12', '12点', '', '', null);
INSERT INTO `sys_dict` VALUES ('861', '时间', 'code_time', '13', '13点', '', '', null);
INSERT INTO `sys_dict` VALUES ('862', '时间', 'code_time', '14', '14点', '', '', null);
INSERT INTO `sys_dict` VALUES ('863', '时间', 'code_time', '15', '15点', '', '', null);
INSERT INTO `sys_dict` VALUES ('864', '时间', 'code_time', '16', '16点', '', '', null);
INSERT INTO `sys_dict` VALUES ('865', '时间', 'code_time', '17', '17点', '', '', null);
INSERT INTO `sys_dict` VALUES ('866', '时间', 'code_time', '18', '18点', '', '', null);
INSERT INTO `sys_dict` VALUES ('867', '时间', 'code_time', '19', '19点', '', '', null);
INSERT INTO `sys_dict` VALUES ('868', '时间', 'code_time', '20', '20点', '', '', null);
INSERT INTO `sys_dict` VALUES ('869', '时间', 'code_time', '21', '21点', '', '', null);
INSERT INTO `sys_dict` VALUES ('870', '时间', 'code_time', '22', '22点', '', '', null);
INSERT INTO `sys_dict` VALUES ('871', '时间', 'code_time', '23', '23点', '', '', null);
INSERT INTO `sys_dict` VALUES ('872', '证件类型', 'code_papers', '0', '不限', '', '', null);
INSERT INTO `sys_dict` VALUES ('873', '证件类型', 'code_papers', '1', '居民身份证', '', '', null);
INSERT INTO `sys_dict` VALUES ('874', '证件类型', 'code_papers', '2', '护照', '', '', null);
INSERT INTO `sys_dict` VALUES ('875', '证件类型', 'code_papers', '21', '外交护照', '', '', null);
INSERT INTO `sys_dict` VALUES ('876', '证件类型', 'code_papers', '22', '公务护照', '', '', null);
INSERT INTO `sys_dict` VALUES ('877', '证件类型', 'code_papers', '23', '因公普通护照', '', '', null);
INSERT INTO `sys_dict` VALUES ('878', '证件类型', 'code_papers', '24', '普通护照', '', '', null);
INSERT INTO `sys_dict` VALUES ('879', '证件类型', 'code_papers', '3', '驾照', '', '', null);
INSERT INTO `sys_dict` VALUES ('880', '证件类型', 'code_papers', '4', '工作证', '', '', null);
INSERT INTO `sys_dict` VALUES ('881', '证件类型', 'code_papers', '5', '军官证', '', '', null);
INSERT INTO `sys_dict` VALUES ('882', '证件类型', 'code_papers', '51', '士兵证', '', '', null);
INSERT INTO `sys_dict` VALUES ('883', '证件类型', 'code_papers', '6', '学生证', '', '', null);
INSERT INTO `sys_dict` VALUES ('884', '证件类型', 'code_papers', '7', '台胞证', '', '', null);
INSERT INTO `sys_dict` VALUES ('885', '证件类型', 'code_papers', '8', '港澳回乡证', '', '', null);
INSERT INTO `sys_dict` VALUES ('886', '证件类型', 'code_papers', '9', '其它', '', '', null);
INSERT INTO `sys_dict` VALUES ('887', '证件类型', 'code_papers', '10', '户口簿', '', '', null);
INSERT INTO `sys_dict` VALUES ('888', '证件类型', 'code_papers', '11', '警官证', '', '', null);
INSERT INTO `sys_dict` VALUES ('889', '银行', 'code_bank', 'Union', '银联', '', '', null);
INSERT INTO `sys_dict` VALUES ('890', '银行', 'code_bank', 'Icbc ', '工行', '', '', null);
INSERT INTO `sys_dict` VALUES ('891', '支付动作', 'code_payaction', 'pay', '线上', '', '', null);
INSERT INTO `sys_dict` VALUES ('892', '支付动作', 'code_payaction', 'pos', '线下', '', '', null);
INSERT INTO `sys_dict` VALUES ('893', '支付状态', 'code_paystatus', '1', '支付成功', '', '', null);
INSERT INTO `sys_dict` VALUES ('894', '支付状态', 'code_paystatus', '0', '支付失败', '', '', null);
INSERT INTO `sys_dict` VALUES ('953', '退房模式', 'code_checkoutpattern', '1', '在线退房', '', '', null);
INSERT INTO `sys_dict` VALUES ('954', '退房模式', 'code_checkoutpattern', '0', '离线退房', '', '', null);
INSERT INTO `sys_dict` VALUES ('955', '团队结账方式 ', 'code_groupsettletype', '0', '团付', '', '', null);
INSERT INTO `sys_dict` VALUES ('956', '团队结账方式 ', 'code_groupsettletype', '1', '个付', '', '', null);
INSERT INTO `sys_dict` VALUES ('957', '应用类型', 'code_applytype', '1', '银行卡', '', '', null);
INSERT INTO `sys_dict` VALUES ('958', '应用类型', 'code_applytype', '4', '线上B扫C', '', '', null);
INSERT INTO `sys_dict` VALUES ('959', '应用类型', 'code_applytype', '5', '线上C扫B', '', '', null);
INSERT INTO `sys_dict` VALUES ('960', '交易类型(业务)', 'code_trantypebs', '1', '担保', '', '', null);
INSERT INTO `sys_dict` VALUES ('961', '交易类型(业务)', 'code_trantypebs', '0', '消费', '', '', null);
INSERT INTO `sys_dict` VALUES ('962', '交易类型(业务)', 'code_trantypebs', '2', '担保完成', '', '', null);
INSERT INTO `sys_dict` VALUES ('963', '交易类型(业务)', 'code_trantypebs', '3', '预授权', '', '', null);
INSERT INTO `sys_dict` VALUES ('964', '交易类型(业务)', 'code_trantypebs', '4', '预授权完成', '', '', null);
INSERT INTO `sys_dict` VALUES ('965', '交易类型(业务)', 'code_trantypebs', '9', '无消费', '', '', null);
INSERT INTO `sys_dict` VALUES ('966', '交易类型(业务)', 'code_trantypebs', '5', '担保撤销', '', '', null);
INSERT INTO `sys_dict` VALUES ('967', '交易类型(业务)', 'code_trantypebs', '6', '预授权撤销', '', '', null);
INSERT INTO `sys_dict` VALUES ('972', '退款状态', 'code_checkoutpay', '0', '未退', null, null, null);
INSERT INTO `sys_dict` VALUES ('973', '退款状态', 'code_checkoutpay', '1', '已退', null, null, null);
INSERT INTO `sys_dict` VALUES ('974', '退款状态', 'code_checkoutpay', '2', '处理中', null, null, null);
INSERT INTO `sys_dict` VALUES ('975', null, 'test', null, null, '1', null, null);
INSERT INTO `sys_dict` VALUES ('976', null, 'test1', null, null, '0', null, '2');
INSERT INTO `sys_dict` VALUES ('977', 'e', 'code', 'r', 'e', '0', 'fff', '0');
INSERT INTO `sys_dict` VALUES ('978', '55', 'ddd', 'dd', 'ff', '0', 'ee', '0');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `taskId` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '主键编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '文件名',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文件类型',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '文件路径',
  `suffix` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `createuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `modifyuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `deltag` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0',
  `createuser_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifyuser_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `hotelId` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `inputType` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('1548208879066', '004dcabb-c128-45f3-a68b-5623cd9e4981', '20190114183348.xls', 'application/vnd.ms-excel', '20190123//940cd2e4-3f2c-4d22-aa0c-48b1171b2da3.xls', 'xls', '2019-01-23 10:01:28', '1', null, null, '0', 'admin', null, 'HOTEL1474449797', null);
INSERT INTO `sys_file` VALUES ('1552907288274', '00504f9f-2370-4075-a0b3-c71c672e7e33', '20190318095035.xls', 'application/vnd.ms-excel', '20190318//f133af99-6132-4e8c-a212-9c656532e927.xls', 'xls', '2019-03-18 19:08:23', '1a3fb8784b3d44feaf8fab5c5c3af8dd', null, null, '0', 'baoli', null, '520113002', null);
INSERT INTO `sys_file` VALUES ('1552900779443', '0060e2ca-c62d-4b10-b603-9d8a0364c0d2', '20190318095035.xls', 'application/vnd.ms-excel', '20190318//234835c7-dfc9-400a-aa0f-948eaf72ef6d.xls', 'xls', '2019-03-18 17:19:54', '1a3fb8784b3d44feaf8fab5c5c3af8dd', null, null, '0', 'baoli', null, '520113002', null);

-- ----------------------------
-- Table structure for sys_file_colsconfig
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_colsconfig`;
CREATE TABLE `sys_file_colsconfig` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账单详细主键',
  `colname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '字段名称',
  `colcomments` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '字段注释',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5691 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='sys_file_colsconfig';

-- ----------------------------
-- Records of sys_file_colsconfig
-- ----------------------------
INSERT INTO `sys_file_colsconfig` VALUES ('35', 'c1', '清算日期', null, 'COMP-UPLOAD-PREVIEW');
INSERT INTO `sys_file_colsconfig` VALUES ('36', 'c2', '交易日期', null, 'COMP-UPLOAD-PREVIEW');
INSERT INTO `sys_file_colsconfig` VALUES ('37', 'c3', '交易时间', null, 'COMP-UPLOAD-PREVIEW');
INSERT INTO `sys_file_colsconfig` VALUES ('38', 'c4', '终端号', null, 'COMP-UPLOAD-PREVIEW');


-- ----------------------------
-- Table structure for sys_file_temp
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_temp`;
CREATE TABLE `sys_file_temp` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `taskid` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `hotelId` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `c1` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c1',
  `c2` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c2',
  `c3` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c3',
  `c4` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c4',
  `c5` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c5',
  `c6` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c6',
  `c7` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c7',
  `c8` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c8',
  `c9` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c9',
  `c10` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c10',
  `c11` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c11',
  `c12` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c12',
  `c13` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c13',
  `c14` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c14',
  `c15` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c15',
  `c16` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c16',
  `c17` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c17',
  `c18` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c18',
  `c19` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c19',
  `c20` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c20',
  `c21` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c21',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='对账临时表';

-- ----------------------------
-- Records of sys_file_temp
-- ----------------------------
INSERT INTO `sys_file_temp` VALUES ('0000e8bb-c000-4ad9-bf1c-d5b6cc0901be', '1548382906786', null, '20181020', '20-十月-2018', '20-十月-2018', '01228698', '0.01', '0.01', '0.0', '07552333965W', '286749********7558', '523622', '消费', '电子钱包', '支付宝钱包', '支付宝支付', '20181020202122007552333965', '', '', null, null, null, null);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户姓名',
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '方法',
  `params` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '参数',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'IP',
  `taketime` bigint(11) DEFAULT NULL COMMENT '耗时',
  `deltag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0' COMMENT '删除标签',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifytime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82555 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_log
-- ----------------------------


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `premission` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `createuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  `modifytime` datetime DEFAULT NULL,
  `modifyuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  `deltag` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0',
  `isfunc` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `pidname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `component` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `orderid` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', null, '系统管理', '', null, '2018-10-30 19:28:56', '', '2019-10-25 17:27:50', 'admin', '0', '0', 'sys', '/sys', '顶级菜单', '', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', 'sys:user:list', null, '2018-10-30 20:43:25', '', '2019-10-28 16:56:58', 'admin', '0', '0', 'user', 'user', '系统管理', 'sys/user', '2');
INSERT INTO `sys_menu` VALUES ('3', '2', '用户添加', 'sys:user:add', '', '2018-10-30 20:43:25', '', '2018-12-23 20:46:00', 'admin', '0', '1', 'fa fa-circle-o', '', '用户管理', null, null);
INSERT INTO `sys_menu` VALUES ('4', '2', '用户修改', 'sys:user:update,sys:user:info', null, '2018-11-13 21:16:04', '', '2018-11-13 21:16:07', '', '0', '1', 'fa fa-circle-o', null, '用户管理', null, null);
INSERT INTO `sys_menu` VALUES ('5', '2', '用户删除', 'sys:user:del', null, '2018-11-16 13:41:13', '', '2018-11-13 21:03:25', '', '0', '1', 'fa fa-circle-o', null, '用户管理', null, null);
INSERT INTO `sys_menu` VALUES ('6', '2', '用户授权', 'sys:user:prem,sys:role:list', null, '2018-11-16 13:41:30', '', '2018-11-13 21:03:25', '', '0', '1', 'fa fa-circle-o', null, '用户管理', null, null);
INSERT INTO `sys_menu` VALUES ('7', '1', '角色管理', 'sys:role:list', null, '2018-11-16 13:44:29', '', '2019-10-28 16:57:04', 'admin', '0', '0', 'role', 'role', '系统管理', 'sys/role', '3');
INSERT INTO `sys_menu` VALUES ('8', '7', '角色添加', 'sys:role:add', null, '2018-11-16 13:45:53', '', '2018-11-13 21:03:25', '', '0', '1', 'fa fa-circle-o', null, '角色管理', null, null);
INSERT INTO `sys_menu` VALUES ('9', '7', '角色修改', 'sys:role:edit,sys:role:info', null, '2018-11-16 13:47:27', '', '2018-11-13 21:03:25', '', '0', '1', 'fa fa-circle-o', null, '角色管理', null, null);
INSERT INTO `sys_menu` VALUES ('10', '7', '角色删除', 'sys:role:del', null, '2018-11-16 13:48:34', '', '2018-11-13 21:03:25', '', '0', '1', 'fa fa-circle-o', null, '角色管理', null, null);
INSERT INTO `sys_menu` VALUES ('11', '1', '菜单管理', 'sys:menu:list', null, '2018-11-16 13:49:16', '', '2019-10-28 16:57:11', 'admin', '0', '0', 'menu', 'menu', '系统管理', 'sys/menu', '4');
INSERT INTO `sys_menu` VALUES ('12', '11', '菜单添加', 'sys:menu:add', null, '2018-11-16 13:50:44', '', '2019-01-21 11:12:39', 'admin', '0', '1', 'fa fa-circle-o', null, '菜单管理', null, null);
INSERT INTO `sys_menu` VALUES ('13', '11', '菜单修改', 'sys:menu:edit,sys:menu:info', null, '2018-11-16 13:50:58', '', '2019-01-21 11:12:53', 'admin', '0', '1', 'fa fa-circle-o', null, '菜单管理', null, null);
INSERT INTO `sys_menu` VALUES ('14', '11', '菜单删除', 'sys:menu:del', null, '2018-11-16 13:51:08', '', '2019-01-21 11:13:06', 'admin', '0', '1', 'fa fa-circle-o', null, '菜单管理', null, null);
INSERT INTO `sys_menu` VALUES ('15', null, 'OTA订单管理', '', null, '2018-11-16 13:55:59', '', '2018-11-13 21:03:25', '', '1', '0', 'fa fa-file', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('16', '94', 'OTA订单查询', 'hotel:advancedOrder:list,hotel:advancedOrder:info', null, '2018-11-16 14:09:00', '', '2018-12-24 05:07:45', 'admin', '1', '1', 'fa fa-circle-o', '', 'OTA订单管理', null, null);
INSERT INTO `sys_menu` VALUES ('17', '94', 'OTA订单添加', 'hotel:advancedOrder:add', null, '2018-11-16 14:09:11', '', '2018-11-13 21:03:25', '', '1', '1', 'fa fa-circle-o', null, 'OTA订单管理', null, null);
INSERT INTO `sys_menu` VALUES ('18', '94', 'OTA订单取消', 'hotel:advancedOrder:cancel', null, '2018-11-16 14:09:46', '', '2018-11-13 21:03:25', '', '1', '1', 'fa fa-circle-o', null, 'OTA订单管理', null, null);
INSERT INTO `sys_menu` VALUES ('19', null, '订单管理', '', null, '2018-11-16 14:33:37', '', '2018-11-13 21:03:25', '', '1', '0', 'fa fa-file', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('21', '19', '待超时担保订单', '222', null, '2018-11-16 14:46:17', '', '2018-11-24 17:37:02', 'admin', '1', '0', 'fa fa-circle-o', null, '订单管理', null, null);
INSERT INTO `sys_menu` VALUES ('22', '19', '待超时入住订单', '1111', null, '2018-11-16 14:47:03', '', '2018-11-29 14:36:13', 'admin', '1', '0', 'fa fa-circle-o', null, '订单管理', null, null);
INSERT INTO `sys_menu` VALUES ('24', '87', '消费品信息查询', 'hotel:consumergoods:list,hotel:consumergoods:info', null, '2018-11-26 10:56:40', '', '2018-12-24 03:23:33', 'admin', '1', '1', 'fa fa-circle-o', '', '消费品管理', null, null);
INSERT INTO `sys_menu` VALUES ('25', '87', '消费品信息添加', 'hotel:consumergoods:add', null, '2018-11-26 10:59:43', '', '2019-01-21 11:16:30', 'admin', '1', '1', 'fa fa-circle-o', null, '消费品信息查询', null, null);
INSERT INTO `sys_menu` VALUES ('26', '87', '消费品信息删除', 'hotel:consumergoods:del', null, '2018-11-26 11:01:03', '', '2019-01-21 11:16:46', 'admin', '1', '1', 'fa fa-circle-o', null, '消费品信息查询', null, null);
INSERT INTO `sys_menu` VALUES ('27', '94', 'OTA订单支付', 'hotel:advancedOrder:pay', null, '2018-11-21 16:41:23', '', '2018-11-21 18:59:31', '', '1', '1', 'fa fa-circle-o', null, 'OTA订单查询', null, null);
INSERT INTO `sys_menu` VALUES ('28', '87', '消费品信息修改', 'hotel:consumergoods:edit', null, '2018-11-26 11:02:09', '', '2019-01-21 11:16:56', 'admin', '1', '1', 'fa fa-circle-o', null, '消费品信息查询', null, null);
INSERT INTO `sys_menu` VALUES ('31', '86', '用户添加', 'hotel:user:add', null, '2018-11-27 17:07:53', '', '2019-01-21 11:14:22', 'admin', '1', '1', 'fa fa-circle-o', null, '用户管理', null, null);
INSERT INTO `sys_menu` VALUES ('32', '86', '用户修改', 'hotel:user:update,hotel:user:info', null, '2018-11-27 17:07:55', '', '2019-01-21 11:14:32', 'admin', '1', '1', 'fa fa-circle-o', null, '用户管理', null, null);
INSERT INTO `sys_menu` VALUES ('33', '86', '用户删除', 'hotel:user:del', null, '2018-11-27 17:07:58', '', '2019-01-21 11:14:50', 'admin', '1', '1', 'fa fa-circle-o', null, '用户管理', null, null);
INSERT INTO `sys_menu` VALUES ('34', '86', '用户授权', 'hotel:user:prem,sys:role:list', null, '2018-11-27 17:08:00', '', '2019-01-21 11:16:00', 'admin', '1', '1', 'fa fa-circle-o', null, '用户管理', null, null);
INSERT INTO `sys_menu` VALUES ('35', '38', '结账退房', 'hotel:checkOut:list,hotel:checkOut,hotel:order:info,hotel:orderDetails:info,hotel:bill:billDetailInfo', '   ', '2018-11-22 13:51:32', '', '2019-03-16 15:11:55', 'admin', '1', '0', 'checkout', 'checkout', '结账退房', 'checkout', null);
INSERT INTO `sys_menu` VALUES ('36', null, '退款管理', null, null, '2018-12-06 16:54:37', '', '2019-01-01 21:26:04', 'admin', '1', '0', 'refund', '/refund', null, null, null);
INSERT INTO `sys_menu` VALUES ('37', '36', '退款管理', 'hotel:refund:list', null, '2018-12-06 16:56:04', '', '2019-01-01 21:27:03', 'admin', '1', '0', 'refund', 'refund', '退款管理', 'refund', null);
INSERT INTO `sys_menu` VALUES ('38', null, '结账退房', '', null, '2018-12-07 11:43:28', '', '2019-01-21 11:17:12', 'admin', '1', '0', 'checkout', '/checkout', null, '', '3');
INSERT INTO `sys_menu` VALUES ('39', '37', '退款', 'hotel:refund', null, '2018-12-07 13:54:40', '', null, '', '1', '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('40', '1', '系统日志管理', 'sys:log,sys:log:info', null, '2018-12-14 10:20:04', '', '2019-10-28 17:01:53', 'admin', '0', '0', 'log', 'log', '日志管理', 'sys/log', '6');
INSERT INTO `sys_menu` VALUES ('41', '19', '订单添加', 'order:guest:add', null, '2018-12-17 11:02:07', '', '2018-12-17 11:02:09', '', '1', '1', 'fa fa-circle-o', null, '订单管理', null, null);
INSERT INTO `sys_menu` VALUES ('42', '19', '订单取消', 'order:guest:cancel', null, '2018-12-17 11:03:52', '', '2018-12-17 11:03:54', '', '1', '1', 'fa fa-circle-o', null, '订单管理', null, null);
INSERT INTO `sys_menu` VALUES ('43', '35', '结账退房消费品添加', 'hotel:checkOut:consumergoods:add', null, '2018-12-21 18:29:53', '', null, '', '1', '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('44', null, '访客管理', '', null, '2019-01-02 17:22:51', '', '2019-01-21 11:30:11', 'admin', '1', '0', 'caller', '/caller', null, null, '8');
INSERT INTO `sys_menu` VALUES ('45', '44', '访客管理', 'caller:list,caller:info', null, '2019-01-03 09:34:04', '', '2019-01-21 11:30:21', 'admin', '1', '0', 'caller', 'caller', '访客管理', 'caller', null);
INSERT INTO `sys_menu` VALUES ('46', '45', '访客添加', 'caller:add', null, '2019-01-02 17:29:05', '', '2019-01-21 11:30:32', 'admin', '1', '1', 'fa fa-circle-o', null, '访客管理', null, null);
INSERT INTO `sys_menu` VALUES ('47', '45', '访客修改', 'caller:update', null, '2019-01-02 17:29:40', '', '2019-01-21 11:30:43', 'admin', '1', '1', 'fa fa-circle-o', null, '访客管理', null, null);
INSERT INTO `sys_menu` VALUES ('48', '45', '访客删除', 'caller:delete', null, '2019-01-02 17:31:13', '', '2019-03-11 19:51:21', 'admin', '1', '1', 'fa fa-circle-o', null, '访客管理', null, null);
INSERT INTO `sys_menu` VALUES ('57', '7', '角色授权', 'sys:role:prem,sys:menu:list', null, '2018-11-24 16:17:55', '', '2019-01-21 11:12:04', 'admin', '0', '1', 'fa fa-circle-o', null, '角色管理', null, null);
INSERT INTO `sys_menu` VALUES ('58', '1', '报表管理', 'export:list', null, '2018-12-21 10:34:10', '', '2019-01-21 20:49:06', 'admin', '1', '0', 'report', 'report', '报表管理', 'report', '14');
INSERT INTO `sys_menu` VALUES ('85', null, '酒店管理', null, null, '2018-12-24 00:09:12', 'admin', '2019-01-21 11:14:03', 'admin', '1', '0', 'hotel', '/hotel', null, null, '2');
INSERT INTO `sys_menu` VALUES ('86', '85', '用户管理', 'hotel:user:list', null, '2018-12-24 00:09:40', 'admin', '2019-01-21 20:49:21', 'admin', '1', '0', 'user', 'user', '酒店管理', 'hotel/user', '15');
INSERT INTO `sys_menu` VALUES ('87', '85', '消费品管理', 'hotel:consumergoods:list,hotel:consumergoods:info', null, '2018-12-24 00:10:51', 'admin', '2019-01-21 20:49:27', 'admin', '1', '0', 'consumerGoods', 'consumerGoods', '酒店管理', 'hotel/consumerGoods', '16');
INSERT INTO `sys_menu` VALUES ('88', null, '消息中心', null, null, '2018-12-24 00:12:08', 'admin', '2019-01-21 11:31:14', 'admin', '1', '0', 'message', '/message', null, null, '9');
INSERT INTO `sys_menu` VALUES ('89', '88', '消息中心', null, null, '2018-12-24 00:12:18', 'admin', '2019-01-21 11:31:21', 'admin', '1', '0', 'message', 'message', '消息中心', 'message', null);
INSERT INTO `sys_menu` VALUES ('90', null, '报表统计', null, null, '2018-12-24 00:16:48', 'admin', '2019-01-21 11:28:57', 'admin', '1', '0', 'report', '/report', null, null, '6');
INSERT INTO `sys_menu` VALUES ('91', null, '预订单管理', '', null, '2018-12-24 00:20:54', 'admin', '2018-12-25 02:46:09', 'admin', '1', '0', 'advancedOrder', '/advancedOrder', null, null, null);
INSERT INTO `sys_menu` VALUES ('94', '91', '预订单管理', 'hotel:advancedOrder:list,hotel:advancedOrder:info', null, '2018-12-24 01:46:24', 'admin', '2018-12-24 21:56:15', 'admin', '1', '0', 'advancedOrder', 'advancedOrder', '预订单管理', 'advancedOrder', null);
INSERT INTO `sys_menu` VALUES ('95', '90', '自助机账务汇总报表-（旧）', 'sys:report:summarybillDetails,sys:report:exportSummaryBillDetails', null, '2018-12-24 01:48:40', 'admin', '2019-09-07 11:30:28', 'admin', '1', '0', 'report', 'detailed', '报表统计', 'report/detailed', '17');
INSERT INTO `sys_menu` VALUES ('96', null, '订单管理', '', null, '2018-12-28 10:03:02', 'admin', '2019-01-21 11:27:32', 'admin', '1', '0', 'advancedOrder', '/orderManagement', null, null, '5');
INSERT INTO `sys_menu` VALUES ('97', '96', '订单查询', 'hotel:allOrder:list,hotel:order:info,hotel:orderDetails:info,hotel:bill:list', null, '2018-12-28 10:05:18', 'admin', '2019-01-22 17:02:27', 'admin', '1', '0', 'advancedOrder', 'orderQuery', '订单管理', 'orderManagement/orderQuery', '21');
INSERT INTO `sys_menu` VALUES ('98', '89', '消息查询', 'sys:message:list', null, '2018-12-29 16:54:24', 'admin', '2019-01-21 11:31:30', 'admin', '1', '1', null, null, '消息查询', null, null);
INSERT INTO `sys_menu` VALUES ('99', '89', '消息更新', 'sys:message:update', null, '2018-12-29 16:55:29', 'admin', '2019-01-21 11:31:42', 'admin', '1', '1', null, null, '消息更新', null, null);
INSERT INTO `sys_menu` VALUES ('100', '35', '结账退房账务添加', 'hotel:bill:add', null, '2019-01-03 09:29:50', 'admin', '2019-01-21 11:19:07', 'admin', '1', '1', null, null, '结账退房', null, null);
INSERT INTO `sys_menu` VALUES ('101', '35', '结账退房账务查询', 'hotel:bill:list', null, '2019-01-03 09:29:47', 'admin', '2019-01-21 11:19:30', 'admin', '1', '1', null, null, '结账退房', null, null);
INSERT INTO `sys_menu` VALUES ('102', '90', '酒店结算汇总报表', null, null, '2019-01-04 16:37:05', 'admin', '2019-01-21 20:53:23', 'admin', '1', '0', 'report', 'collect', '报表统计', 'report/collect', '20');
INSERT INTO `sys_menu` VALUES ('103', null, '快速对账', null, null, '2019-01-07 11:15:28', 'admin', '2019-01-21 11:29:49', 'admin', '1', '0', 'reconciliation', '/reconciliation', null, null, '7');
INSERT INTO `sys_menu` VALUES ('104', '103', '汇总报表对账', null, null, '2019-01-07 11:16:19', 'admin', '2019-03-16 17:27:09', 'admin', '1', '0', 'reconciliation', 'summary', '快速对账', 'reconciliation/summary', null);
INSERT INTO `sys_menu` VALUES ('105', '110', '入住到期订单', 'checkinMaturityOrder:list', null, '2019-01-07 16:29:00', '', '2019-01-21 11:28:07', 'admin', '1', '0', 'checkout', 'checkinMaturityOrder', '入住到期订单', 'orderRemind/checkinMaturityOrder', null);
INSERT INTO `sys_menu` VALUES ('106', '110', '超期退款订单', 'refundOverdueOrder:list', null, '2019-01-07 17:22:42', '', '2019-01-21 11:28:21', 'admin', '1', '0', 'checkout', 'refundOverdueOrder', '超期退款订单', 'orderRemind/refundOverdueOrder', null);
INSERT INTO `sys_menu` VALUES ('108', '90', '自助机账务明细报表', 'sys:report:billDetails,sys:report:exportBillDetails', null, '2019-01-15 14:11:27', 'admin', '2019-03-21 18:29:52', 'admin', '1', '0', 'report', 'accounting', '报表统计', 'report/accounting', '18');
INSERT INTO `sys_menu` VALUES ('109', '90', '当日收退款统计报表', null, null, '2019-01-17 15:05:23', 'dev', '2019-01-21 20:53:15', 'admin', '1', '0', 'report', 'collectrefund', '报表统计', 'report/collectrefund', '19');
INSERT INTO `sys_menu` VALUES ('111', '96', '订单提醒', 'refundOverdueOrder:list,checkinMaturityOrder:list,hotel:refund,hotel:orderDetails:info', null, '2019-01-21 19:39:38', '', '2019-01-22 16:53:32', 'admin', '1', '0', 'orderRemind', 'orderRemind', '订单提醒', 'orderManagement/orderRemind', '22');
INSERT INTO `sys_menu` VALUES ('112', '104', '文件上传', 'sys:filecompare:upload', null, '2019-01-22 16:01:30', 'admin', '2019-01-22 16:07:37', 'admin', '1', '1', null, null, '快速对账', null, null);
INSERT INTO `sys_menu` VALUES ('113', '104', '文件比对结果', 'sys:filecompare:result', null, '2019-01-22 16:02:27', 'admin', '2019-01-22 16:07:47', 'admin', '1', '1', null, null, '快速对账', null, null);
INSERT INTO `sys_menu` VALUES ('114', '104', '文件导出', 'sys:filecompare:export', null, '2019-01-22 16:03:03', 'admin', '2019-01-22 16:07:55', 'admin', '1', '1', null, null, '快速对账', null, null);
INSERT INTO `sys_menu` VALUES ('115', null, '团住二维码管理', null, null, '2019-01-25 16:48:36', 'admin', '2019-02-28 09:50:50', 'admin', '1', '0', 'QRCode', '/QRCode', null, null, '23');
INSERT INTO `sys_menu` VALUES ('116', '115', '添加团住', 'hotel:qrcode:list', null, '2019-01-25 16:50:24', 'admin', '2019-03-11 16:48:41', 'admin', '1', '0', 'QRCode', 'add', '团住二维码', 'QRCode/add', '25');
INSERT INTO `sys_menu` VALUES ('117', null, '哟哟哟哟', null, null, '2019-01-26 16:50:53', 'admin', '2019-01-26 16:51:48', 'admin', '1', '1', 'sys', null, '顶级菜单', null, '0');
INSERT INTO `sys_menu` VALUES ('118', null, '测试', null, null, '2019-02-13 15:43:52', 'admin', '2019-02-13 15:43:52', 'admin', '1', '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('119', null, '测试测试', null, null, '2019-02-13 15:44:22', 'admin', '2019-02-13 15:44:22', 'admin', '1', '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('120', '115', '团住二维码查询', 'hotel:orderGroupList,hotel:orderGroup:del', null, '2019-02-28 10:08:03', 'admin', '2019-03-20 22:58:30', 'admin', '1', '0', 'QRCode', 'teamQuery', '团住二维码管理', 'QRCode/teamQuery', '26');
INSERT INTO `sys_menu` VALUES ('121', '115', '客单二维码查询', 'hotel:guestList', null, '2019-02-28 10:09:49', 'admin', '2019-03-11 16:49:21', 'admin', '1', '0', 'QRCode', 'listQuery', '团住二维码管理', 'QRCode/listQuery', '27');
INSERT INTO `sys_menu` VALUES ('122', '1', '字典管理', 'sys:announcement:list,sys:announcement:info', null, '2019-03-07 11:13:42', 'admin', '2019-10-28 16:57:19', 'admin', '0', '0', 'dictionary', 'dictionary', '系统管理', 'sys/dictionary', '5');
INSERT INTO `sys_menu` VALUES ('123', '122', '添加系统通告', 'sys:announcement:add', null, '2019-03-07 16:08:52', 'admin', '2019-03-07 16:12:45', 'admin', '0', '1', null, null, '系统通告管理', null, null);
INSERT INTO `sys_menu` VALUES ('124', '122', '删除系统通告', 'sys:announcement:del', null, '2019-03-07 16:10:30', 'admin', '2019-03-07 16:10:30', 'admin', '0', '1', null, null, '系统通告管理', null, null);
INSERT INTO `sys_menu` VALUES ('125', '122', '修改系统通告', 'sys:announcement:update', null, '2019-03-07 16:11:05', 'admin', '2019-03-07 16:11:05', 'admin', '0', '1', null, null, '系统通告管理', null, null);
INSERT INTO `sys_menu` VALUES ('126', null, '首页', null, null, '2019-03-14 09:55:17', 'admin', '2019-03-14 10:18:10', 'admin', '1', '1', null, null, null, null, '30');
INSERT INTO `sys_menu` VALUES ('127', '126', '系统通告', 'sys:announcement:list,sys:announcement:info', null, '2019-03-14 09:56:18', 'admin', '2019-03-17 16:22:59', 'admin', '1', '1', null, null, '首页', null, null);
INSERT INTO `sys_menu` VALUES ('128', '126', '最近退房', 'hotel:checkOut:list,hotel:recentlyCheckOut:list', null, '2019-03-14 10:05:44', 'admin', '2019-03-14 10:08:56', 'admin', '1', '1', null, null, '首页', null, null);
INSERT INTO `sys_menu` VALUES ('129', '126', '在住客人', 'hotel:checkOut:list', null, '2019-03-14 10:06:13', 'admin', '2019-03-14 10:07:18', 'admin', '1', '1', null, null, '首页', null, null);
INSERT INTO `sys_menu` VALUES ('130', '126', '入住统计', 'hotel:checkin:count', null, '2019-03-14 10:06:30', 'admin', '2019-03-14 10:08:25', 'admin', '1', '1', null, null, '首页', null, null);
INSERT INTO `sys_menu` VALUES ('131', '103', '明细报表对账', null, null, '2019-03-16 17:25:26', 'admin', '2019-03-16 17:27:29', 'admin', '1', '0', 'reconciliation', 'detail', '快速对账', 'reconciliation/detail', null);
INSERT INTO `sys_menu` VALUES ('132', '131', '文件上传', 'sys:filecompare:upload', null, '2019-03-16 17:34:57', 'admin', '2019-03-16 17:34:57', 'admin', '1', '1', null, null, '明细报表对账', null, null);
INSERT INTO `sys_menu` VALUES ('133', '131', '文件比对结果', 'sys:filecompare:billdetailsresult', null, '2019-03-16 17:36:36', 'admin', '2019-03-21 18:37:32', 'admin', '1', '1', null, null, '明细报表对账', null, null);
INSERT INTO `sys_menu` VALUES ('134', '131', '文件导出', 'sys:filecompare:billdetailsexport', null, '2019-03-16 17:37:19', 'admin', '2019-03-21 18:38:01', 'admin', '1', '1', null, null, '明细报表对账', null, null);
INSERT INTO `sys_menu` VALUES ('135', '120', '团住订单删除', 'hotel:orderGroup:del', null, '2019-03-20 22:09:50', 'admin', '2019-03-21 09:46:27', 'admin', '1', '1', null, null, '团住二维码查询', null, null);
INSERT INTO `sys_menu` VALUES ('136', null, '退款管理', null, null, '2019-06-13 09:57:48', 'admin', '2019-06-13 09:57:48', 'admin', '1', '0', 'refund', '/refund', null, null, '24');
INSERT INTO `sys_menu` VALUES ('137', '136', '退款管理', 'hotel:refund:list', null, '2019-06-13 09:58:19', 'admin', '2019-06-13 09:58:19', 'admin', '1', '0', null, 'refund', '退款管理', 'refund', null);
INSERT INTO `sys_menu` VALUES ('138', null, '对账汇总', null, null, '2019-06-13 09:59:41', 'admin', '2019-06-13 09:59:41', 'admin', '1', '0', 'reconciliationSum', '/reconciliationSum', null, null, '25');
INSERT INTO `sys_menu` VALUES ('139', '138', '对账汇总', 'sys:report:summarybillDetails,sys:report:exportSummaryBillDetails', null, '2019-06-13 10:00:07', 'admin', '2019-06-13 10:00:07', 'admin', '1', '0', null, 'reconciliationSum', '对账汇总', 'reconciliationSum', null);
INSERT INTO `sys_menu` VALUES ('140', '85', '房型图片', null, null, '2019-08-27 15:50:25', 'admin', '2019-08-27 15:50:25', 'admin', '1', '0', 'roomImages', 'roomTypeImages', '酒店管理', 'hotel/roomTypeImages', '32');
INSERT INTO `sys_menu` VALUES ('141', null, '团队入住', null, null, '2019-08-27 15:52:54', 'admin', '2019-08-27 15:52:54', 'admin', '1', '0', 'advancedOrder', '/groupOrder', null, null, '31');
INSERT INTO `sys_menu` VALUES ('142', '141', '团队入住', null, null, '2019-08-27 15:55:51', 'admin', '2019-08-27 15:55:51', 'admin', '1', '0', 'advancedOrder', 'groupOrder', '团队入住', 'groupOrder', null);
INSERT INTO `sys_menu` VALUES ('143', '90', '对账汇总', 'sys:report:summarybillDetails,sys:report:exportSummaryBillDetails', null, '2019-09-07 11:10:51', 'admin', '2019-09-07 12:34:00', 'admin', '1', '0', 'reconciliationSum', 'reconciliationSum', '报表统计', 'report/reconciliationSum', '17');
INSERT INTO `sys_menu` VALUES ('144', '85', '酒店LOGO', null, null, '2019-09-17 11:13:51', 'yangmeng', '2019-09-17 11:14:07', 'yangmeng', '1', '0', 'logo', 'logo', '酒店管理', 'hotel/logo', '35');
INSERT INTO `sys_menu` VALUES ('145', '145', '集团管理', null, null, '2019-10-28 17:12:22', 'admin', '2019-10-28 17:15:35', 'admin', '0', '0', 'hotel', 'group', '酒店管理', 'hotel/group', null);
INSERT INTO `sys_menu` VALUES ('146', null, '酒店管理', null, null, '2019-10-28 17:20:23', 'admin', '2019-10-29 09:18:33', 'admin', '0', '0', 'hotel', '/hotel', '顶级菜单', null, '7');
INSERT INTO `sys_menu` VALUES ('147', '146', '集团管理', null, null, '2019-10-28 17:22:02', 'admin', '2019-11-01 15:39:33', 'admin', '0', '0', 'group', 'group', '酒店管理', 'hotel/group', '1');
INSERT INTO `sys_menu` VALUES ('148', null, '参数管理', null, null, '2019-10-28 18:15:18', 'admin', '2019-10-28 18:15:18', 'admin', '0', '0', 'param', '/param', null, null, '9');
INSERT INTO `sys_menu` VALUES ('149', '148', '参数结构管理', null, null, '2019-10-28 18:18:09', 'admin', '2019-10-28 18:18:09', 'admin', '0', '0', 'structure', 'structure', '参数管理', 'param/structure', '10');
INSERT INTO `sys_menu` VALUES ('150', '148', '参数模版管理', null, null, '2019-10-28 18:21:43', 'admin', '2019-10-28 18:22:51', 'admin', '0', '0', 'template', 'template', '参数管理', 'param/template', '11');
INSERT INTO `sys_menu` VALUES ('151', null, '版本管理', null, null, '2019-10-29 09:22:02', 'admin', '2019-10-29 10:15:38', 'admin', '0', '0', 'edition', '/edition', null, null, '12');
INSERT INTO `sys_menu` VALUES ('152', null, '产品管理', null, null, '2019-10-29 09:23:19', 'admin', '2019-10-29 10:16:15', 'admin', '0', '0', 'product', '/product', '顶级菜单', null, '14');
INSERT INTO `sys_menu` VALUES ('153', null, '远程运维', null, null, '2019-10-29 09:25:24', 'admin', '2019-10-29 10:15:46', 'admin', '0', '0', 'operation', '/operation', '顶级菜单', null, '13');
INSERT INTO `sys_menu` VALUES ('154', null, '统计报表', '', null, '2019-10-29 09:27:11', 'admin', '2019-10-29 10:16:21', 'admin', '0', '0', 'statistics', '/statistics', '顶级菜单', null, '15');
INSERT INTO `sys_menu` VALUES ('155', '151', '版本发布管理', null, null, '2019-10-29 09:37:25', 'admin', '2019-10-29 09:37:25', 'admin', '0', '0', 'release', 'release', '版本管理', 'edition/release', null);
INSERT INTO `sys_menu` VALUES ('156', '151', '版本升级管理', null, null, '2019-10-29 09:39:09', 'admin', '2019-11-05 15:59:04', 'admin', '0', '0', 'upgrade', 'upgrade', '版本管理', 'edition/upgrade', null);
INSERT INTO `sys_menu` VALUES ('157', '146', '酒店管理', null, null, '2019-10-29 09:41:53', 'admin', '2019-11-01 15:42:03', 'admin', '0', '0', 'hotel', 'hotel', '酒店管理', 'hotel/hotel', '2');
INSERT INTO `sys_menu` VALUES ('158', '146', '自助终端管理', null, null, '2019-10-29 09:44:54', 'admin', '2019-11-01 15:42:12', 'admin', '0', '0', 'terminal', 'terminal', '酒店管理', 'hotel/terminal', '3');
INSERT INTO `sys_menu` VALUES ('159', '152', '产品型号管理', null, null, '2019-10-29 09:47:57', 'admin', '2019-10-29 09:47:57', 'admin', '0', '0', 'model', 'model', '产品管理', 'product/model', null);
INSERT INTO `sys_menu` VALUES ('160', '152', '产品批次管理', null, null, '2019-10-29 09:50:15', 'admin', '2019-10-29 09:50:15', 'admin', '0', '0', 'batch', 'batch', '产品管理', 'product/batch', null);
INSERT INTO `sys_menu` VALUES ('161', '152', '产品镜像管理', null, null, '2019-10-29 09:52:07', 'admin', '2019-10-29 09:52:07', 'admin', '0', '0', 'image', 'image', '产品管理', 'product/image', null);
INSERT INTO `sys_menu` VALUES ('162', '153', '终端预警', null, null, '2019-10-29 09:54:09', 'admin', '2019-10-29 09:54:09', 'admin', '0', '0', 'warning', 'warning', '远程运维', 'operation/warning', null);
INSERT INTO `sys_menu` VALUES ('163', '153', '日志查询', null, null, '2019-10-29 09:56:11', 'admin', '2019-10-29 09:56:11', 'admin', '0', '0', 'journal', 'journal', '远程运维', 'operation/journal', null);
INSERT INTO `sys_menu` VALUES ('164', '153', '终端监控', null, null, '2019-10-29 09:57:45', 'admin', '2019-10-29 09:57:45', 'admin', '0', '0', 'monitor', 'monitor', '远程运维', 'operation/monitor', null);
INSERT INTO `sys_menu` VALUES ('165', '154', '终端统计', null, null, '2019-10-29 10:02:05', 'admin', '2019-11-05 16:21:07', 'admin', '0', '0', 'statistics', 'terminal_statistics', '统计报表', 'statistics/terminal_statistics', null);
INSERT INTO `sys_menu` VALUES ('166', '154', '终端异常统计', null, null, '2019-10-29 10:04:30', 'admin', '2019-11-05 16:21:02', 'admin', '0', '0', 'statistics', 'unusual', '统计报表', 'statistics/unusual', null);
INSERT INTO `sys_menu` VALUES ('167', '154', '终端状态统计', null, null, '2019-10-29 10:06:07', 'admin', '2019-11-05 16:21:10', 'admin', '0', '0', 'statistics', 'state', '统计报表', 'statistics/state', null);
INSERT INTO `sys_menu` VALUES ('168', null, '11', '111', null, '2019-11-02 15:53:41', 'admin', '2019-11-02 15:53:41', 'admin', '1', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `hi_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `pushdate` datetime DEFAULT NULL,
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `typeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `order` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100740 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_message
-- ----------------------------
INSERT INTO `sys_message` VALUES ('1', 'HOTEL1474449797', '1', '结账退房', '201房间 张三 已退房！', '2019-01-09 18:42:44', '结账退房', 'component', '1', '123123', null, null);
INSERT INTO `sys_message` VALUES ('2', 'HOTEL1474449797', '1', '结账退房', '201房间 张三 已退房！', '2019-01-03 13:13:13', '结账退房', 'component', '1', '123123', null, null);
INSERT INTO `sys_message` VALUES ('3', 'HOTEL1474449797', '1', '结账退房', '201房间 张三 已退房！123', '2019-01-03 13:13:13', '结账退房', 'component', '1', '123123', null, null);
INSERT INTO `sys_message` VALUES ('4', 'HOTEL1474449797', '1', '结账退房', '201房间 张三 已退房！', '2019-01-03 13:13:13', '结账退房', 'component', '1', '123123', null, null);
INSERT INTO `sys_message` VALUES ('5', 'HOTEL1474449797', '1', '结账退房', '201房间 张三 已退房！', '2019-01-03 13:13:13', '结账退房', 'component', '1', '123123', null, null);
INSERT INTO `sys_message` VALUES ('6', 'HOTEL1474449797', '1', '结账退房', '201房间 张三 已退房！', '2019-01-03 13:13:13', '结账退房', 'component', '1', '123123', null, null);
INSERT INTO `sys_message` VALUES ('98873', '520101048', 'MESSAGE', '退款消息提醒', '浪漫主题房 3052 [退款失败],退费金额大于可退款金额，请注意查看！', '2019-10-21 06:09:12', 'CHECKOUT', null, '0', null, '退款', '{\"or_checkin_id\":\"1437189\",\"ro_id\":\"3052\",\"ro_type_name\":\"浪漫主题房\"}');
-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `createuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `modifyuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `deltag` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0',
  `createuser_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifyuser_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `issystem` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `issystem_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '超级管理员', '2018-10-30 19:28:08', '1', '2019-01-28 11:55:02', '1', '0', 'admin', 'admin', '1', '是');
INSERT INTO `sys_role` VALUES ('2', '系统管理员', '系统管理员', '2018-12-10 11:59:45', '1', '2019-01-16 18:34:06', '1', '0', 'admin', 'admin', '1', '是');
INSERT INTO `sys_role` VALUES ('5', '酒店前台', '酒店前台', '2018-11-19 13:16:57', '1', '2018-12-29 00:38:13', '1', '0', 'admin', 'admin', '0', '否');
INSERT INTO `sys_role` VALUES ('6', '酒店管理员', '酒店管理员', '2018-11-19 13:16:57', '1', '2018-12-29 01:02:10', '1', '0', 'admin', 'admin', '0', '否');
INSERT INTO `sys_role` VALUES ('27', '测试', '测试', '2018-12-18 05:12:15', '1', '2018-12-18 05:12:15', '1', '1', 'admin', 'admin', '1', '是');
INSERT INTO `sys_role` VALUES ('28', '22222', 'ggggg', '2018-12-18 05:15:41', '1', '2018-12-19 00:12:23', '1', '1', 'admin', 'admin', '0', '否');
INSERT INTO `sys_role` VALUES ('29', 'aaaaaaaaa', 'aaaaaaaaaaa', '2018-12-18 05:36:25', '1', '2018-12-18 05:36:25', '1', '1', 'admin', 'admin', '0', '否');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `mid` int(6) DEFAULT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `createuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1454 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '19', '19', '2018-12-04 10:52:45', '');
INSERT INTO `sys_role_menu` VALUES ('2', '19', '20', '2018-12-04 10:52:45', '');
INSERT INTO `sys_role_menu` VALUES ('3', '19', '21', '2018-12-04 10:52:45', '');
INSERT INTO `sys_role_menu` VALUES ('4', '19', '22', '2018-12-04 10:52:45', '');
INSERT INTO `sys_role_menu` VALUES ('5', '19', '55', '2018-12-04 10:52:45', '');
INSERT INTO `sys_role_menu` VALUES ('6', '18', '2', '2018-12-05 19:33:31', '');
INSERT INTO `sys_role_menu` VALUES ('7', '23', '4', '2018-12-06 09:09:58', '');
INSERT INTO `sys_role_menu` VALUES ('8', '23', '7', '2018-12-06 09:09:58', '');
INSERT INTO `sys_role_menu` VALUES ('9', '22', '30', '2018-12-06 10:22:57', '');
INSERT INTO `sys_role_menu` VALUES ('14', '22', '73', '2018-12-06 10:22:57', '');
INSERT INTO `sys_role_menu` VALUES ('15', '20', '29', '2018-12-06 11:17:33', '');
INSERT INTO `sys_role_menu` VALUES ('20', '21', '29', '2018-12-06 12:01:48', '');
INSERT INTO `sys_role_menu` VALUES ('21', '21', '30', '2018-12-06 12:01:48', '');
INSERT INTO `sys_role_menu` VALUES ('25', '17', '29', '2018-12-06 12:02:45', '');
INSERT INTO `sys_role_menu` VALUES ('26', '17', '30', '2018-12-06 12:02:45', '');
INSERT INTO `sys_role_menu` VALUES ('28', '26', '1', '2018-12-13 14:15:15', '');
INSERT INTO `sys_role_menu` VALUES ('29', '26', '2', '2018-12-13 14:15:15', '');
INSERT INTO `sys_role_menu` VALUES ('30', '26', '3', '2018-12-13 14:15:15', '');
INSERT INTO `sys_role_menu` VALUES ('31', '26', '4', '2018-12-13 14:15:15', '');
INSERT INTO `sys_role_menu` VALUES ('32', '28', '3', '2018-12-19 14:19:51', '');
INSERT INTO `sys_role_menu` VALUES ('33', '28', '4', '2018-12-19 14:19:51', '');
INSERT INTO `sys_role_menu` VALUES ('34', '28', '5', '2018-12-19 14:19:51', '');
INSERT INTO `sys_role_menu` VALUES ('35', '28', '6', '2018-12-19 14:19:51', '');
INSERT INTO `sys_role_menu` VALUES ('36', '28', '8', '2018-12-19 14:19:52', '');
INSERT INTO `sys_role_menu` VALUES ('37', '28', '9', '2018-12-19 14:19:52', '');
INSERT INTO `sys_role_menu` VALUES ('38', '28', '10', '2018-12-19 14:19:52', '');
INSERT INTO `sys_role_menu` VALUES ('39', '28', '57', '2018-12-19 14:19:52', '');
INSERT INTO `sys_role_menu` VALUES ('40', '28', '12', '2018-12-19 14:19:52', '');
INSERT INTO `sys_role_menu` VALUES ('41', '28', '13', '2018-12-19 14:19:52', '');
INSERT INTO `sys_role_menu` VALUES ('42', '28', '14', '2018-12-19 14:19:52', '');
INSERT INTO `sys_role_menu` VALUES ('43', '28', '40', '2018-12-19 14:19:52', '');
INSERT INTO `sys_role_menu` VALUES ('1370', '2', '1', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1371', '2', '2', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1372', '2', '3', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1373', '2', '4', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1374', '2', '5', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1375', '2', '6', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1376', '2', '7', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1377', '2', '8', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1378', '2', '9', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1379', '2', '10', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1380', '2', '57', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1381', '2', '11', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1382', '2', '12', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1383', '2', '13', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1384', '2', '14', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1385', '2', '40', '2019-10-29 10:14:11', '1');
INSERT INTO `sys_role_menu` VALUES ('1386', '2', '122', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1387', '2', '123', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1388', '2', '124', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1389', '2', '125', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1390', '2', '146', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1391', '2', '147', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1392', '2', '157', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1393', '2', '158', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1394', '2', '148', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1395', '2', '149', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1396', '2', '150', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1397', '2', '151', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1398', '2', '155', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1399', '2', '156', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1400', '2', '152', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1401', '2', '159', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1402', '2', '160', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1403', '2', '161', '2019-10-29 10:14:12', '1');
INSERT INTO `sys_role_menu` VALUES ('1404', '2', '153', '2019-10-29 10:14:13', '1');
INSERT INTO `sys_role_menu` VALUES ('1405', '2', '162', '2019-10-29 10:14:13', '1');
INSERT INTO `sys_role_menu` VALUES ('1406', '2', '163', '2019-10-29 10:14:13', '1');
INSERT INTO `sys_role_menu` VALUES ('1407', '2', '164', '2019-10-29 10:14:13', '1');
INSERT INTO `sys_role_menu` VALUES ('1408', '2', '154', '2019-10-29 10:14:13', '1');
INSERT INTO `sys_role_menu` VALUES ('1409', '2', '165', '2019-10-29 10:14:13', '1');
INSERT INTO `sys_role_menu` VALUES ('1410', '2', '166', '2019-10-29 10:14:13', '1');
INSERT INTO `sys_role_menu` VALUES ('1411', '2', '167', '2019-10-29 10:14:13', '1');
INSERT INTO `sys_role_menu` VALUES ('1412', '1', '1', '2019-10-29 10:14:28', '1');
INSERT INTO `sys_role_menu` VALUES ('1413', '1', '2', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1414', '1', '3', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1415', '1', '4', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1416', '1', '5', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1417', '1', '6', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1418', '1', '7', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1419', '1', '8', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1420', '1', '9', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1421', '1', '10', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1422', '1', '57', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1423', '1', '11', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1424', '1', '12', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1425', '1', '13', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1426', '1', '14', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1427', '1', '40', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1428', '1', '122', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1429', '1', '123', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1430', '1', '124', '2019-10-29 10:14:29', '1');
INSERT INTO `sys_role_menu` VALUES ('1431', '1', '125', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1432', '1', '146', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1433', '1', '147', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1434', '1', '157', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1435', '1', '158', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1436', '1', '148', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1437', '1', '149', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1438', '1', '150', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1439', '1', '151', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1440', '1', '155', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1441', '1', '156', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1442', '1', '152', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1443', '1', '159', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1444', '1', '160', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1445', '1', '161', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1446', '1', '153', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1447', '1', '162', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1448', '1', '163', '2019-10-29 10:14:30', '1');
INSERT INTO `sys_role_menu` VALUES ('1449', '1', '164', '2019-10-29 10:14:31', '1');
INSERT INTO `sys_role_menu` VALUES ('1450', '1', '154', '2019-10-29 10:14:31', '1');
INSERT INTO `sys_role_menu` VALUES ('1451', '1', '165', '2019-10-29 10:14:31', '1');
INSERT INTO `sys_role_menu` VALUES ('1452', '1', '166', '2019-10-29 10:14:31', '1');
INSERT INTO `sys_role_menu` VALUES ('1453', '1', '167', '2019-10-29 10:14:31', '1');

-- ----------------------------
-- Table structure for sys_score
-- ----------------------------
DROP TABLE IF EXISTS `sys_score`;
CREATE TABLE `sys_score` (
  `score_id` int(10) NOT NULL,
  `chinese` int(10) DEFAULT NULL,
  `math` int(10) DEFAULT NULL,
  `english` int(10) DEFAULT NULL,
  `history` int(10) DEFAULT NULL,
  PRIMARY KEY (`score_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_score
-- ----------------------------
INSERT INTO `sys_score` VALUES ('1', '50', '80', '90', '40');
INSERT INTO `sys_score` VALUES ('2', '60', '80', '60', '47');
INSERT INTO `sys_score` VALUES ('3', '80', '40', '80', '88');
INSERT INTO `sys_score` VALUES ('4', '80', '68', '45', '82');
INSERT INTO `sys_score` VALUES ('5', '85', '75', '54', '54');
INSERT INTO `sys_score` VALUES ('6', '85', '84', '98', '99');
INSERT INTO `sys_score` VALUES ('7', '88', '95', '98', '54');
INSERT INTO `sys_score` VALUES ('8', '75', '95', '72', '88');
INSERT INTO `sys_score` VALUES ('9', '76', '85', '85', '44');
INSERT INTO `sys_score` VALUES ('10', '89', '75', '45', '74');

-- ----------------------------
-- Table structure for sys_statistics
-- ----------------------------
DROP TABLE IF EXISTS `sys_statistics`;
CREATE TABLE `sys_statistics` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '主表ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '主表名称',
  `parent_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_statistics
-- ----------------------------
INSERT INTO `sys_statistics` VALUES ('100', '报表统计', null, null);
INSERT INTO `sys_statistics` VALUES ('200', '当日收退款统计报表', null, null);

-- ----------------------------
-- Table structure for sys_statistics_column
-- ----------------------------
DROP TABLE IF EXISTS `sys_statistics_column`;
CREATE TABLE `sys_statistics_column` (
  `column_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `statistics_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `column_sql` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `column_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `property` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `ifsql` int(10) DEFAULT NULL,
  `header_id` int(10) DEFAULT NULL,
  `tosort` int(10) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_statistics_column
-- ----------------------------
INSERT INTO `sys_statistics_column` VALUES ('105', '100', 'select DISTINCT dic.name as groupName from t_pms_bill_detail det LEFT JOIN sys_dict dic on det.bill_paytype = dic.code where  det.bill_paytype =  #{groupId}', '支付方式', 'group', '1', '110', '1');
INSERT INTO `sys_statistics_column` VALUES ('108', '100', '($rental + $deposit + $other) - ($conmoney)', '退款金额', 'repmoney', '0', '140', '6');
INSERT INTO `sys_statistics_column` VALUES ('109', '100', 'select IFNULL(sum(det.bill_con_money) ,0) as bill_pay_money from t_pms_bill_detail det where  date_format(det.c_time,\'%Y-%m-%d\') between #{startTime} and #{endTime} and det.bill_paytype = #{groupId} group by det.bill_paytype', '实收金额', 'conmoney', '1', '140', '7');
INSERT INTO `sys_statistics_column` VALUES ('111', '100', 'select IFNULL(sum(det.rental) ,0) as rental from t_pms_bill_detail det where det.bill_type = \'0\' and  date_format(det.c_time,\'%Y-%m-%d\') between #{startTime} and #{endTime} and det.bill_paytype = #{groupId} group by det.bill_paytype', '房费', 'rental', '1', '120', '2');
INSERT INTO `sys_statistics_column` VALUES ('112', '100', 'select IFNULL(sum(det.deposit) ,0) as deposit from t_pms_bill_detail det where det.bill_type = \'0\' and  date_format(det.c_time,\'%Y-%m-%d\') between #{startTime} and #{endTime} and det.bill_paytype = #{groupId} group by det.bill_paytype', '押金', 'deposit', '1', '120', '3');
INSERT INTO `sys_statistics_column` VALUES ('113', '100', 'select IFNULL(sum(det.other) ,0) as other from t_pms_bill_detail det where det.bill_type = \'0\' and  date_format(det.c_time,\'%Y-%m-%d\') between #{startTime} and #{endTime} and det.bill_paytype = #{groupId} group by det.bill_paytype', '其他', 'other', '1', '120', '4');
INSERT INTO `sys_statistics_column` VALUES ('114', '100', '($rental + $deposit + $other)', '消费合计', 'count', '0', '120', '5');
INSERT INTO `sys_statistics_column` VALUES ('115', '200', ' select DISTINCT dic.name as groupName from t_pms_bill_detail det LEFT JOIN sys_dict dic on det.pay_paytype = dic.code where  det.pay_paytype =  #{groupId}', '', 'group', '1', '140', '1');
INSERT INTO `sys_statistics_column` VALUES ('116', '200', 'SELECT\n sum(d.bill_con_money) AS rental  \nFROM\n t_pms_bill_detail d   where date_format(d.c_time,\'%Y-%m-%d %H:%i:%S\') between #{startTime} and #{endTime} and d.hi_id in (#{hi_ids}) and d.bill_pid = \'1\' AND d.pay_paytype = #{groupId}', '房费', 'rental', '1', '170', '2');
INSERT INTO `sys_statistics_column` VALUES ('117', '200', 'SELECT\n sum(d.bill_con_money) AS deposit\n FROM\n t_pms_bill_detail d  where d.hi_id in  (#{hi_ids}) and date_format(d.c_time,\'%Y-%m-%d %H:%i:%S\') between #{startTime} and #{endTime} and d.bill_pid = \'2\'  and d.pay_paytype = #{groupId}', '押金', 'deposit', '1', '170', '3');
INSERT INTO `sys_statistics_column` VALUES ('118', '200', 'SELECT\n sum(d.bill_con_money) AS other \nFROM\n t_pms_bill_detail d  where d.hi_id in (#{hi_ids}) and date_format(d.c_time,\'%Y-%m-%d %H:%i:%S\') between #{startTime} and #{endTime} and d.bill_pid = \'3\' AND d.pay_paytype = #{groupId}', '其他消费', 'other', '1', '170', '4');
INSERT INTO `sys_statistics_column` VALUES ('119', '200', '($rental + $deposit + $other)', '总计', 'count', '0', '170', '5');
INSERT INTO `sys_statistics_column` VALUES ('220', '200', 'SELECT\n ( sum( d.bill_con_money ) - sum( d.bill_pay_money ) ) AS refound \nFROM\n t_pms_bill_detail d \nWHERE d.hi_id in (#{hi_ids}) and date_format(d.c_time,\'%Y-%m-%d %H:%i:%S\') between #{startTime} and #{endTime} and\n d.pay_paytype = #{groupId} GROUP BY\n d.pay_paytype', '', 'refound', '1', '190', '6');

-- ----------------------------
-- Table structure for sys_statistics_header
-- ----------------------------
DROP TABLE IF EXISTS `sys_statistics_header`;
CREATE TABLE `sys_statistics_header` (
  `id` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `label` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `prop` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `width` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `top_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_statistics_header
-- ----------------------------
INSERT INTO `sys_statistics_header` VALUES ('110', '', 'districtName', null, '100', null);
INSERT INTO `sys_statistics_header` VALUES ('120', '消费', 'timeDimension', null, '100', null);
INSERT INTO `sys_statistics_header` VALUES ('140', '结算', null, null, '100', null);
INSERT INTO `sys_statistics_header` VALUES ('150', '统计时间', 'counttime', null, '200', '1');
INSERT INTO `sys_statistics_header` VALUES ('160', '支付方式', 'group', null, '200', '2');
INSERT INTO `sys_statistics_header` VALUES ('170', '收款总额', null, null, '200', '3');
INSERT INTO `sys_statistics_header` VALUES ('180', '退款总额', 'refound', null, '200', '4');

-- ----------------------------
-- Table structure for sys_statistics_query
-- ----------------------------
DROP TABLE IF EXISTS `sys_statistics_query`;
CREATE TABLE `sys_statistics_query` (
  `field_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `statistics_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `field_sql` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `field_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `order_count` int(10) DEFAULT NULL,
  PRIMARY KEY (`field_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_statistics_query
-- ----------------------------

-- ----------------------------
-- Table structure for sys_student
-- ----------------------------
DROP TABLE IF EXISTS `sys_student`;
CREATE TABLE `sys_student` (
  `student_id` int(20) NOT NULL,
  `student_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `student_class` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `score_id` int(20) DEFAULT NULL,
  `order_count` int(10) DEFAULT NULL,
  `grade_no` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_student
-- ----------------------------
INSERT INTO `sys_student` VALUES ('1', '张珊', '二年级', '1', '2', 'no2', '2018-12-28');
INSERT INTO `sys_student` VALUES ('2', '丽江', '二年级', '2', '2', 'no2', '2018-12-30');
INSERT INTO `sys_student` VALUES ('3', '黄军', '三年级', '3', '3', 'no3', '2018-12-28');
INSERT INTO `sys_student` VALUES ('4', '张家辉', '一年级', '4', '1', 'no1', '2018-12-30');
INSERT INTO `sys_student` VALUES ('5', '刘德华', '四年级', '5', '4', 'no4', '2018-12-28');
INSERT INTO `sys_student` VALUES ('6', '周星驰', '四年级', '6', '4', 'no4', '2018-12-29');
INSERT INTO `sys_student` VALUES ('7', '刘嘉玲', '三年级', '7', '3', 'no3', '2018-12-29');
INSERT INTO `sys_student` VALUES ('8', '梁家辉', '二年级', '8', '2', 'no2', '2018-12-24');
INSERT INTO `sys_student` VALUES ('9', '张韶涵', '三年级', '9', '3', 'no3', '2018-12-29');
INSERT INTO `sys_student` VALUES ('10', '张杰', '一年级', '10', '1', 'no1', '2018-12-29');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `username` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `truename` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `enabled` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `createuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `modifyuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `deltag` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0',
  `gender_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `enabled_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `hiId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `hiId_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createuser_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `modifyuser_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `issystem` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `issystem_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `blocId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `blocId_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('09899dcf52cb41459cff03673249ec1f', 'yst', '44fea1e1efce0eca5a531a8540bfb8d6', '雅斯特', '18888888888', '1', '1', '2019-06-13 10:02:53', '1', '2019-07-11 14:53:24', '1', '0', '男', '是', '450103014', '雅斯特国际酒店南宁发展大厦店', 'admin', 'admin', '0', '否', '0086450101000030', '雅斯特国际酒店管理集团', '雅斯特', '2019-07-30 19:36:15');
INSERT INTO `sys_user` VALUES ('1', 'admin', '8f54ebf2adca51cb989528f7147d0b58', '超级管理员', '15085912630', '1', '1', '2018-11-16 15:40:18', '1', '2019-11-04 10:40:51', '1', '0', '男', '否', '520101048', '金帝大酒店', 'admin', 'admin', '1', '是', '0086520101000043', '汉唐佳华酒店集团', '超级管理员,酒店前台,酒店管理员,酒店财务', '2019-11-14 14:23:56');
INSERT INTO `sys_user` VALUES ('100000060142917266', 'nbkadmin', 'c2d29373ef0474e89b9e343b5454be95', '纽宾凯管理员', '11111111111', '1', '1', '2019-09-24 14:29:17', '1', null, '1', '0', '男', '是', '100000060', '武汉纽宾凯楚玉酒店', 'admin', 'admin', '1', '是', '0086100000000056', '纽宾凯集团', '酒店管理员', '2019-09-24 14:29:45');
INSERT INTO `sys_user` VALUES ('100000060143012048', 'nbkqt', 'c2d29373ef0474e89b9e343b5454be95', '纽宾凯前台', '11111111111', '1', '1', '2019-09-24 14:30:12', '100000060142917266', null, '100000060142917266', '0', '男', '是', '100000060', '武汉纽宾凯楚玉酒店', 'nbkadmin', 'nbkadmin', null, null, '0086100000000056', '纽宾凯集团', '酒店前台', '2019-09-28 14:31:27');
INSERT INTO `sys_user` VALUES ('100000060143036205', 'nbkcw', 'c2d29373ef0474e89b9e343b5454be95', '纽宾凯财务', '11111111111', '1', '1', '2019-09-24 14:30:36', '100000060142917266', null, '100000060142917266', '0', '男', '是', '100000060', '武汉纽宾凯楚玉酒店', 'nbkadmin', 'nbkadmin', null, null, '0086100000000056', '纽宾凯集团', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('100000061143920350', 'ldbpadmin', 'c2d29373ef0474e89b9e343b5454be95', '绿地管理员', '11111111111', '1', '1', '2019-09-24 14:39:20', '1', null, '1', '0', '男', '是', '100000061', '武汉绿地铂派酒店', 'admin', 'admin', '1', '是', '0086100000000057', '绿地集团', '酒店管理员', '2019-09-24 14:39:39');
INSERT INTO `sys_user` VALUES ('100000061144028423', 'ldbpqt', 'c2d29373ef0474e89b9e343b5454be95', '绿地铂派管理员', '11111111111', '1', '1', '2019-09-24 14:40:28', '100000061143920350', null, '100000061143920350', '0', '男', '是', '100000061', '武汉绿地铂派酒店', 'ldbpadmin', 'ldbpadmin', null, null, '0086100000000057', '绿地集团', '酒店前台', null);
INSERT INTO `sys_user` VALUES ('100000061144057110', 'ldbpcw', 'c2d29373ef0474e89b9e343b5454be95', '绿地铂派财务', '11111111111', '1', '1', '2019-09-24 14:40:57', '100000061143920350', null, '100000061143920350', '0', '男', '是', '100000061', '武汉绿地铂派酒店', 'ldbpadmin', 'ldbpadmin', null, null, '0086100000000057', '绿地集团', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('100000063144616049', 'jkfxadmin', 'c2d29373ef0474e89b9e343b5454be95', '江口凤栖管理员', '11111111111', '1', '1', '2019-09-24 14:46:16', '1', null, '1', '0', '男', '是', '100000063', '铜仁江口凤栖酒店', 'admin', 'admin', '1', '是', '0086100000000059', '江口凤栖集团', '酒店管理员', '2019-09-30 14:13:29');
INSERT INTO `sys_user` VALUES ('100000063144709656', 'jkfxqt', 'c2d29373ef0474e89b9e343b5454be95', '江口凤栖前台', '11111111111', '1', '1', '2019-09-24 14:47:10', '100000063144616049', null, '100000063144616049', '0', '男', '是', '100000063', '铜仁江口凤栖酒店', 'jkfxadmin', 'jkfxadmin', null, null, '0086100000000059', '江口凤栖集团', '酒店前台', null);
INSERT INTO `sys_user` VALUES ('100000063144736895', 'jkfqcw', 'c2d29373ef0474e89b9e343b5454be95', '江口凤栖财务', '11111111111', '1', '1', '2019-09-24 14:47:37', '100000063144616049', null, '100000063144616049', '0', '男', '是', '100000063', '铜仁江口凤栖酒店', 'jkfxadmin', 'jkfxadmin', null, null, '0086100000000059', '江口凤栖集团', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('100000064143447737', 'oyhzadmin', 'c2d29373ef0474e89b9e343b5454be95', '欧亚会展管理员', '11111111111', '1', '1', '2019-09-24 14:34:48', '1', null, '1', '0', '男', '是', '100000064', '武汉欧亚会展酒店', 'admin', 'admin', '1', '是', '0086100000000060', '武汉欧亚会展集团', '酒店管理员', '2019-09-24 14:35:07');
INSERT INTO `sys_user` VALUES ('100000064143530560', 'oyhzqt', 'c2d29373ef0474e89b9e343b5454be95', '欧亚会展前台', '11111111111', '1', '1', '2019-09-24 14:35:31', '100000064143447737', null, '100000064143447737', '0', '男', '是', '100000064', '武汉欧亚会展酒店', 'oyhzadmin', 'oyhzadmin', null, null, '0086100000000060', '武汉欧亚会展集团', '酒店前台', null);
INSERT INTO `sys_user` VALUES ('100000064143609336', 'oyhzcw', 'c2d29373ef0474e89b9e343b5454be95', '欧亚会展财务', '11111111111', '1', '1', '2019-09-24 14:36:09', '100000064143447737', null, '100000064143447737', '0', '男', '是', '100000064', '武汉欧亚会展酒店', 'oyhzadmin', 'oyhzadmin', null, null, '0086100000000060', '武汉欧亚会展集团', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('100000072134920834', 'altladmin', 'c2d29373ef0474e89b9e343b5454be95', '阿尔泰勒管理员', '11111111111', '1', '1', '2019-09-30 13:49:21', '1', null, '1', '0', '男', '是', '100000072', '阿勒泰地区旅游宾馆', 'admin', 'admin', '1', '是', '0086100000000063', '阿勒泰地区旅投集团', '酒店管理员', '2019-09-30 14:12:46');
INSERT INTO `sys_user` VALUES ('100000072135158725', 'altlqt', 'c2d29373ef0474e89b9e343b5454be95', '阿尔泰勒前台', '11111111111', '1', '1', '2019-09-30 13:51:59', '100000072134920834', null, '100000072134920834', '0', '男', '是', '100000072', '阿勒泰地区旅游宾馆', 'altladmin', 'altladmin', null, null, '0086100000000063', '阿勒泰地区旅投集团', '酒店前台', null);
INSERT INTO `sys_user` VALUES ('100000072135242497', 'altlcw', 'c2d29373ef0474e89b9e343b5454be95', '阿勒泰勒财务', '11111111111', '1', '1', '2019-09-30 13:52:42', '100000072134920834', '2019-09-30 13:53:09', '100000072134920834', '0', '男', '是', '100000072', '阿勒泰地区旅游宾馆', 'altladmin', 'altladmin', null, null, '0086100000000063', '阿勒泰地区旅投集团', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('440101044141923458', 'yxadmin', '04918037e2015462c559cc31df0d936f', '有戏管理员', '18585411111', '1', '1', '2019-09-05 14:19:23', '1', null, '1', '0', '男', '是', '440101044', '广州有戏电影酒店', 'admin', 'admin', '1', '是', '0086100000000044', '北京乐创酒店管理有限公司', '酒店管理员', '2019-10-20 04:17:05');
INSERT INTO `sys_user` VALUES ('440101044142106642', 'yxqt', 'c2d29373ef0474e89b9e343b5454be95', '有戏前台', '11111111111', '1', '1', '2019-09-05 14:21:07', '440101044141923458', null, '440101044141923458', '0', '男', '是', '440101044', '广州有戏电影酒店', 'yxadmin', 'yxadmin', null, null, '0086100000000044', '北京乐创酒店管理有限公司', '酒店前台', '2019-10-16 23:57:07');
INSERT INTO `sys_user` VALUES ('440101044142135616', 'yxcw', 'c2d29373ef0474e89b9e343b5454be95', '有戏财务', '11111111111', '2', '1', '2019-09-05 14:21:36', '440101044141923458', null, '440101044141923458', '0', '女', '是', '440101044', '广州有戏电影酒店', 'yxadmin', 'yxadmin', null, null, '0086100000000044', '北京乐创酒店管理有限公司', '酒店财务', '2019-10-03 00:07:27');
INSERT INTO `sys_user` VALUES ('450100074094448735', 'shengzhan', '860bcab0230134b3b79dbb3179f19578', '圣展酒店', '16643536627', '1', '1', '2019-10-18 09:44:49', '520101043115026097', null, '520101043115026097', '0', '男', '是', '450100074', '圣展酒店', 'yangmeng', 'yangmeng', '0', '否', '0086100000000065', '广西圣展集团', '酒店管理员', '2019-10-18 09:58:44');
INSERT INTO `sys_user` VALUES ('450103014145811968', 'yst2', '10bdec15f7d52d366a5f3ea18101f9bf', '雅斯特2', '15202458741', '1', '1', '2019-07-11 14:58:12', '1', null, '1', '0', '男', '是', '450103014', '雅斯特国际酒店南宁发展大厦店', 'admin', 'admin', '1', '是', '0086450101000030', '雅斯特国际酒店管理集团', '雅斯特', '2019-08-25 15:45:35');
INSERT INTO `sys_user` VALUES ('520000066134615506', 'gladmin', 'c2d29373ef0474e89b9e343b5454be95', '港龙管理员', '11111111111', '1', '1', '2019-09-29 13:46:16', '1', null, '1', '0', '男', '是', '520000066', '都匀港龙大酒店', 'admin', 'admin', '1', '是', '0086520000000061', '都匀通利万利酒店管理有限公司', '酒店管理员', '2019-10-15 16:33:32');
INSERT INTO `sys_user` VALUES ('520000066134706386', 'glqt', 'c2d29373ef0474e89b9e343b5454be95', '港龙前台', '11111111111', '1', '1', '2019-09-29 13:47:06', '520000066134615506', null, '520000066134615506', '0', '男', '是', '520000066', '都匀港龙大酒店', 'gladmin', 'gladmin', null, null, '0086520000000061', '都匀通利万利酒店管理有限公司', '酒店前台', '2019-10-16 10:33:27');
INSERT INTO `sys_user` VALUES ('520000066134733940', 'glcw', 'c2d29373ef0474e89b9e343b5454be95', '港龙财务', '11111111111', '1', '1', '2019-09-29 13:47:34', '520000066134615506', null, '520000066134615506', '0', '男', '是', '520000066', '都匀港龙大酒店', 'gladmin', 'gladmin', null, null, '0086520000000061', '都匀通利万利酒店管理有限公司', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('520101042142354929', 'sfzhjdadmin', 'bbaab139c175b275c753554d0f584c83', '索菲管理员', '18585411281', '2', '1', '2019-09-04 14:23:55', '1', null, '1', '0', '女', '是', '520101042', '索菲智慧酒店', 'admin', 'admin', '1', '是', '0086520101000042', '贵州索菲智慧酒店管理有限公司', '酒店管理员', '2019-10-16 13:32:21');
INSERT INTO `sys_user` VALUES ('520101042142509889', 'sfzhjdqt', 'c7ff7e0fed0dd1f26ed9c10172ce8c2b', '索菲酒店前台', '18585458122', '2', '1', '2019-09-04 14:25:10', '520101042142354929', null, '520101042142354929', '0', '女', '是', '520101042', '索菲智慧酒店', 'sfzhjdadmin', 'sfzhjdadmin', null, null, '0086520101000042', '贵州索菲智慧酒店管理有限公司', '酒店前台', '2019-09-05 23:17:26');
INSERT INTO `sys_user` VALUES ('520101042142606671', 'sfzhjdcw', '04918037e2015462c559cc31df0d936f', '索菲智慧酒店财务', '18585411111', '2', '1', '2019-09-04 14:26:07', '520101042142354929', null, '520101042142354929', '0', '女', '是', '520101042', '索菲智慧酒店', 'sfzhjdadmin', 'sfzhjdadmin', null, null, '0086520101000042', '贵州索菲智慧酒店管理有限公司', '酒店财务', '2019-10-18 17:23:55');
INSERT INTO `sys_user` VALUES ('520101043115026097', 'yangmeng', '6f2e89e368cdb67f78e160f009163ccb', 'yangmeng', '18708684784', '1', '1', '2019-09-05 11:50:26', '1', null, '1', '0', '男', '是', '520101043', '汉唐佳华金融城店', 'admin', 'admin', '1', '是', '0086520101000043', '汉唐佳华酒店集团', '超级管理员,系统管理员,酒店前台,酒店管理员,酒店财务', '2019-11-02 17:31:28');
INSERT INTO `sys_user` VALUES ('520101043135306185', 'jrcadmin', '61223b3e165a0ec6b58f0f28da8aa3be', '汉唐佳华（金融城店）', '13597953566', '2', '1', '2019-09-02 13:53:06', '1', null, '1', '0', '女', '是', '520101043', '汉唐佳华金融城店', 'admin', 'admin', '0', '否', '0086520101000043', '汉唐佳华酒店集团', '酒店管理员', '2019-09-30 16:25:25');
INSERT INTO `sys_user` VALUES ('520101043135422021', 'jrcqt', 'bbaab139c175b275c753554d0f584c83', 'lj', '18585411281', '2', '1', '2019-09-02 13:54:22', '520101043135306185', '2019-09-02 13:54:47', '520101043135306185', '0', '女', '是', '520101043', '汉唐佳华金融城店', 'jrcadmin', 'jrcadmin', null, null, '0086520101000043', '汉唐佳华酒店集团', '酒店前台', '2019-09-27 09:23:34');
INSERT INTO `sys_user` VALUES ('520101043150002060', 'htcadmin', 'c2d29373ef0474e89b9e343b5454be95', '汉唐巢管理员', '11111111111', '2', '1', '2019-09-10 15:00:02', '520101043115026097', null, '520101043115026097', '1', '女', '是', '520101043', '汉唐佳华金融城店', 'yangmeng', 'yangmeng', null, null, '0086520101000043', '汉唐佳华酒店集团', '酒店管理员', '2019-09-10 15:02:57');
INSERT INTO `sys_user` VALUES ('520101043150058452', 'htcqt', 'c2d29373ef0474e89b9e343b5454be95', '汉唐巢前台', '11111111111', '2', '1', '2019-09-10 15:00:58', '520101043150002060', null, '520101043150002060', '1', '女', '是', '520101043', '汉唐佳华金融城店', 'htcadmin', 'htcadmin', null, null, '0086520101000043', '汉唐佳华酒店集团', '酒店前台', null);
INSERT INTO `sys_user` VALUES ('520101046143145043', 'lcjradmin', 'c2d29373ef0474e89b9e343b5454be95', '林城假日管理员', '11111111111', '1', '1', '2019-09-05 14:31:45', '1', null, '1', '0', '男', '是', '520101046', '林城假日酒店', 'admin', 'admin', '1', '是', '0086520101000045', '林城假日', '酒店管理员', '2019-10-20 08:58:59');
INSERT INTO `sys_user` VALUES ('520101046143540160', 'lcjrqt', 'c2d29373ef0474e89b9e343b5454be95', '林城假日前台', '11111111111', '1', '1', '2019-09-05 14:35:40', '520101046143145043', '2019-09-25 12:34:00', '520101043115026097', '0', '男', '是', '520101046', '林城假日酒店', 'lcjradmin', 'yangmeng', '0', '否', '0086520101000045', '林城假日', '酒店前台', '2019-10-18 06:09:34');
INSERT INTO `sys_user` VALUES ('520101046143620513', 'lcjrcw', 'c2d29373ef0474e89b9e343b5454be95', '林城假日财务', '11111111111', '2', '1', '2019-09-05 14:36:21', '520101046143145043', '2019-09-05 14:38:04', '520101046143145043', '0', '女', '是', '520101046', '林城假日酒店', 'lcjradmin', 'lcjradmin', null, null, '0086520101000045', '林城假日', '酒店财务', '2019-10-18 15:09:16');
INSERT INTO `sys_user` VALUES ('520101046143733691', 'kcjrcw', 'c2d29373ef0474e89b9e343b5454be95', '林城假日财务', '11111111111', '1', '1', '2019-09-05 14:37:34', '520101046143145043', null, '520101046143145043', '1', '男', '是', '520101046', '林城假日酒店', 'lcjradmin', 'lcjradmin', null, null, '0086520101000045', '林城假日', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('520101048144129044', 'jdadmin', 'c2d29373ef0474e89b9e343b5454be95', '金帝管理员', '11111111111', '1', '1', '2019-09-05 14:41:29', '1', '2019-09-25 12:34:06', '520101043115026097', '0', '男', '是', '520101048', '金帝大酒店', 'admin', 'yangmeng', '1', '是', '0086520101000043', '汉唐佳华酒店集团', '酒店管理员', '2019-10-21 15:32:58');
INSERT INTO `sys_user` VALUES ('520101048144626685', 'jdqt', 'c2d29373ef0474e89b9e343b5454be95', '金帝前台', '11111111111', '1', '1', '2019-09-05 14:46:27', '520101048144129044', null, '520101048144129044', '0', '男', '是', '520101048', '金帝大酒店', 'jdadmin', 'jdadmin', null, null, '0086520101000043', '汉唐佳华酒店集团', '酒店前台', '2019-10-18 19:32:47');
INSERT INTO `sys_user` VALUES ('520101048144653461', 'jdcw', 'c2d29373ef0474e89b9e343b5454be95', '金帝财务', '11111111111', '1', '1', '2019-09-05 14:46:53', '520101048144129044', null, '520101048144129044', '0', '男', '是', '520101048', '金帝大酒店', 'jdadmin', 'jdadmin', null, null, '0086520101000043', '汉唐佳华酒店集团', '酒店财务', '2019-10-21 09:48:14');
INSERT INTO `sys_user` VALUES ('520101055150653338', 'htcadmin', 'c2d29373ef0474e89b9e343b5454be95', '汉唐巢管理员', '11111111111', '2', '1', '2019-09-10 15:06:53', '1', null, '1', '0', '女', '是', '520101055', '汉唐巢', 'admin', 'admin', '1', '是', '0086520101000043', '汉唐佳华酒店集团', '酒店管理员', '2019-09-25 17:14:07');
INSERT INTO `sys_user` VALUES ('520101055150837122', 'htcqt', 'c2d29373ef0474e89b9e343b5454be95', '汉唐巢前台', '11111111111', '1', '1', '2019-09-10 15:08:37', '520101055150653338', null, '520101055150653338', '0', '男', '是', '520101055', '汉唐巢', 'htcadmin', 'htcadmin', null, null, '0086520101000043', '汉唐佳华酒店集团', '酒店前台', null);
INSERT INTO `sys_user` VALUES ('520101055150908550', 'htccw', 'c2d29373ef0474e89b9e343b5454be95', '汉唐巢财务', '11111111111', '1', '1', '2019-09-10 15:09:09', '520101055150653338', null, '520101055150653338', '0', '男', '是', '520101055', '汉唐巢', 'htcadmin', 'htcadmin', null, null, '0086520101000043', '汉唐佳华酒店集团', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('520101059110945379', 'dzsfadmin', 'c2d29373ef0474e89b9e343b5454be95', '道真索菲管理员', '11111111111', '1', '1', '2019-09-24 11:09:45', '1', null, '1', '0', '男', '是', '520101059', '道真索菲大酒店', 'admin', 'admin', '1', '是', '0086000055', '索菲集团', '酒店管理员', '2019-09-24 11:12:03');
INSERT INTO `sys_user` VALUES ('520101059111250153', 'dzsfqt', 'c2d29373ef0474e89b9e343b5454be95', '道真前台', '11111111111', '1', '1', '2019-09-24 11:12:50', '520101059110945379', null, '520101059110945379', '0', '男', '是', '520101059', '道真索菲大酒店', 'dzsfadmin', 'dzsfadmin', null, null, '0086000055', '索菲集团', '酒店前台', null);
INSERT INTO `sys_user` VALUES ('520101059111327871', 'dzsfcw', 'c2d29373ef0474e89b9e343b5454be95', '道真索菲财务', '11111111111', '1', '1', '2019-09-24 11:13:28', '520101059110945379', null, '520101059110945379', '0', '男', '是', '520101059', '道真索菲大酒店', 'dzsfadmin', 'dzsfadmin', null, null, '0086000055', '索菲集团', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('520101062140618939', 'ssadmin', 'c2d29373ef0474e89b9e343b5454be95', '上善管理员', '11111111111', '1', '1', '2019-09-24 14:06:19', '1', null, '1', '0', '男', '是', '520101062', '贵阳上善酒店', 'admin', 'admin', '1', '是', '0086100000000058', '上善水文化集团', '酒店管理员', '2019-10-10 16:10:54');
INSERT INTO `sys_user` VALUES ('520101062161123848', 'ssqt', 'c2d29373ef0474e89b9e343b5454be95', '上善前台', '11111111111', '1', '1', '2019-10-10 16:11:24', '520101062140618939', null, '520101062140618939', '0', '男', '是', '520101062', '贵阳上善酒店', 'ssadmin', 'ssadmin', null, null, '0086100000000058', '上善水文化集团', '酒店前台', null);
INSERT INTO `sys_user` VALUES ('520101062161154191', 'sscw', 'c2d29373ef0474e89b9e343b5454be95', '上善财务', '11111111111', '1', '1', '2019-10-10 16:11:54', '520101062140618939', null, '520101062140618939', '0', '男', '是', '520101062', '贵阳上善酒店', 'ssadmin', 'ssadmin', null, null, '0086100000000058', '上善水文化集团', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('520101065180045885', 'hthzcadmin', 'c2d29373ef0474e89b9e343b5454be95', '汉唐佳华(会展城)', '11111111111', '1', '1', '2019-09-26 18:00:46', '1', null, '1', '0', '男', '是', '520101065', '贵州汉唐佳会展城分公司', 'admin', 'admin', '1', '是', '0086520103000041', '贵州汉唐佳华酒店管理有限公司', '酒店管理员', '2019-09-26 18:01:49');
INSERT INTO `sys_user` VALUES ('520101065180246228', 'hthzcqt', 'c2d29373ef0474e89b9e343b5454be95', '汉唐佳华前台', '11111111111', '1', '1', '2019-09-26 18:02:46', '520101065180045885', null, '520101065180045885', '0', '男', '是', '520101065', '贵州汉唐佳会展城分公司', 'hthzcadmin', 'hthzcadmin', null, null, '0086520103000041', '贵州汉唐佳华酒店管理有限公司', '酒店前台', null);
INSERT INTO `sys_user` VALUES ('520101065180325606', 'hthzccw', 'b0bc9fa113fa53c47fb0f64959815610', '汉唐佳华财务', '11111111111', '1', '1', '2019-09-26 18:03:26', '520101065180045885', null, '520101065180045885', '0', '男', '是', '520101065', '贵州汉唐佳会展城分公司', 'hthzcadmin', 'hthzcadmin', null, null, '0086520103000041', '贵州汉唐佳华酒店管理有限公司', '酒店财务', null);
INSERT INTO `sys_user` VALUES ('520101067140919195', 'dsyjadmin', 'c2d29373ef0474e89b9e343b5454be95', '都市怡景管理', '11111111111', '2', '1', '2019-09-28 14:09:19', '1', null, '1', '0', '女', '是', '520101067', '都市怡景酒店', 'admin', 'admin', '1', '是', '0086520101000062', '贵州都市怡景酒店', '酒店管理员', '2019-10-14 13:57:52');
INSERT INTO `sys_user` VALUES ('520101067141035177', 'dsyjqt', 'c2d29373ef0474e89b9e343b5454be95', '都市怡景前台', '11111111111', '1', '1', '2019-09-28 14:10:35', '520101067140919195', null, '520101067140919195', '0', '男', '是', '520101067', '都市怡景酒店', 'dsyjadmin', 'dsyjadmin', null, null, '0086520101000062', '贵州都市怡景酒店', '酒店前台', '2019-09-29 15:35:04');
INSERT INTO `sys_user` VALUES ('520101067141109846', 'dsyjcw', 'c2d29373ef0474e89b9e343b5454be95', '都市怡景财务', '11111111111', '1', '1', '2019-09-28 14:11:10', '520101067140919195', '2019-09-28 15:27:42', '520101043115026097', '0', '男', '是', '520101067', '都市怡景酒店', 'dsyjadmin', 'yangmeng', '0', '否', '0086520101000062', '贵州都市怡景酒店', '酒店财务', '2019-09-29 14:48:56');
INSERT INTO `sys_user` VALUES ('520101071144823658', 'hwadmin', 'c2d29373ef0474e89b9e343b5454be95', '豪威管理员', '11111111111', '1', '1', '2019-09-30 14:48:24', '1', null, '1', '0', '男', '是', '520101071', '豪威大酒店', 'admin', 'admin', '1', '是', '0086520101000043', '汉唐佳华酒店集团', '酒店管理员', '2019-10-14 15:56:01');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `createuser` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '81d3c40f54744d5bbc3861aad0063f14', '5', '2018-11-30 10:03:15', null);
INSERT INTO `sys_user_role` VALUES ('2', '81d3c40f54744d5bbc3861aad0063f14', '6', '2018-11-30 10:03:15', null);
INSERT INTO `sys_user_role` VALUES ('3', '1b75bce4418d4b35be885d397e9fc880', '5', '2018-12-13 14:20:47', null);
INSERT INTO `sys_user_role` VALUES ('4', 'a3d306f632784535a7a3c170b6b107cb', '1', '2018-12-21 19:32:35', null);
INSERT INTO `sys_user_role` VALUES ('5', 'a3d306f632784535a7a3c170b6b107cb', '2', '2018-12-21 19:32:35', null);
INSERT INTO `sys_user_role` VALUES ('6', 'a45c46fd52d14f4cb1b804791a4bae96', '1', '2018-12-21 19:37:08', null);
INSERT INTO `sys_user_role` VALUES ('7', '5d4ef1220f3f4d058323ec0882e008de', '1', '2019-01-03 09:36:39', null);
INSERT INTO `sys_user_role` VALUES ('8', '148ac3ddd5294f179d2a6649a28e9ab3', '1', '2019-01-03 15:11:30', null);
INSERT INTO `sys_user_role` VALUES ('9', '148ac3ddd5294f179d2a6649a28e9ab3', '2', '2019-01-03 15:11:30', null);
INSERT INTO `sys_user_role` VALUES ('10', '148ac3ddd5294f179d2a6649a28e9ab3', '5', '2019-01-03 15:11:30', null);
INSERT INTO `sys_user_role` VALUES ('11', 'c38776c4e55b41a5af48d5d2aa775c26', '30', '2019-01-17 09:22:20', null);
INSERT INTO `sys_user_role` VALUES ('12', 'b5e542c16f564aa989c3b95ac530d120', '30', '2019-01-19 10:43:17', null);
INSERT INTO `sys_user_role` VALUES ('13', 'f13a08684c5442e79ae3e1c4d3725d2f', '2', '2019-01-19 11:57:33', null);
INSERT INTO `sys_user_role` VALUES ('14', '4a6cc733b9b34eda8b5ad5531064ea21', '5', '2019-01-24 20:13:11', null);
INSERT INTO `sys_user_role` VALUES ('16', 'ff12e7d765744c9f8b35975b13e73211', '6', '2019-01-26 17:11:13', null);
INSERT INTO `sys_user_role` VALUES ('17', '7e79a1c2983d486e988f2d7a38c1170f', '5', '2019-01-28 15:08:19', null);
INSERT INTO `sys_user_role` VALUES ('18', '5028453a26ca403499dc11f1a4f480ea', '5', '2019-01-28 15:31:11', null);
INSERT INTO `sys_user_role` VALUES ('19', 'c04757f9e6b444f4b9cf20844ba8dc75', '6', '2019-01-28 15:41:25', null);
INSERT INTO `sys_user_role` VALUES ('20', 'a8e609f11c854fd49df8e3dbf78bd3cf', '6', '2019-01-28 15:48:06', null);
INSERT INTO `sys_user_role` VALUES ('21', 'b11a8e427fc641e2bbdb4aa814b8a6ec', '6', '2019-01-28 15:51:09', null);
INSERT INTO `sys_user_role` VALUES ('22', '313f93a8e8dd4a09a88a64b85fca2aad', '6', '2019-01-28 15:51:24', null);
INSERT INTO `sys_user_role` VALUES ('23', '313f93a8e8dd4a09a88a64b85fca2aad', '30', '2019-01-28 15:51:24', null);
INSERT INTO `sys_user_role` VALUES ('24', 'a48a0ef1d9f240bfbba666bde58d1b63', '6', '2019-01-28 15:54:39', null);
INSERT INTO `sys_user_role` VALUES ('25', 'd0224c9ec51d4683b2c6fb71eb630bb0', '1', '2019-01-28 16:11:21', null);
INSERT INTO `sys_user_role` VALUES ('26', '857b427dbf2d42ac95f952692bf5c5bd', '30', '2019-02-13 15:44:50', null);
INSERT INTO `sys_user_role` VALUES ('27', '03f77a13f4974309b46ffd4dea52db43', '2', '2019-03-01 14:59:08', null);
INSERT INTO `sys_user_role` VALUES ('28', '03f77a13f4974309b46ffd4dea52db43', '1', '2019-03-01 14:59:08', null);
INSERT INTO `sys_user_role` VALUES ('33', '1a3fb8784b3d44feaf8fab5c5c3af8dd', '30', '2019-03-15 20:10:57', null);
INSERT INTO `sys_user_role` VALUES ('34', '1a3fb8784b3d44feaf8fab5c5c3af8dd', '6', '2019-03-15 20:10:57', null);
INSERT INTO `sys_user_role` VALUES ('35', '1a3fb8784b3d44feaf8fab5c5c3af8dd', '5', '2019-03-15 20:10:57', null);
INSERT INTO `sys_user_role` VALUES ('36', '03aa8e526ee445e2a1a0932097872582', '6', '2019-03-16 09:53:47', null);
INSERT INTO `sys_user_role` VALUES ('37', '03aa8e526ee445e2a1a0932097872582', '5', '2019-03-16 09:53:47', null);
INSERT INTO `sys_user_role` VALUES ('38', '03aa8e526ee445e2a1a0932097872582', '30', '2019-03-16 09:53:47', null);
INSERT INTO `sys_user_role` VALUES ('39', 'fefd06b41b684b8f9fe9a22e37cfc1fe', '5', '2019-03-16 14:05:54', null);
INSERT INTO `sys_user_role` VALUES ('40', 'fefd06b41b684b8f9fe9a22e37cfc1fe', '6', '2019-03-16 14:05:54', null);
INSERT INTO `sys_user_role` VALUES ('41', 'fefd06b41b684b8f9fe9a22e37cfc1fe', '30', '2019-03-16 14:05:54', null);
INSERT INTO `sys_user_role` VALUES ('42', 'c8af3d8685dd4070a9a05c3af5e58257', '1', '2019-03-16 14:06:07', null);
INSERT INTO `sys_user_role` VALUES ('43', 'c8af3d8685dd4070a9a05c3af5e58257', '5', '2019-03-16 14:06:07', null);
INSERT INTO `sys_user_role` VALUES ('44', 'c8af3d8685dd4070a9a05c3af5e58257', '30', '2019-03-16 14:06:07', null);
INSERT INTO `sys_user_role` VALUES ('45', '050a3636ca07459db5b8a4c2258a3a1e', '30', '2019-03-16 21:54:18', null);
INSERT INTO `sys_user_role` VALUES ('46', '62188e06b9f44a658d9a5c3963a38574', '5', '2019-03-17 15:59:42', null);
INSERT INTO `sys_user_role` VALUES ('47', '766fd6eabe09425b96f543560a7d57b6', '5', '2019-03-17 16:04:34', null);
INSERT INTO `sys_user_role` VALUES ('48', 'd45176489bc847b18e3247fd368b11bc', '5', '2019-03-17 16:07:23', null);
INSERT INTO `sys_user_role` VALUES ('49', 'b5c0928f743243389bcecc5d51180f62', '1', '2019-04-17 14:41:10', null);
INSERT INTO `sys_user_role` VALUES ('51', '09899dcf52cb41459cff03673249ec1f', '32', '2019-06-13 10:14:41', null);
INSERT INTO `sys_user_role` VALUES ('52', 'c435fb14ad4a47b6b2827d1a7465f740', '32', '2019-06-17 17:05:57', null);
INSERT INTO `sys_user_role` VALUES ('53', '450103014145811968', '32', '2019-07-11 15:08:13', null);
INSERT INTO `sys_user_role` VALUES ('54', '520103041152428927', '6', '2019-08-27 15:31:54', null);
INSERT INTO `sys_user_role` VALUES ('57', '520103041152726450', '5', '2019-08-28 15:05:52', null);
INSERT INTO `sys_user_role` VALUES ('58', '520101043135306185', '6', '2019-09-02 14:00:47', null);
INSERT INTO `sys_user_role` VALUES ('59', '520101043135422021', '5', '2019-09-02 14:01:58', null);
INSERT INTO `sys_user_role` VALUES ('60', '520103041141654914', '30', '2019-09-02 14:24:31', null);
INSERT INTO `sys_user_role` VALUES ('62', '520103041185127174', '6', '2019-09-02 18:59:09', null);
INSERT INTO `sys_user_role` VALUES ('63', '520101042142354929', '6', '2019-09-04 14:31:40', null);
INSERT INTO `sys_user_role` VALUES ('64', '520101042142509889', '5', '2019-09-04 14:32:49', null);
INSERT INTO `sys_user_role` VALUES ('65', '520101042142606671', '30', '2019-09-04 14:33:47', null);
INSERT INTO `sys_user_role` VALUES ('67', '520101043115026097', '1', '2019-09-05 11:58:40', null);
INSERT INTO `sys_user_role` VALUES ('68', '520101043115026097', '6', '2019-09-05 11:58:40', null);
INSERT INTO `sys_user_role` VALUES ('69', '520101043115026097', '5', '2019-09-05 11:58:40', null);
INSERT INTO `sys_user_role` VALUES ('70', '520101043115026097', '30', '2019-09-05 11:58:40', null);
INSERT INTO `sys_user_role` VALUES ('71', '520101043115026097', '2', '2019-09-05 11:58:40', null);
INSERT INTO `sys_user_role` VALUES ('72', '440101044141923458', '6', '2019-09-05 14:27:07', null);
INSERT INTO `sys_user_role` VALUES ('73', '440101044142106642', '5', '2019-09-05 14:28:48', null);
INSERT INTO `sys_user_role` VALUES ('74', '440101044142135616', '30', '2019-09-05 14:29:19', null);
INSERT INTO `sys_user_role` VALUES ('75', '520103045143057979', '6', '2019-09-05 14:38:39', null);
INSERT INTO `sys_user_role` VALUES ('76', '520101046143145043', '6', '2019-09-05 14:39:25', null);
INSERT INTO `sys_user_role` VALUES ('77', '520103045143414174', '5', '2019-09-05 14:41:56', null);
INSERT INTO `sys_user_role` VALUES ('78', '520103045143446419', '30', '2019-09-05 14:42:31', null);
INSERT INTO `sys_user_role` VALUES ('79', '520101046143540160', '5', '2019-09-05 14:43:21', null);
INSERT INTO `sys_user_role` VALUES ('80', '520101046143733691', '30', '2019-09-05 14:45:15', null);
INSERT INTO `sys_user_role` VALUES ('81', '520101046143620513', '30', '2019-09-05 14:45:50', null);
INSERT INTO `sys_user_role` VALUES ('82', '520101048144129044', '6', '2019-09-05 14:49:10', null);
INSERT INTO `sys_user_role` VALUES ('83', '520101048144626685', '5', '2019-09-05 14:54:07', null);
INSERT INTO `sys_user_role` VALUES ('84', '520101048144653461', '30', '2019-09-05 14:54:34', null);
INSERT INTO `sys_user_role` VALUES ('85', '520102049100717501', '6', '2019-09-06 10:15:01', null);
INSERT INTO `sys_user_role` VALUES ('86', '520102049100825138', '5', '2019-09-06 10:16:09', null);
INSERT INTO `sys_user_role` VALUES ('87', '520102049101203498', '30', '2019-09-06 10:19:50', null);
INSERT INTO `sys_user_role` VALUES ('88', '520114050132123789', '6', '2019-09-06 13:29:07', null);
INSERT INTO `sys_user_role` VALUES ('89', '520114050132234114', '5', '2019-09-06 13:30:17', null);
INSERT INTO `sys_user_role` VALUES ('90', '520114050132302675', '30', '2019-09-06 13:30:45', null);
INSERT INTO `sys_user_role` VALUES ('91', '520101043150002060', '6', '2019-09-10 15:07:52', null);
INSERT INTO `sys_user_role` VALUES ('92', '520101043150058452', '5', '2019-09-10 15:08:50', null);
INSERT INTO `sys_user_role` VALUES ('94', '520101055150837122', '5', '2019-09-10 15:16:27', null);
INSERT INTO `sys_user_role` VALUES ('95', '520101055150908550', '30', '2019-09-10 15:16:58', null);
INSERT INTO `sys_user_role` VALUES ('96', '520101055150653338', '6', '2019-09-10 15:23:39', null);
INSERT INTO `sys_user_role` VALUES ('97', '520102052162503078', '6', '2019-09-10 16:32:54', null);
INSERT INTO `sys_user_role` VALUES ('98', '520103051162553708', '6', '2019-09-10 16:33:44', null);
INSERT INTO `sys_user_role` VALUES ('99', '520103051162709956', '5', '2019-09-10 16:35:00', null);
INSERT INTO `sys_user_role` VALUES ('100', '520103051162757745', '5', '2019-09-10 16:35:48', null);
INSERT INTO `sys_user_role` VALUES ('101', '520102052162958929', '5', '2019-09-10 16:37:49', null);
INSERT INTO `sys_user_role` VALUES ('102', '520102052163025115', '30', '2019-09-10 16:38:15', null);
INSERT INTO `sys_user_role` VALUES ('103', '520103056222330325', '6', '2019-09-15 22:31:37', null);
INSERT INTO `sys_user_role` VALUES ('104', '520103056222330325', '5', '2019-09-15 22:31:37', null);
INSERT INTO `sys_user_role` VALUES ('105', '520103057170120672', '6', '2019-09-16 17:09:24', null);
INSERT INTO `sys_user_role` VALUES ('106', '520103057170209392', '5', '2019-09-16 17:10:14', null);
INSERT INTO `sys_user_role` VALUES ('107', '520103057170241046', '30', '2019-09-16 17:10:45', null);
INSERT INTO `sys_user_role` VALUES ('108', '520103056174404282', '6', '2019-09-16 17:52:06', null);
INSERT INTO `sys_user_role` VALUES ('109', '520103056174556055', '6', '2019-09-16 17:54:00', null);
INSERT INTO `sys_user_role` VALUES ('110', '520103056174808907', '30', '2019-09-16 17:56:11', null);
INSERT INTO `sys_user_role` VALUES ('111', '520102058141115423', '6', '2019-09-20 14:19:24', null);
INSERT INTO `sys_user_role` VALUES ('112', '520102058141206776', '5', '2019-09-20 14:20:15', null);
INSERT INTO `sys_user_role` VALUES ('113', '520102058141231907', '30', '2019-09-20 14:20:41', null);
INSERT INTO `sys_user_role` VALUES ('115', '520101059110945379', '6', '2019-09-24 11:18:01', null);
INSERT INTO `sys_user_role` VALUES ('116', '520101059111250153', '5', '2019-09-24 11:21:10', null);
INSERT INTO `sys_user_role` VALUES ('117', '520101059111327871', '30', '2019-09-24 11:21:44', null);
INSERT INTO `sys_user_role` VALUES ('118', '520102047111445328', '30', '2019-09-24 11:23:04', null);
INSERT INTO `sys_user_role` VALUES ('119', '520102047111522381', '5', '2019-09-24 11:23:40', null);
INSERT INTO `sys_user_role` VALUES ('120', '520101062140618939', '6', '2019-09-24 14:14:35', null);
INSERT INTO `sys_user_role` VALUES ('121', '100000060142917266', '6', '2019-09-24 14:37:33', null);
INSERT INTO `sys_user_role` VALUES ('122', '100000060143012048', '5', '2019-09-24 14:38:28', null);
INSERT INTO `sys_user_role` VALUES ('123', '100000060143036205', '30', '2019-09-24 14:38:54', null);
INSERT INTO `sys_user_role` VALUES ('124', '100000064143447737', '6', '2019-09-24 14:43:05', null);
INSERT INTO `sys_user_role` VALUES ('125', '100000064143530560', '5', '2019-09-24 14:43:46', null);
INSERT INTO `sys_user_role` VALUES ('126', '100000064143609336', '30', '2019-09-24 14:44:27', null);
INSERT INTO `sys_user_role` VALUES ('127', '100000061143920350', '6', '2019-09-24 14:47:36', null);
INSERT INTO `sys_user_role` VALUES ('128', '100000061144028423', '5', '2019-09-24 14:48:45', null);
INSERT INTO `sys_user_role` VALUES ('129', '100000061144057110', '30', '2019-09-24 14:49:13', null);
INSERT INTO `sys_user_role` VALUES ('130', '100000063144616049', '6', '2019-09-24 14:54:31', null);
INSERT INTO `sys_user_role` VALUES ('131', '100000063144709656', '5', '2019-09-24 14:55:26', null);
INSERT INTO `sys_user_role` VALUES ('132', '100000063144736895', '30', '2019-09-24 14:55:53', null);
INSERT INTO `sys_user_role` VALUES ('133', '520101065180045885', '6', '2019-09-26 18:09:06', null);
INSERT INTO `sys_user_role` VALUES ('134', '520101065180246228', '5', '2019-09-26 18:11:06', null);
INSERT INTO `sys_user_role` VALUES ('135', '520101065180325606', '30', '2019-09-26 18:11:46', null);
INSERT INTO `sys_user_role` VALUES ('136', '520101067140919195', '6', '2019-09-28 14:17:43', null);
INSERT INTO `sys_user_role` VALUES ('137', '520101067141035177', '5', '2019-09-28 14:18:59', null);
INSERT INTO `sys_user_role` VALUES ('138', '520101067141109846', '30', '2019-09-28 14:19:34', null);
INSERT INTO `sys_user_role` VALUES ('139', '520102047163216017', '5', '2019-09-28 16:40:40', null);
INSERT INTO `sys_user_role` VALUES ('141', '520102047110145279', '33', '2019-09-28 16:55:52', null);
INSERT INTO `sys_user_role` VALUES ('142', '520000066134615506', '6', '2019-09-29 13:54:41', null);
INSERT INTO `sys_user_role` VALUES ('143', '520000066134706386', '5', '2019-09-29 13:55:31', null);
INSERT INTO `sys_user_role` VALUES ('144', '520000066134733940', '30', '2019-09-29 13:55:59', null);
INSERT INTO `sys_user_role` VALUES ('145', '100000072134920834', '6', '2019-09-30 13:57:47', null);
INSERT INTO `sys_user_role` VALUES ('146', '100000072135158725', '5', '2019-09-30 14:00:35', null);
INSERT INTO `sys_user_role` VALUES ('147', '100000072135242497', '30', '2019-09-30 14:01:12', null);
INSERT INTO `sys_user_role` VALUES ('148', '520101071144823658', '6', '2019-09-30 14:56:50', null);
INSERT INTO `sys_user_role` VALUES ('149', '520101071144939313', '5', '2019-09-30 14:58:06', null);
INSERT INTO `sys_user_role` VALUES ('150', '520101071144959581', '30', '2019-09-30 14:58:27', null);
INSERT INTO `sys_user_role` VALUES ('151', '520101062161123848', '5', '2019-10-10 16:20:12', null);
INSERT INTO `sys_user_role` VALUES ('152', '520101062161154191', '30', '2019-10-10 16:20:40', null);
INSERT INTO `sys_user_role` VALUES ('153', '450100074094448735', '6', '2019-10-18 09:45:15', null);
INSERT INTO `sys_user_role` VALUES ('154', '520111075132739022', '6', '2019-10-18 13:27:49', null);
INSERT INTO `sys_user_role` VALUES ('155', '520111075132840196', '5', '2019-10-18 13:28:50', null);
INSERT INTO `sys_user_role` VALUES ('156', '520111075132906642', '30', '2019-10-18 13:29:16', null);
INSERT INTO `sys_user_role` VALUES ('157', '1', '1', '2019-10-30 19:21:21', null);
INSERT INTO `sys_user_role` VALUES ('158', '1', '5', '2019-10-30 19:21:21', null);
INSERT INTO `sys_user_role` VALUES ('159', '1', '6', '2019-10-30 19:21:21', null);
INSERT INTO `sys_user_role` VALUES ('160', '1', '30', '2019-10-30 19:21:21', null);

-- ----------------------------
-- Function structure for COPY_TEMPLATE_TO_TERMINAL
-- ----------------------------
DROP FUNCTION IF EXISTS `COPY_TEMPLATE_TO_TERMINAL`;
DELIMITER ;;
CREATE DEFINER=`chais`@`%` FUNCTION `COPY_TEMPLATE_TO_TERMINAL`(templateId VARCHAR(50),terminalId varchar(50),hotelId varchar(50)) RETURNS tinyint(6)
BEGIN
	delete from cf_pt_config  where ti_id = terminalId;
  insert into cf_pt_config(ti_id,hi_id,p_id,p_type,p_category,p_name,p_comment,p_version_new,is_dict,dict_type,p_value)
  select terminalId,hotelId,s.p_id,s.p_type,s.p_category,s.p_name,s.p_comment,s.p_version_new,s.is_dict,s.dict_type,tp.value from cf_pt_structure s,cf_pt_templates_paras tp
  where tp.del_flag=0 and s.del_flag=0 and tp.template_id = templateId and s.p_id = tp.p_id;
	RETURN 0;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for CREATE_TEMPLATEPARA
-- ----------------------------
DROP FUNCTION IF EXISTS `CREATE_TEMPLATEPARA`;
DELIMITER ;;
CREATE DEFINER=`chais`@`%` FUNCTION `CREATE_TEMPLATEPARA`(sourceid INT,targetid INT) RETURNS int(11)
BEGIN
	INSERT INTO cf_pt_templates_paras(template_id,p_id,value)
  select targetid,p_id,value from cf_pt_templates_paras where id = sourceid;
	RETURN 0;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for CREATE_TERMINAL
-- ----------------------------
DROP FUNCTION IF EXISTS `CREATE_TERMINAL`;
DELIMITER ;;
CREATE DEFINER=`chais`@`%` FUNCTION `CREATE_TERMINAL`(terminalid VARCHAR(50),hotelid varchar(50)) RETURNS tinyint(1)
BEGIN
	insert into cf_pt_config(ti_id,hi_id,p_id,p_type,p_category,p_name,p_comment,p_version_new,is_dict,dict_type)
  select terminalid,hotelid,p_id,p_type,p_category,p_name,p_comment,p_version_new,is_dict,dict_type from cf_pt_structure;
	RETURN 1;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for NEXT_VAL
-- ----------------------------
DROP FUNCTION IF EXISTS `NEXT_VAL`;
DELIMITER ;;
CREATE DEFINER=`chais`@`%` FUNCTION `NEXT_VAL`(sequence_name VARCHAR(64)) RETURNS bigint(20)
BEGIN
    UPDATE CORE_SEQUENCE t, (SELECT @current_val:=`value` FROM CORE_SEQUENCE t2 WHERE t2.sequence_name=sequence_name) t3 SET t.value = t.value + 1 WHERE t.sequence_name =sequence_name AND @current_val=t.value;
    RETURN @current_val+1;
END
;;
DELIMITER ;

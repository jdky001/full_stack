CREATE DATABASE /*!32312 IF NOT EXISTS*/`newbee_mall` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `newbee_mall`;

DROP TABLE IF EXISTS `tb_newbee_mall_admin_user`;

CREATE TABLE `tb_newbee_mall_admin_user` (
     `admin_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
     `login_user_name` varchar(50) NOT NULL COMMENT '管理员登陆名称',
     `login_password` varchar(50) NOT NULL COMMENT '管理员登陆密码',
     `nick_name` varchar(50) NOT NULL COMMENT '管理员显示昵称',
     `locked` tinyint(4) DEFAULT '0' COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
     PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

LOCK TABLES `tb_newbee_mall_admin_user` WRITE;
/*!40000 ALTER TABLE `tb_newbee_mall_admin_user` DISABLE KEYS */;

INSERT INTO `tb_newbee_mall_admin_user` (`admin_user_id`, `login_user_name`, `login_password`, `nick_name`, `locked`)
VALUES
    (1,'admin','e10adc3949ba59abbe56e057f20f883e','新蜂00',0),
    (2,'newbee-admin1','e10adc3949ba59abbe56e057f20f883e','新蜂01',0),
    (3,'newbee-admin2','e10adc3949ba59abbe56e057f20f883e','新蜂02',0);

DROP TABLE IF EXISTS `tb_newbee_mall_admin_user_token`;

CREATE TABLE `tb_newbee_mall_admin_user_token` (
     `admin_user_id` bigint(20) NOT NULL COMMENT '用户主键id',
     `token` varchar(32) NOT NULL COMMENT 'token值(32位字符串)',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
     `expire_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'token过期时间',
     PRIMARY KEY (`admin_user_id`),
     UNIQUE KEY `uq_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
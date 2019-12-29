CREATE DATABASE mall_order;

USE mall_order;

CREATE TABLE order_main(
	id VARCHAR (64) NOT NULL PRIMARY KEY COMMENT '主键',
	order_no VARCHAR (64) NOT NULL COMMENT '订单编码',
	order_amount DECIMAL(10, 3) UNSIGNED DEFAULT '0.000' COMMENT '订单金额',
	create_date datetime default NOW() COMMENT '订单创建时间',
	receipt_people VARCHAR (100) NOT NULL COMMENT '收件人',
	receipt_address varchar(150) not null comment '收件地址',
	order_status tinyint(1) default '0' COMMENT '订单状态: 0 待支付 1 已支付 2 已取消',
	pay_no varchar(100) default null comment '支付编码',
	UNIQUE KEY uk_order_no_pay_no (order_no, pay_no),
    UNIQUE KEY uk_order_no (order_no)
) ENGINE = INNODB DEFAULT charset = utf8mb4 COMMENT '订单表';

CREATE TABLE order_detail(
  id VARCHAR(64) NOT NULL primary key COMMENT '主键',
  order_no varchar(64) NOT NULL COMMENT '订单编码',
  store_no VARCHAR (64) NOT NULL COMMENT '店铺编码',
  store_name VARCHAR (100) NOT NULL COMMENT '店铺名',
  sku VARCHAR (100) NOT NULL COMMENT '商品编码',
  goods_name VARCHAR (100) NOT NULL COMMENT '商品名称',
  change_num BIGINT UNSIGNED DEFAULT '0' COMMENT '数量',
  amount DECIMAL(10, 3) UNSIGNED DEFAULT '0.000' COMMENT '金额',
  order_status tinyint(1) default '0' COMMENT '订单状态: 0 待支付 1 已支付 2 已取消',
  UNIQUE KEY uk_order_no_sku(order_no,sku)
) ENGINE = INNODB DEFAULT charset = utf8mb4 COMMENT '订单明细';


DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `branch_id` bigint(20) NOT NULL,
    `xid` varchar(100) NOT NULL,
    `context` varchar(128) NOT NULL,
    `rollback_info` longblob NOT NULL,
    `log_status` int(11) NOT NULL,
    `log_created` datetime NOT NULL,
    `log_modified` datetime NOT NULL,
    `ext` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
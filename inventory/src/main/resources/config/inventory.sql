CREATE DATABASE mall_inventory;

USE mall_inventory;

CREATE TABLE inventory_goods (
 id VARCHAR (64) NOT NULL PRIMARY KEY COMMENT '主键',
 store_no VARCHAR (64) NOT NULL COMMENT '店铺编码',
 store_name VARCHAR (100) NOT NULL COMMENT '店铺名',
 sku VARCHAR (100) NOT NULL COMMENT '商品编码',
 goods_name VARCHAR (100) NOT NULL COMMENT '商品名称',
 price DECIMAL (10, 3) UNSIGNED DEFAULT '0.000' COMMENT '商品单价',
 storage_num BIGINT UNSIGNED DEFAULT '0' COMMENT '库存数',
 available_num BIGINT UNSIGNED DEFAULT '0' COMMENT '可用数',
 wait_pay_num BIGINT UNSIGNED DEFAULT '0' COMMENT '待支付数量',
 sell_num BIGINT UNSIGNED DEFAULT '0' COMMENT '已卖出数量',
 goods_status TINYINT (1) DEFAULT '0' COMMENT '商品状态: 0 在架 1 下架',
 UNIQUE KEY uk_store_no_sku (store_no, sku)
) ENGINE = INNODB DEFAULT charset = utf8mb4 COMMENT '商品库存表';


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
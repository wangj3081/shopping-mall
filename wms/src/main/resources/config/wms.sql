CREATE DATABASE mall_wms;

USE mall_wms;

CREATE TABLE wms_storage (
     id VARCHAR (64) NOT NULL PRIMARY KEY COMMENT '主键',
     storage_no VARCHAR (64) NOT NULL COMMENT '店铺编码',
     storage_name VARCHAR (100) NOT NULL COMMENT '店铺名',
     sku VARCHAR (100) NOT NULL COMMENT '商品编码',
     available_num BIGINT UNSIGNED DEFAULT '0' COMMENT '可用数',
     storage_num BIGINT UNSIGNED DEFAULT '0' COMMENT '在仓数',
     wait_outbount BIGINT UNSIGNED DEFAULT '0' COMMENT '待出库数',
     outbount BIGINT UNSIGNED DEFAULT '0' COMMENT '已出库数',
     UNIQUE KEY uk_storage_no_sku (storage_no, sku)
) ENGINE = INNODB DEFAULT charset = utf8mb4 COMMENT '仓库存量表';

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
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


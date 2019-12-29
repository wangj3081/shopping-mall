CREATE DATABASE mall_integral;

USE mall_integral;

CREATE TABLE integral_record (
     id VARCHAR (64) NOT NULL PRIMARY KEY COMMENT '主键',
     storage_no VARCHAR (64) NOT NULL COMMENT '店铺编码',
     storage_name VARCHAR (100) NOT NULL COMMENT '店铺名',
     order_no VARCHAR (64) NOT NULL COMMENT '订单编码',
     pay_no VARCHAR (100) NOT NULL COMMENT '支付编码(支付凭证)',
     amount DECIMAL (10, 3) UNSIGNED DEFAULT '0.000' COMMENT '消费金额',
     integral BIGINT UNSIGNED DEFAULT '0' COMMENT '积分',
     UNIQUE KEY uk_pay_no (pay_no)
) ENGINE = INNODB DEFAULT charset = utf8mb4 COMMENT '仓库存量表';


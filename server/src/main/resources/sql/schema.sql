CREATE DATABASE IF NOT EXISTS stcos;

USE stcos;

CREATE TABLE IF NOT EXISTS t_admin
(
    uid                     VARCHAR(64) NOT NULL,
    password                VARCHAR(64) DEFAULT NULL,
    username                VARCHAR(30) NOT NULL,
    account_non_expired     BOOLEAN     DEFAULT TRUE,
    account_non_locked      BOOLEAN     DEFAULT TRUE,
    credentials_non_expired BOOLEAN     DEFAULT TRUE,
    enabled                 BOOLEAN     DEFAULT TRUE,
    PRIMARY KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_operator
(
    uid                     VARCHAR(64) NOT NULL,
    password                VARCHAR(64) DEFAULT NULL,   # 密码
    username                VARCHAR(32) NOT NULL,       # 用户名
    realName                VARCHAR(32) NOT NULL,       # 姓名
    email                   VARCHAR(32) NOT NULL,       # 邮箱
    account_non_expired     BOOLEAN     DEFAULT TRUE,
    account_non_locked      BOOLEAN     DEFAULT TRUE,
    credentials_non_expired BOOLEAN     DEFAULT TRUE,
    enabled                 BOOLEAN     DEFAULT TRUE,
    PRIMARY KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_client
(
    uid                     VARCHAR(64) NOT NULL,
    password                VARCHAR(64) DEFAULT NULL,   # 密码
    username                VARCHAR(32) NOT NULL,       # 用户名
    realName                VARCHAR(32) NOT NULL,       # 姓名
    email                   VARCHAR(32) NOT NULL,       # 邮箱
    account_non_expired     BOOLEAN     DEFAULT TRUE,
    account_non_locked      BOOLEAN     DEFAULT TRUE,
    credentials_non_expired BOOLEAN     DEFAULT TRUE,
    enabled                 BOOLEAN     DEFAULT TRUE,
    PRIMARY KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_setting
(
    setting_key VARCHAR(64) NOT NULL,
    setting_val VARCHAR(64) NOT NULL,
    PRIMARY KEY (setting_key)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


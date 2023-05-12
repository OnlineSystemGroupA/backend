CREATE DATABASE IF NOT EXISTS stcos;

USE stcos;

# DROP TABLE IF EXISTS user;

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
    password                VARCHAR(64) DEFAULT NULL,
    username                VARCHAR(30) NOT NULL,
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
    password                VARCHAR(64) DEFAULT NULL,
    username                VARCHAR(30) NOT NULL,
    email                   VARCHAR(20) DEFAULT NULL,
    account_non_expired     BOOLEAN     DEFAULT TRUE,
    account_non_locked      BOOLEAN     DEFAULT TRUE,
    credentials_non_expired BOOLEAN     DEFAULT TRUE,
    enabled                 BOOLEAN     DEFAULT TRUE,
    PRIMARY KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_authority
(
    uid       VARCHAR(64) NOT NULL,
    authority VARCHAR(10) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
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
    authority_id         VARCHAR(64) NOT NULL,
    authority_name       VARCHAR(10) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_user_authority
(
    uid             VARCHAR(64) NOT NULL,
    authority_id    VARCHAR(64) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS t_role
(
    role_id      VARCHAR(64) NOT NULL,
    role_name    VARCHAR(10) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_user_role
(
    uid         VARCHAR(64) NOT NULL,
    role_id     VARCHAR(64) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_file_metadata
(
    file_metadata_id    BIGINT NOT NULL,
    file_name           VARCHAR(64) NOT NULL,
    file_type           VARCHAR(10) NOT NULL,
    file_size           BIGINT NOT NULL,
    updated_by          VARCHAR(64) NOT NULL,
    updated_date        DATETIME NOT NULL,
    file_path           VARCHAR(64) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

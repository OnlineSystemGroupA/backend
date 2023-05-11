CREATE DATABASE IF NOT EXISTS stcos;

USE stcos;

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    uid                   VARCHAR(20) NOT NULL,
    password              VARCHAR(30) DEFAULT NULL,
    username              VARCHAR(30) DEFAULT NULL,
    accountNonExpired     BOOLEAN     DEFAULT TRUE,
    accountNonLocked      BOOLEAN     DEFAULT TRUE,
    credentialsNonExpired BOOLEAN     DEFAULT TRUE,
    enabled               BOOLEAN     DEFAULT TRUE,
    PRIMARY KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE DATABASE IF NOT EXISTS stcos;

USE stcos;

-- 管理员表
CREATE TABLE IF NOT EXISTS t_admin
(
    uid      VARCHAR(64) NOT NULL,
    username VARCHAR(30) NOT NULL,     -- 用户名
    password VARCHAR(64) DEFAULT NULL, -- 密码
    PRIMARY KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 员工表
CREATE TABLE IF NOT EXISTS t_operator
(
    uid                     VARCHAR(64) NOT NULL,
    job_number              VARCHAR(32) NOT NULL,     -- 员工工号
    password                VARCHAR(64) DEFAULT NULL, -- 密码
    created_date            DATETIME    NOT NULL,     -- 账号创建时间
    email                   VARCHAR(32) NOT NULL,     -- 邮箱
    phone                   VARCHAR(16),              -- 联系电话
    real_name               VARCHAR(32),              -- 姓名
    department              VARCHAR(32),              -- 部门
    position                VARCHAR(32),              -- 职位
    is_manager              BOOLEAN     DEFAULT FALSE,-- 是否为主管
    process_instance        TEXT        NOT NULL,     -- 可见的流程实例
    process_record          TEXT        NOT NULL,     -- 可见的流程记录
    account_non_expired     BOOLEAN     DEFAULT TRUE,
    account_non_locked      BOOLEAN     DEFAULT TRUE,
    credentials_non_expired BOOLEAN     DEFAULT TRUE,
    enabled                 BOOLEAN     DEFAULT TRUE,
    PRIMARY KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 客户表
CREATE TABLE IF NOT EXISTS t_client
(
    uid                     VARCHAR(64) NOT NULL,
    username                VARCHAR(32) NOT NULL,     -- 用户名
    password                VARCHAR(64) DEFAULT NULL, -- 密码
    created_date            DATETIME    NOT NULL,     -- 账号创建时间
    email                   VARCHAR(32) NOT NULL,     -- 邮箱
    phone                   VARCHAR(16),              -- 联系电话
    real_name               VARCHAR(32),              -- 姓名
    gender                  VARCHAR(8),               -- 性别
    company                 VARCHAR(64),              -- 公司名称
    company_telephone       VARCHAR(16),              -- 公司电话号
    company_fax             VARCHAR(16),              -- 公司传真
    company_address         VARCHAR(128),             -- 公司地址
    company_postcode        VARCHAR(16),              -- 公司邮编
    company_website         VARCHAR(64),              -- 公司网址
    company_email           VARCHAR(32),              -- 公司邮箱
    company_phone           VARCHAR(16),              -- 公司手机号
    processes_instance      TEXT        NOT NULL,     -- 可见的流程实例
    processes_record        TEXT        NOT NULL,     -- 可见的流程记录
    account_non_expired     BOOLEAN     DEFAULT TRUE,
    account_non_locked      BOOLEAN     DEFAULT TRUE,
    credentials_non_expired BOOLEAN     DEFAULT TRUE,
    enabled                 BOOLEAN     DEFAULT TRUE,
    PRIMARY KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 平台配置表
CREATE TABLE IF NOT EXISTS t_setting
(
    setting_key VARCHAR(64) NOT NULL,
    setting_val VARCHAR(64) NOT NULL,
    PRIMARY KEY (setting_key)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- FormMetadata
CREATE TABLE IF NOT EXISTS t_form_metadata
(
    form_metadata_id   BIGINT AUTO_INCREMENT, -- 表单元数据 ID，保存对象时由数据库自动赋值
    form_id            BIGINT      NOT NULL,  --  表单元数据对应表单的 ID
    project_id         BIGINT      NOT NULL,  -- 项目号
    form_type          VARCHAR(64) NOT NULL,  -- 表单名
    form_state         VARCHAR(16) NOT NULL,  -- 表单状态
    created_by         VARCHAR(64),           -- 表单的创建者 (userId)
    created_date       DATETIME,              -- 表单创建时间
    last_modified_by   VARCHAR(64),           -- 表单最后一次被谁修改 (userId)
    last_modified_date DATETIME,              -- 表单最后一次被修改的时间
    readable_users     TEXT        NOT NULL,  -- 对表单具有读权限用户的 ID 列表
    writable_users     TEXT        NOT NULL,  -- 对表单具有写权限用户的 ID 列表
    PRIMARY KEY (form_metadata_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ProcessRecord
CREATE TABLE IF NOT EXISTS t_process_record
(
    record_id bigint AUTO_INCREMENT, --
    temp      VARCHAR(64) NOT NULL,  --
    PRIMARY KEY (record_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- TaskDetails
CREATE TABLE IF NOT EXISTS t_task_details
(
    task_id     BIGINT       NOT NULL,
    process_id  BIGINT       NOT NULL,
    task_name   VARCHAR(64)  NOT NULL,
    department  VARCHAR(64)  NOT NULL,
    operator    VARCHAR(64)  NOT NULL,
    start_date  DATETIME     NOT NULL,
    finish_date DATETIME     NOT NULL,
    result      BOOLEAN      NOT NULL,
    description VARCHAR(128) NOT NULL,
    PRIMARY KEY (task_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ProcessDetails
CREATE TABLE IF NOT EXISTS t_process_details
(
    project_id       BIGINT AUTO_INCREMENT,
    title            VARCHAR(64) NOT NULL,
    version          VARCHAR(64) NOT NULL,
    test_type        VARCHAR(64) NOT NULL,
    application_date VARCHAR(64) NOT NULL,
    applicant        VARCHAR(64) NOT NULL,
    company          VARCHAR(64) NOT NULL,
    telephone        VARCHAR(64) NOT NULL,
    email            VARCHAR(64) NOT NULL,
    address          VARCHAR(64) NOT NULL,
    start_date       DATETIME,
    due_date         DATETIME,
    PRIMARY KEY (project_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_file_metadata
(
    file_metadata_id BIGINT AUTO_INCREMENT,
    file_name        VARCHAR(128) NOT NULL,
    file_type        VARCHAR(32)  NOT NULL,
    file_size        BIGINT       NOT NULL,
    updated_by       VARCHAR(64)  NOT NULL,
    updated_date     DATETIME     NOT NULL,
    file_path        VARCHAR(256) NOT NULL,
    PRIMARY KEY (file_metadata_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- SampleMetadata
CREATE TABLE IF NOT EXISTS t_sample_metadata
(
    sample_metadata_id    BIGINT AUTO_INCREMENT, -- 样品元数据 ID，保存对象时由数据库自动赋值
    readable_users        TEXT NOT NULL,         -- 对样品具有读权限用户的 ID 列表
    writable_users        TEXT NOT NULL,         -- 对样品具有写权限用户的 ID 列表
    file_metadata_id_list TEXT,                  -- 文件元数据 ID 列表
    PRIMARY KEY (sample_metadata_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ProcessRecord
CREATE TABLE IF NOT EXISTS t_process_record
(
    project_id            BIGINT AUTO_INCREMENT,
    client_id             VARCHAR(64) NOT NULL,
    marketing_manager_id  VARCHAR(64) NOT NULL,
    testing_manager_id    VARCHAR(64) NOT NULL,
    quality_manager_id    VARCHAR(64) NOT NULL,
    signatory_id          VARCHAR(64) NOT NULL,
    marketing_operator_id VARCHAR(64) NOT NULL,
    testing_operator_id   VARCHAR(64) NOT NULL,
    start_user_name       VARCHAR(32) NOT NULL,
    title                 VARCHAR(64) NOT NULL,
    start_date            DATETIME    NOT NULL,
    finish_date           DATETIME    NOT NULL,
    PRIMARY KEY (project_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
USE stcos;

-- 初始化平台设置
INSERT IGNORE INTO t_setting
VALUES ('marketingManager', 'op-1'),
       ('testingManager', 'op-2'),
       ('qualityManager', 'op-3'),
       ('signatory', 'op-4');
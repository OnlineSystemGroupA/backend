USE stcos;

-- 初始化平台设置
INSERT IGNORE INTO t_setting
VALUES ('marketingManager', 'op-2'),
       ('testingManager', 'op-1'),
       ('qualityManager', 'op-3'),
       ('signatory', 'op-4'),
       ('pdfFilePath', './data/pdf'),
       ('sampleFilePath', './data/sample');
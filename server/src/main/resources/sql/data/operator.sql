USE stcos;

-- 初始化工作人员
INSERT IGNORE INTO t_operator
VALUES
    -- 默认各主管以及授权签字人
    ('op-1', '20xxx0214', '$2a$10$RZqhp/5zIyGe43JL95YC1.hsVR1aZVb6zfpakeG36JHRFZY.EMHW6', '2011-01-04', 'keekkewy@qq.com', '98978675', '华法琳', '测试部', '主管', true, '[]', '[]', true, true, true, true),
    ('op-2', '20xxx0001', '$2a$10$f3r4KsJRrGmP2PMHZlA.aO6HAvgtKAwuGzkiIaUFJG768VJpRLTVq', '2011-01-04', 'keekkewy@qq.com', '123415', '德克萨斯', '市场部', '主管', true, '[]', '[]', true, true, true, true),
    ('op-3', '20xxx0002', '$2a$10$6hgJBQrtkNNRC/elrC0cDeVMyQUbFyfFAlrndFC4ivHU9tsvhcOsy', '2011-01-04', 'keekkewy@qq.com', '1237734124', '陈', '质量部', '主管', true, '[]', '[]', true, true, true, true),
    ('op-4', '20xxx0003', '$2a$10$ECWwgZ7WN91eji0hfsWo6.lbmblp1SrFYC.F7veG.MNu.bCZksAAK', '2011-01-04', 'keekkewy@qq.com', '24357879', '推进之王', '授权签字人', '', false, '[]', '[]', true, true, true, true),
    -- 用于测试的其他普通工作人员
    ('op-5', '20xxx0004', '$2a$10$ARPeuMSoVgjad.Z6YO1DMeoHIoE7ABOxih3Xpt1jpc.Q.HLv/CyUG', '2011-01-04', 'keekkewy@qq.com', '34455376', '凯尔希', '测试部', '员工', false, '[]', '[]', true, true, true, true),
    ('op-6', '20xxx0005', '$2a$10$n5che4IPj7phtgp2oV.3WOknZzPxg7wF74boA8CaJ/PPYYSGapMBG', '2011-01-04', 'keekkewy@qq.com', '4345345', '塞雷娅', '测试部', '员工', false, '[]', '[]', true, true, true, true),
    ('op-7', '20xxx0006', '$2a$10$.kRq/xH/ogjNS2k0KLopi.pI9nBNN/LbVIkUF8SpiGS6V3.jQ5c4a', '2011-01-04', 'keekkewy@qq.com', '23457564', '星熊', '质量部', '员工', false, '[]', '[]', true, true, true, true),
    ('op-8', '20xxx0007', '$2a$10$gI92duj/iLs/izAGJqBWTuici6FhXQTeSYYXTz7j4PHTcrOQ6LZ1u', '2011-01-04', 'keekkewy@qq.com', '345423', '鸿雪', '质量部', '员工', false, '[]', '[]', true, true, true, true),
    ('op-9', '20xxx0008', '$2a$10$AgF6RmIuhNJcL.mA/2qab.2gCPpkuUbsAbE0KFbb8Xt1TngRgoWqK', '2011-01-04', 'keekkewy@qq.com', '1765841', '能天使', '市场部', '员工', false, '[]', '[]', true, true, true, true),
    ('op-10', '20xxx0009', '$2a$10$5TZgjAbFbWk.gTW4ToxBl.dnKCRIiGHBTDKUtvgDfn7SQesZDH2Gi', '2011-01-04', 'keekkewy@qq.com', '31248907', '拉普兰德', '市场部', '员工', false, '[]', '[]', true, true, true, true);

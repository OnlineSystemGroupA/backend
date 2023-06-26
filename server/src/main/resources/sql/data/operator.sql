USE stcos;

-- 初始化工作人员
INSERT IGNORE INTO t_operator
VALUES
    -- 默认各主管以及授权签字人
    ('op-1', '20xxx0214', '$2a$10$RZqhp/5zIyGe43JL95YC1.hsVR1aZVb6zfpakeG36JHRFZY.EMHW6', '华法琳', 'keekkewy@qq.com', true, true, true, true),
    ('op-2', '20xxx0001', '$2a$10$f3r4KsJRrGmP2PMHZlA.aO6HAvgtKAwuGzkiIaUFJG768VJpRLTVq', '德克萨斯', '2875790413@qq.com', true, true, true, true),
    ('op-3', '20xxx0002', '$2a$10$6hgJBQrtkNNRC/elrC0cDeVMyQUbFyfFAlrndFC4ivHU9tsvhcOsy', '陈', '1169366059@qq.com', true, true, true, true),
    ('op-4', '20xxx0003', '$2a$10$ECWwgZ7WN91eji0hfsWo6.lbmblp1SrFYC.F7veG.MNu.bCZksAAK', '推进之王', '1738337761@qq.com', true, true, true, true),
    -- 用于测试的其他普通工作人员
    ('op-5', '20xxx0004', '$2a$10$ARPeuMSoVgjad.Z6YO1DMeoHIoE7ABOxih3Xpt1jpc.Q.HLv/CyUG', '凯尔希', '519505988@qq.com', true, true, true, true),
    ('op-6', '20xxx0005', '$2a$10$n5che4IPj7phtgp2oV.3WOknZzPxg7wF74boA8CaJ/PPYYSGapMBG', '塞雷娅', '1138705975@qq.com', true, true, true, true),
    ('op-7', '20xxx0006', '$2a$10$.kRq/xH/ogjNS2k0KLopi.pI9nBNN/LbVIkUF8SpiGS6V3.jQ5c4a', '星熊', '892907273@qq.com', true, true, true, true),
    ('op-8', '20xxx0007', '$2a$10$gI92duj/iLs/izAGJqBWTuici6FhXQTeSYYXTz7j4PHTcrOQ6LZ1u', '鸿雪', '1252068695@qq.com', true, true, true, true),
    ('op-9', '20xxx0008', '$2a$10$AgF6RmIuhNJcL.mA/2qab.2gCPpkuUbsAbE0KFbb8Xt1TngRgoWqK', '能天使', '68278480@qq.com', true, true, true, true),
    ('op-10', '20xxx0009', '$2a$10$5TZgjAbFbWk.gTW4ToxBl.dnKCRIiGHBTDKUtvgDfn7SQesZDH2Gi', '拉普兰德', 'keekkewy@qq.com', true, true, true, true);

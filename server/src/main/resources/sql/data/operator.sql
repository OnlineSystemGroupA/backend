USE stcos;

-- 初始化工作人员
INSERT IGNORE INTO t_operator
VALUES
    -- 默认各主管以及授权签字人
    ('op-1', '20xxx0214', '$2a$10$RZqhp/5zIyGe43JL95YC1.hsVR1aZVb6zfpakeG36JHRFZY.EMHW6', '2011-01-04', 'keekkewy@qq.com', '98978675', '测试部主管', '测试部', '主管', true, '[]', '[]', true, true, true, true),
    ('op-2', '20xxx0001', '$2a$10$f3r4KsJRrGmP2PMHZlA.aO6HAvgtKAwuGzkiIaUFJG768VJpRLTVq', '2011-01-04', 'keekkewy@qq.com', '123415', '市场部主管', '市场部', '主管', true, '[]', '[]', true, true, true, true),
    ('op-3', '20xxx0002', '$2a$10$6hgJBQrtkNNRC/elrC0cDeVMyQUbFyfFAlrndFC4ivHU9tsvhcOsy', '2011-01-04', 'keekkewy@qq.com', '1237734124', '质量部主管', '质量部', '主管', true, '[]', '[]', true, true, true, true),
    ('op-4', '20xxx0003', '$2a$10$ECWwgZ7WN91eji0hfsWo6.lbmblp1SrFYC.F7veG.MNu.bCZksAAK', '2011-01-04', 'keekkewy@qq.com', '24357879', '授权签字人', '授权签字人', '', false, '[]', '[]', true, true, true, true),
    -- 用于测试的其他普通工作人员
    ('op-5', '20xxx0004', '$2a$10$ARPeuMSoVgjad.Z6YO1DMeoHIoE7ABOxih3Xpt1jpc.Q.HLv/CyUG', '2011-01-04', 'keekkewy@qq.com', '34455376', '测试部员工01', '测试部', '员工', false, '[]', '[]', true, true, true, true),
    ('op-6', '20xxx0005', '$2a$10$n5che4IPj7phtgp2oV.3WOknZzPxg7wF74boA8CaJ/PPYYSGapMBG', '2011-01-04', 'keekkewy@qq.com', '4345345', '测试部员工02', '测试部', '员工', false, '[]', '[]', true, true, true, true),
    ('op-9', '20xxx0008', '$2a$10$AgF6RmIuhNJcL.mA/2qab.2gCPpkuUbsAbE0KFbb8Xt1TngRgoWqK', '2011-01-04', 'keekkewy@qq.com', '1765841', '市场部员工01', '市场部', '员工', false, '[]', '[]', true, true, true, true),
    ('op-10', '20xxx0009', '$2a$10$5TZgjAbFbWk.gTW4ToxBl.dnKCRIiGHBTDKUtvgDfn7SQesZDH2Gi', '2011-01-04', 'keekkewy@qq.com', '31248907', '市场部员工02', '市场部', '员工', false, '[]', '[]', true, true, true, true);

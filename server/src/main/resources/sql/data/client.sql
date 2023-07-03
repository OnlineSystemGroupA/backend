USE stcos;

-- 初始化工作人员
INSERT IGNORE INTO t_client
VALUES
    -- 仅用于测试
    ('cl-1', 'zhang3', '$2a$10$182uYXB7XXB7evgP/rN7Puf5BvwWKNYa5bmC6Ewqske/h9fWIt8ha', '2022-9-8', 'keekkewy@qq.com', '15996247056', '张三', '男', '九乡河第一食堂', '134143', '1000001', '南京栖霞', '1234', 'https://saf.com', 'safd', 'asuhc', '[]', '[]', true, true, true, true),
    ('cl-2', 'li4', '$2a$10$IaXRqJVxbqLOkDgvbUc8LeBJ3NzWoy8Vy4b76Ghji2SN3oNUfirne', '2023-4-6', 'keekkewy@qq.com', '15996247056', '李四', '女', '罗德岛小食堂', '567567', '1000002', '二刺螈', '89789', 'https://dsafsa@w.com', 'sdg', 'uit', '[]', '[]', true, true, true, true),
    ('cl-3', 'wang5', '$2a$10$Dd20XHAwzlI/FeMlRWNrUeTllz6Z0EyyTsaDO726YUGQrgLWw2gB.', '2024-11-19', 'keekkewy@qq.com', '15996247056', '王五', '男', '九乡河技术职业学校', '78968', '10000003', '南大仙林校区', '8927348', 'https://nju.edu.cn', 'vjh', 'saef', '[]', '[]', true, true, true, true);

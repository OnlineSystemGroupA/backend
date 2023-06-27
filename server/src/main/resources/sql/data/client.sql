USE stcos;

-- 初始化工作人员
INSERT IGNORE INTO t_client
VALUES
    -- 仅用于测试
    ('cl-1', 'zhang3', '$2a$10$182uYXB7XXB7evgP/rN7Puf5BvwWKNYa5bmC6Ewqske/h9fWIt8ha', '张三', 'keekkewy@qq.com', '[]', true, true, true, true),
    ('cl-2', 'li4', '$2a$10$IaXRqJVxbqLOkDgvbUc8LeBJ3NzWoy8Vy4b76Ghji2SN3oNUfirne', '李四', '2875790413@qq.com', '[]', true, true, true, true),
    ('cl-3', 'wang5', '$2a$10$Dd20XHAwzlI/FeMlRWNrUeTllz6Z0EyyTsaDO726YUGQrgLWw2gB.', '王五', '1169366059@qq.com', '[]', true, true, true, true);

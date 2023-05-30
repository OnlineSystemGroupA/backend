/*
 * 初始化脚本
 * 用于演示基础功能
 */

use stcos;

REPLACE INTO t_operator
    (uid,username)
VALUES
    #市场部主管
    ("op-1","marketing_manager"),
    #测试部主管
    ("op-2","testing_manager"),
    #质量部主管
    ("op-3","quality_manager"),
    #授权签字人
    ("op-4","signatory"),
    #市场部员工1
    ("op-5","marketing_operator_1"),
    #测试部员工1
    ("op-6","testing_operator_1");


REPLACE INTO t_client
    (uid,username)
VALUES
    #用户1
    ("cl-1","client_1");
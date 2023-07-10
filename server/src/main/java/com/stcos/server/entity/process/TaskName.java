package com.stcos.server.entity.process;

/**
 * 定义整个流程中所有任务的任务名
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/9 14:32
 */

public class TaskName {
    /*    填写申请表    */
    public final static String NAME_TASK_01 = "填写申请表";
    /*     审核委托    */
    public final static String NAME_TASK_02 = "分配市场部人员";
    public final static String NAME_TASK_03 = "分配测试部人员";
    public final static String NAME_TASK_04 = "市场部审核委托";
    public final static String NAME_TASK_05 = "测试部审核委托";
    public final static String NAME_TASK_06 = "市场部审核不通过，修改委托";
    public final static String NAME_TASK_07 = "测试部审核不通过，修改委托";
    /*     生成报价    */
    public final static String NAME_TASK_08 = "市场部生成报价";
    public final static String NAME_TASK_09 = "客户审核报价";
    public final static String NAME_TASK_10 = "市场部修改报价";
    /*   生成合同草稿   */
    public final static String NAME_TASK_11 = "市场部生成合同草稿";
    public final static String NAME_TASK_12 = "客户审核合同草稿";
    public final static String NAME_TASK_13 = "市场部修改合同草稿";
    public final static String NAME_TASK_14 = "客户填写合同";
    public final static String NAME_TASK_15 = "市场部审核合同";
    public final static String NAME_TASK_16 = "客户修改合同";
    /*     签署合同    */
    public final static String NAME_TASK_17 = "客户签署合同";
    public final static String NAME_TASK_18 = "市场部盖章合同";
    /*     上传样品    */
    public final static String NAME_TASK_19 = "客户上传待测样品";
    public final static String NAME_TASK_20 = "测试部审核样品";
    public final static String NAME_TASK_21 = "客户重新上传样品";
    /*   生成测试方案   */
    public final static String NAME_TASK_22 = "测试部生成测试方案";
    public final static String NAME_TASK_23 = "质量部审核测试方案";
    public final static String NAME_TASK_24 = "测试部修改测试方案";
    public final static String NAME_TASK_25 = "测试部主管审核测试方案";
    // 跳过进行软件测试的手动任务
    /*   生成测试报告   */
    public final static String NAME_TASK_26 = "测试部生成测试报告";
    public final static String NAME_TASK_27 = "测试部主管审核测试报告";
    public final static String NAME_TASK_28 = "客户审核测试报告";
    public final static String NAME_TASK_29 = "授权签字人审核测试报告";
    public final static String NAME_TASK_30 = "测试部修改测试报告";
    public final static String NAME_TASK_31 = "测试文档归档";
    /*   确认测试报告   */
    public final static String NAME_TASK_32 = "市场部发送测试报告";
    public final static String NAME_TASK_33 = "用户确认测试报告";
}

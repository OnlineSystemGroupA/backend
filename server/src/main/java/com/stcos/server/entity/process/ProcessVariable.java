package com.stcos.server.entity.process;

import java.util.HashMap;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/19 16:37
 */
public class ProcessVariable extends HashMap<String, Object> {

    public ProcessVariable(String startUser) {
        put("startUser", startUser);         // 初始化流程发起人
        put("passed", false);                // 初始化流程控制变量
        put("applicationForm", null);        // 初始化申请表单
    }
}

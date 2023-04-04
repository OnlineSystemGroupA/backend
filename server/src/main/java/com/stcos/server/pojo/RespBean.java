package com.stcos.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 响应的返回对象
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/29 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RespBean {

    /**
     * 状态码
     */
    private long code;

    /**
     * 提示信息，常规情况可忽略，让前端根据响应码自定义信息提示用户
     */
    private String message;

    /**
     * 需要包含的其他信息
     */
    private Object obj;

    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }

    public static RespBean success(String message, Object obj) {
        return new RespBean(200, message, obj);
    }

    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    public static RespBean error(String message, Object obj) {
        return new RespBean(500, message, obj);
    }

    public static RespBean unImplemented() {
        return new RespBean().setObj(700).setMessage("调用方法未被实现");
    }
}

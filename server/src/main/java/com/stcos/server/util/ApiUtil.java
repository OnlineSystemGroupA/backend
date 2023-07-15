package com.stcos.server.util;

import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * API 工具类，用于处理 API 相关的操作
 */
public class ApiUtil {
    /**
     * 设置示例响应
     *
     * @param req         原生 Web 请求
     * @param contentType 响应内容类型
     * @param example     示例内容
     */
    public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            assert res != null;
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            res.getWriter().print(example);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

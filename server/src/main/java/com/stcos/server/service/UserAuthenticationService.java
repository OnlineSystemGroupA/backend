package com.stcos.server.service;

import com.stcos.server.pojo.RespBean;

/**
 * 用户认证业务：
 * 注册、登录、登出。
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 21:01
 */
public interface UserAuthenticationService {

    default RespBean register(String username, String password, String email) {
        return RespBean.unImplemented();
    }

    default RespBean login(String username, String password) {
        return RespBean.unImplemented();
    }


    default RespBean logout() {
        return RespBean.unImplemented();
    }
}

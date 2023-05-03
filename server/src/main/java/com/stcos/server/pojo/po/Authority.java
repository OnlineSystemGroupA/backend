package com.stcos.server.pojo.po;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Authority implements GrantedAuthority {

    private int authorityType;
/**
 * 权限名称常量定义`
 */
    //#权限1#
    public static final int auth1=1;

    //#权限2#
    public static final int auth2=2;

    @Override
    public String getAuthority() {
        return null;
    }

}

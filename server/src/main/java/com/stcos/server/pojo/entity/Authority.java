package com.stcos.server.pojo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Authority implements GrantedAuthority {

    private  String authorityName;

    @Override
    public String getAuthority() {
        return authorityName;
    }
}

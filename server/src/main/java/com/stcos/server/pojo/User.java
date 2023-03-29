package com.stcos.server.pojo;

import lombok.*;

@Data
@AllArgsConstructor
public class User {
    private Long id;

    private String username;

    private String password;

}

package com.stcos.server.database;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 20:43
 */
public interface UserRepo {

    default UserDetails getUserByName(String username) {
        return null;
    };
}

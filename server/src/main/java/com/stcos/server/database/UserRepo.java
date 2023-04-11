package com.stcos.server.database;

import com.stcos.server.pojo.entity.User;
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
    }
    default boolean existUserName(String username){
        return false;
    }

    default void addNewUser(User newuser){
    }
}

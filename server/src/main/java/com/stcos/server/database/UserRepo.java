package com.stcos.server.database;

import com.stcos.server.pojo.po.TempUser;
import com.stcos.server.pojo.po.Customer;
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
    default boolean existUsername(String username){
        return false;
    }

    default void addNewUser(TempUser newuser){
    }
    default void addNewUser(Customer newuser){
    }
}

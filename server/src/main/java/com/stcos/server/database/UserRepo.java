package com.stcos.server.database;

import com.stcos.server.pojo.entity.TempUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 20:43
 */
public interface UserRepo extends CrudRepository<TempUser, String> {

    UserDetails getUserByUsername(String username);
}

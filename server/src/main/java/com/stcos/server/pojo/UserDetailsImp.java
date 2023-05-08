package com.stcos.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * UserDetailsImp
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/8 20:51
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserDetailsImp implements UserDetails {

    private String uid;

}

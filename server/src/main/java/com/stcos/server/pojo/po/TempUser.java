package com.stcos.server.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 21:55
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TempUser {

    String name;

    String password;

    String email;


}

package com.stcos.server.service.impl;

import com.stcos.server.database.UserRepo;
import com.stcos.server.pojo.RespBean;
import com.stcos.server.pojo.entity.TempUser;
import com.stcos.server.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 21:01
 */

@Component
public class UserAuthenticationServiceImp implements UserAuthenticationService {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public RespBean register(String username, String password, String email) {
        TempUser tempUser = new TempUser(username, password, email);

        if(userRepo.existUserName(tempUser.getName())) //存在对应用户名
            return new RespBean().setCode(601);

        //TODO:校验格式

        userRepo.addNewUser(tempUser);

        return new RespBean().setCode(600);
    }

    @Override
    public RespBean login(String username, String password) {

        UserDetails userDetails = userRepo.getUserByName(username);

        if(userDetails == null) //用户名不存在
            return new RespBean().setCode(601);

        else if (!userDetails.isEnabled()) //用户被禁用
            return new RespBean().setCode(602);

        else if (!userDetails.getPassword().equals(password))
            return new RespBean().setCode(603);

        else if (!userDetails.getPassword().equals(password))
            return new RespBean().setCode(604);

        //TODO:校验格式

        else return new RespBean().setCode(600);
    }

    @Override
    public RespBean logout() {
        return new RespBean().setCode(600);
    }
}

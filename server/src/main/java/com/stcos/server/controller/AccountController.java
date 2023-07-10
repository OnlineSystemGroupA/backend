package com.stcos.server.controller;

import com.stcos.server.controller.api.AccountApi;
import com.stcos.server.entity.dto.ClientDetailsDto;
import com.stcos.server.entity.dto.LockDto;
import com.stcos.server.entity.dto.OperatorDetailsDto;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.entity.user.User;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.AccountService;
import com.stcos.server.util.dto.ClientDetailsMapper;
import com.stcos.server.util.dto.OperatorDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
            ___                               __  ______            __             ____
           /   | ______________  __  ______  / /_/ ____/___  ____  / /__________  / / /__  _____
          / /| |/ ___/ ___/ __ \/ / / / __ \/ __/ /   / __ \/ __ \/ __/ ___/ __ \/ / / _ \/ ___/
         / ___ / /__/ /__/ /_/ / /_/ / / / / /_/ /___/ /_/ / / / / /_/ /  / /_/ / / /  __/ /
        /_/  |_\___/\___/\____/\__,_/_/ /_/\__/\____/\____/_/ /_/\__/_/   \____/_/_/\___/_/

 */

/**
 * 实现账户相关接口
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/2 13:46
 */
@RestController
public class AccountController implements AccountApi {

    private AccountService accountService;

    /*   AUTO_WIRED BEGIN  */
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    /*   AUTO_WIRED END    */

    /* 客户 */
    @Override
    public ResponseEntity<ClientDetailsDto> getClientDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof Client client) {
            // 根据当前登录用户的信息构造 ClientDetailsDto 对象
            ClientDetailsDto clientDetailsDto = ClientDetailsMapper.toClientDetailsDto(client);
            // 返回 HTTP 响应
            return ResponseEntity.ok(clientDetailsDto);
        } else {
            return ResponseEntity.status(409).build();
        }
    }

    @Override
    public ResponseEntity<String> updateClientDetails(ClientDetailsDto clientDetailsDto) {
        ResponseEntity<String> result = null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof Client client) {
            try {
                accountService.updateClientDetails(client, clientDetailsDto);
            } catch (ServiceException e) {
                switch (e.getCode()) {
                    case 0 -> result = ResponseEntity.ok("email");
                    case 1 -> result = ResponseEntity.ok("phone");
                }
            }
            if (result == null) {
                result = ResponseEntity.ok("");
            }
        } else {
            result = ResponseEntity.status(409).build();
        }
        return result;
    }

    /* 员工 */
    @Override
    public ResponseEntity<OperatorDetailsDto> getOperatorDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof Operator operator) {
            // 根据当前登录用户的信息构造 OperatorDetailsDto 对象
            OperatorDetailsDto operatorDetailsDto = OperatorDetailsMapper.toOperatorDetailsDto(operator);
            // 返回 HTTP 响应
            return ResponseEntity.ok(operatorDetailsDto);
        } else {
            return ResponseEntity.status(409).build();
        }
    }

    @Override
    public ResponseEntity<String> updateOperatorDetails(OperatorDetailsDto operatorDetailsDto) {
        ResponseEntity<String> result = null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof Operator operator) {
            try {
                accountService.updateOperatorDetails(operator, operatorDetailsDto);
            } catch (ServiceException e) {
                switch (e.getCode()) {
                    case 0 -> result = ResponseEntity.ok("email");
                    case 1 -> result = ResponseEntity.ok("phone");
                }
            }
            if (result == null) {
                result = ResponseEntity.ok("");
            }
        } else {
            result = ResponseEntity.status(409).build();
        }
        return result;
    }

    /* 主管 */
    @Override
    @Secured("ROLE_MANAGER")  // 只有主管可以调用该 API
    public ResponseEntity<List<OperatorDetailsDto>> getOperators() {
        Operator user = (Operator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Operator> operatorList = accountService.getOperatorsByDepartment(user.getDepartment());
        List<OperatorDetailsDto> ret = new ArrayList<>(operatorList.size());
        for (Operator operator : operatorList) {
            ret.add(OperatorDetailsMapper.toOperatorDetailsDto(operator));
        }
        return ResponseEntity.ok(ret);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<List<ClientDetailsDto>> getClients() {
        List<Client> clientList = accountService.getClients();
        List<ClientDetailsDto> ret = new ArrayList<>(clientList.size());
        for (Client client : clientList) {
            ret.add(ClientDetailsMapper.toClientDetailsDto(client));
        }
        return ResponseEntity.ok(ret);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<List<OperatorDetailsDto>> getOperatorsDepartment() {
        List<Operator> operatorList = accountService.getOperators();
        List<OperatorDetailsDto> ret = new ArrayList<>(operatorList.size());
        for (Operator operator : operatorList) {
            ret.add(OperatorDetailsMapper.toOperatorDetailsDto(operator));
        }
        return ResponseEntity.ok(ret);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<Void> createOperator(OperatorDetailsDto operatorDetailsDto) {
        ResponseEntity<Void> result = null;
        try {
            accountService.createOperator(operatorDetailsDto);
        } catch (ServiceException e) {
            if (e.getCode() == 0) {
                result = ResponseEntity.status(409).build();   //冲突
            }
        }
        if (result == null) {
            result = ResponseEntity.ok().build();
        }
        return result;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<ClientDetailsDto> adminGetClientDetails(String uid) {
        ResponseEntity<ClientDetailsDto> result = null;
        Client client = null;
        try {
            client = accountService.getClientById(uid);
        }catch (ServiceException e) {
            if (e.getCode() == 0) {
                result = ResponseEntity.status(404).build(); //用户不存在
            }
        }
        if(result == null && client != null){
            ClientDetailsDto clientDetailsDto = ClientDetailsMapper.toClientDetailsDto(client);
            result = ResponseEntity.ok(clientDetailsDto);
        }
        return result;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<OperatorDetailsDto> adminGetOperatorDetails(String uid) {
        ResponseEntity<OperatorDetailsDto> result = null;
        Operator operator = null;
        try {
            operator = accountService.getOperatorById(uid);
        }catch (ServiceException e) {
            if (e.getCode() == 0) {
                result = ResponseEntity.status(404).build(); //用户不存在
            }
        }
        if(result == null && operator != null){
            OperatorDetailsDto operatorDetailsDto = OperatorDetailsMapper.toOperatorDetailsDto(operator);
            result = ResponseEntity.ok(operatorDetailsDto);
        }
        return result;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<Void> deleteClient(String uid) {
        ResponseEntity<Void> result = null;
        try {
            accountService.deleteClient(uid);
        } catch (ServiceException e) {
            if (e.getCode() == 0) {
                result = ResponseEntity.status(404).build(); //用户不存在
            }
        }
        if (result == null) {
            result = ResponseEntity.ok().build();
        }
        return result;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<Void> deleteOperator(String uid) {
        ResponseEntity<Void> result = null;
        try {
            accountService.deleteOperator(uid);
        } catch (ServiceException e) {
            if (e.getCode() == 0) {
                result = ResponseEntity.status(404).build(); //用户不存在
            }
        }
        if (result == null) {
            result = ResponseEntity.ok().build();
        }
        return result;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<Void> updateClient(String uid, ClientDetailsDto clientDetailsDto) {
        ResponseEntity<Void> result = null;
        Client client = null;
        try {
            client = accountService.getClientById(uid);
        }catch (ServiceException e) {
            if (e.getCode() == 0) {
                result = ResponseEntity.status(404).build(); //用户不存在
            }
        }
        if(result == null){
            try {
                accountService.updateClientDetails(client, clientDetailsDto);
            } catch (ServiceException e) {
                switch (e.getCode()) {
                    case 0, 1 -> result = ResponseEntity.status(409).build();  //更新数据冲突
                }
            }
            if (result == null) {
                result = ResponseEntity.ok().build();
            }
        }
        return result;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<Void> updateOperator(String uid, OperatorDetailsDto operatorDetailsDto) {
        ResponseEntity<Void> result = null;
        Operator operator = null;
        try {
            operator = accountService.getOperatorById(uid);
        }catch (ServiceException e) {
            if (e.getCode() == 0) {
                result = ResponseEntity.status(404).build(); //用户不存在
            }
        }
        if(result == null){
            try {
                accountService.updateOperatorDetails(operator, operatorDetailsDto);
            } catch (ServiceException e) {
                switch (e.getCode()) {
                    case 0, 1 -> result = ResponseEntity.status(409).build();  //更新数据冲突
                }
            }
            if (result == null) {
                result = ResponseEntity.ok().build();
            }
        }
        return result;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<Void> lockOperator(String uid, LockDto lockDto) {
        ResponseEntity<Void> result = null;
        try {
            accountService.lockOperator(uid, lockDto.getDoLock());
        } catch (ServiceException e) {
            if (e.getCode() == 0) {
                result = ResponseEntity.status(404).build();   //用户不存在
            }
        }
        if (result == null) {
            result = ResponseEntity.ok().build();
        }
        return result;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<Void> lockClient(String uid, LockDto lockDto) {
        ResponseEntity<Void> result = null;
        try {
            accountService.lockClient(uid, lockDto.getDoLock());
        } catch (ServiceException e) {
            if (e.getCode() == 0) {
                result = ResponseEntity.status(404).build();   //用户不存在
            }
        }
        if (result == null) {
            result = ResponseEntity.ok().build();
        }
        return result;
    }

}

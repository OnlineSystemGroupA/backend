package com.stcos.server.controller;

import com.stcos.server.controller.api.AccountApi;
import com.stcos.server.entity.dto.ClientDetailsDto;
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
            ret.add(new OperatorDetailsDto(
                    operator.getUid(),
                    operator.getJobNumber(),
                    operator.getEmail(),
                    operator.getPhone(),
                    operator.getRealName(),
                    operator.getDepartment(),
                    operator.getPosition()
            ));
        }
        return ResponseEntity.ok(ret);
    }
}

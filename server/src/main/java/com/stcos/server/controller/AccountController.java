package com.stcos.server.controller;

import com.stcos.server.controller.api.AccountApi;
import com.stcos.server.entity.dto.ClientDetailsDto;
import com.stcos.server.entity.dto.OperatorDetailsDto;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.entity.user.User;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/2 13:46
 */

@RestController
public class AccountController implements AccountApi {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<ClientDetailsDto> getClientDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof Client client) {
            // 根据当前登录用户的信息构造 ClientDetailsDto 对象
            ClientDetailsDto clientDetailsDto = new ClientDetailsDto(
                    client.getUsername(),               // 用户名
                    client.getCreatedDate().toString(), // 账号创建时间
                    client.getRealName(),               // 用户的真实姓名
                    client.getEmail(),                  // 邮箱
                    client.getPhone(),                  // 联系电话
                    client.getGender(),                 // 性别
                    client.getCompany(),                // 公司名称
                    client.getCompanyTelephone(),       // 公司电话号
                    client.getCompanyFax(),             // 公司传真
                    client.getCompanyAddress(),         // 公司地址
                    client.getCompanyPostcode(),        // 公司邮编
                    client.getCompanyWebsite(),         // 公司网址
                    client.getCompanyEmail(),           // 公司邮箱
                    client.getCompanyPhone()            // 公司手机号
            );
            // 返回 HTTP 响应
            return ResponseEntity.ok(clientDetailsDto);
        } else {
            return ResponseEntity.status(409).build();
        }
    }

    @Override
    @Secured("ROLE_MANAGER") // 只有主管可以调用该 API
    public ResponseEntity<List<OperatorDetailsDto>> getOperators() {
        Operator user = (Operator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Operator> operatorList = accountService.getOperatorsByDepartment(user.getDepartment());
        List<OperatorDetailsDto> ret = new ArrayList<>(operatorList.size());
        for (Operator operator : operatorList) {
            ret.add(new OperatorDetailsDto(
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
}

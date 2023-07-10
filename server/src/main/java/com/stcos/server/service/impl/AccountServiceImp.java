package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.AdminMapper;
import com.stcos.server.database.mysql.ClientMapper;
import com.stcos.server.database.mysql.OperatorMapper;
import com.stcos.server.entity.dto.ClientDetailsDto;
import com.stcos.server.entity.dto.LockDto;
import com.stcos.server.entity.dto.OperatorDetailsDto;
import com.stcos.server.entity.user.Admin;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.Operator;
import com.stcos.server.service.AccountService;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.ClientService;
import com.stcos.server.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    private OperatorService operatorService;

    @Autowired
    public void setOperatorService(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin getAdmin(String username) throws ServiceException {
        Admin admin = adminMapper.getByUsernameAdmin(username);
        if (admin == null) {
            throw new ServiceException(0); // Admin not found
        }
        return admin;
    }

    @Override
    public Client getClient(String username) throws ServiceException {
        Client client = clientService.getByUsername(username);
        if (client == null) {
            throw new ServiceException(0); // Client not found
        }
        return client;
    }

    @Override
    public Operator getOperator(String jobNumber) throws ServiceException {
        Operator operator = operatorService.getByJobNumber(jobNumber);
        if (operator == null) {
            throw new ServiceException(0); // Operator not found
        }
        return operator;
    }

    @Override
    public void register(String username, String password, String email) throws ServiceException {
        Client client = clientService.getByUsername(username);
        if (client != null)
            throw new ServiceException(0);
        client = new Client(username, passwordEncoder.encode(password), email);
        clientService.register(client);
    }

    @Override
    public String getTestingManagerId() {
        return null;
    }

    @Override
    public String getMarketingManagerId() {
        return null;
    }

    @Override
    public String getQualityManagerId() {
        return null;
    }

    @Override
    public void updateClientDetails(Client client, ClientDetailsDto clientDetailsDto) throws ServiceException {
        if (clientService.existEmail(clientDetailsDto.getEmail(), client.getUid())) throw new ServiceException(0);
        if (clientService.existPhone(clientDetailsDto.getPhone(), client.getUid())) throw new ServiceException(1);
        client.setRealName(clientDetailsDto.getRealName());
        client.setCompany(clientDetailsDto.getCompany());
        client.setCompanyEmail(clientDetailsDto.getCompanyEmail());
        client.setCompanyAddress(clientDetailsDto.getCompanyAddress());
        client.setCompanyFax(clientDetailsDto.getCompanyFax());
        client.setCompanyPhone(clientDetailsDto.getCompanyPhone());
        client.setEmail(clientDetailsDto.getEmail());
        client.setGender(clientDetailsDto.getGender());
        client.setPhone(clientDetailsDto.getPhone());
        clientService.updateById(client);
    }

    @Override
    public List<Operator> getOperatorsByDepartment(String department) {
        return operatorService.getByDepartment(department);
    }

    @Override
    public void updateOperatorDetails(Operator operator, OperatorDetailsDto operatorDetailsDto) throws ServiceException {
        if (operatorService.existEmail(operatorDetailsDto.getEmail(), operator.getUid())) throw new ServiceException(0);
        if (operatorService.existPhone(operatorDetailsDto.getPhone(), operator.getUid())) throw new ServiceException(1);
        operator.setRealName(operatorDetailsDto.getRealName());
        operator.setDepartment(operatorDetailsDto.getDepartment());
        operator.setPosition(operatorDetailsDto.getPosition());
        operator.setEmail(operatorDetailsDto.getEmail());
        operator.setPhone(operatorDetailsDto.getPhone());
        operatorService.updateById(operator);
    }

    @Override
    public void lockOperator(String uid, LockDto lockDto) throws ServiceException {

    }

    @Override
    public void lockClient(String uid, LockDto lockDto) throws ServiceException {

    }
}

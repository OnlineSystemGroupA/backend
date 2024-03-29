package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.AdminMapper;
import com.stcos.server.model.dto.ClientDetailsDto;
import com.stcos.server.model.dto.OperatorDetailsDto;
import com.stcos.server.model.user.Admin;
import com.stcos.server.model.user.Client;
import com.stcos.server.model.user.Operator;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.AccountService;
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
        client.setRealName(clientDetailsDto.getRealName());
        client.setCompany(clientDetailsDto.getCompany());
        client.setCompanyEmail(clientDetailsDto.getCompanyEmail());
        client.setCompanyAddress(clientDetailsDto.getCompanyAddress());
        client.setCompanyFax(clientDetailsDto.getCompanyFax());
        client.setCompanyTelephone(clientDetailsDto.getCompanyTelephone());
        client.setCompanyPostcode(clientDetailsDto.getCompanyPostcode());
        client.setCompanyWebsite(clientDetailsDto.getCompanyWebsite());
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
        operator.setRealName(operatorDetailsDto.getRealName());
        operator.setDepartment(operatorDetailsDto.getDepartment());
        operator.setPosition(operatorDetailsDto.getPosition());
        operator.setEmail(operatorDetailsDto.getEmail());
        operator.setPhone(operatorDetailsDto.getPhone());
        operatorService.updateById(operator);
    }

    @Override
    public void lockOperator(String uid, Boolean doLock) throws ServiceException {

    Operator operator = operatorService.getById(uid);

        if(operator == null) throw new ServiceException(0);

        operator.setAccountNonLocked(!doLock);

        operatorService.updateById(operator);

    }

    @Override
    public void lockClient(String uid, Boolean doLock) throws ServiceException {
        Client client = clientService.getById(uid);

        if(client == null) throw new ServiceException(0);

        client.setAccountNonLocked(!doLock);

        clientService.updateById(client);
    }

    @Override
    public Client getClientById(String uid) throws ServiceException {
        Client client = clientService.getById(uid);

        if(client == null) throw new ServiceException(0);

        return client;
    }

    @Override
    public Operator getOperatorById(String uid) throws ServiceException {
        Operator operator = operatorService.getById(uid);

        if(operator == null) throw new ServiceException(0);

        return operator;
    }

    @Override
    public List<Operator> getOperators() {
        return operatorService.getAll();
    }

    @Override
    public List<Client> getClients() {
        return clientService.getAll();
    }

    @Override
    public void createOperator(OperatorDetailsDto operatorDetailsDto) throws ServiceException {
        Operator operator = operatorService.getByJobNumber(operatorDetailsDto.getJobNumber());
        if (operator != null)
            throw new ServiceException(0);
        operator = new Operator();
        operator.setPassword(passwordEncoder.encode("123456"));
        operator.setJobNumber(operatorDetailsDto.getJobNumber());
        operator.setJobNumber(operatorDetailsDto.getJobNumber());
        operator.setRealName(operatorDetailsDto.getRealName());
        operator.setDepartment(operatorDetailsDto.getDepartment());
        operator.setPosition(operatorDetailsDto.getPosition());
        operator.setEmail(operatorDetailsDto.getEmail());
        operator.setPhone(operatorDetailsDto.getPhone());
        operator.setAccountNonLocked(operatorDetailsDto.getIsNonLocked());
        operator.setAccountNonExpired(true);
        operator.setCredentialsNonExpired(true);
        operator.setEnabled(true);
        operatorService.createOperator(operator);
    }

    @Override
    public void deleteClient(String uid) throws ServiceException {
        Client client = clientService.getById(uid);

        if(client == null) throw new ServiceException(0);

        clientService.deleteClient(client);
    }

    @Override
    public void deleteOperator(String uid) throws ServiceException {
        Operator operator = operatorService.getById(uid);

        if(operator == null) throw new ServiceException(0);

        operatorService.deleteOperator(operator);
    }
}

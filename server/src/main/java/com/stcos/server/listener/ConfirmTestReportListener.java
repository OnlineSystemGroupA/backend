package com.stcos.server.listener;

import com.stcos.server.entity.user.Client;
import com.stcos.server.mapper.ClientMapper;
import com.stcos.server.service.EmailService;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ”用户确认测试报告“任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/18 15:04
 */

@Component
public class ConfirmTestReportListener implements TaskListener{
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    private ClientMapper clientMapper;

    @Autowired
    public void setClientMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    public void create(DelegateTask task) {
        //发送邮件
        Client client = clientMapper.getByUidClient(task.getAssignee());
        if(client == null)
            return ;
        String subject = "确认测试报告";
        String text = "您好！您的一份测试委托的报告已经生成，请尽快前往确认。";
        emailService.sendEmail(client.getEmail(), subject, text);
    }
}

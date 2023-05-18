package com.stcos.server.listener;

import com.stcos.server.entity.user.Client;
import com.stcos.server.mapper.ClientMapper;
import com.stcos.server.service.EmailService;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * “用户修改委托”任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/18 14:44
 */

@Component
public class ModifyApplicationListener implements TaskListener{
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

    @Override
    public void create(DelegateTask task) {
        //发送邮件
        Client client = clientMapper.getByUidClient(task.getAssignee());
        if(client == null)
            return ;
        String subject = "修改委托";
        //String text = "您好！一项由您提起的软件测试委托经审核没有通过，请您修改委托并重新提交。";
        String text = client.getUsername() + ",你醒啦？该干活了";
        emailService.sendEmail(client.getEmail(), subject, text);
    }
}

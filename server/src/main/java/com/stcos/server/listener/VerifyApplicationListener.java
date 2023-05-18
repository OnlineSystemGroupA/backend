package com.stcos.server.listener;

import com.stcos.server.entity.user.Operator;
import com.stcos.server.mapper.OperatorMapper;
import com.stcos.server.service.EmailService;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * "审核用户委托"任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/18 14:41
 */

@Component
public class VerifyApplicationListener implements TaskListener{
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    private OperatorMapper operatorMapper;
    @Autowired
    public void setOperatorMapper(OperatorMapper operatorMapper) {
        this.operatorMapper = operatorMapper;
    }

    @Override
    public void create(DelegateTask task) {
        //发送邮件
        Operator operator = operatorMapper.getByUidOperator(task.getAssignee());
        if(operator == null)
            return ;
        String subject = "审核用户委托";
        //String text = "您好！一项被指派给您的\"审核用户委托\"任务已被创建，请尽快完成！";
        String text = operator.getUsername() + ",你醒啦？该干活了";
        emailService.sendEmail(operator.getEmail(), subject, text);
    }
}

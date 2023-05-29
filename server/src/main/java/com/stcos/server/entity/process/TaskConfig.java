package com.stcos.server.entity.process;

import com.stcos.server.entity.email.EmailContent;
import org.flowable.task.api.Task;

import java.util.List;
import java.util.Map;

/**
 * 任务配置相关的虚基类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/19 16:45
 */
public abstract class TaskConfig {

    /**
     * 邮件内容对象
     */
    private final EmailContent emailContent;

    /**
     * 一般路过构造方法
     */
    public TaskConfig(String emailSubject, String emailTemplate) {
        this.emailContent = new EmailContent(emailSubject, emailTemplate);
    }

    /**
     * 判断当前任务是否满足完成条件
     *
     * @param task 当前任务对象
     * @return ture 表示可被完成，否则不满足任务完成条件，不可被完成
     */
    public abstract boolean isCompletable(Task task);

    /**
     * 获取当前任务阶段需要填写的表单
     *
     * @return 需要填写表单的列表
     */
    public abstract List<String> getRequiredForms();

    /**
     * 获取当前任务需要发送的通知邮件的标题
     *
     * @return 邮件标题
     */
    public final String getEmailSubject() {
        return emailContent.getEmailSubject();
    }

    /**
     * 获取当前任务需要发送的通知邮件的正文
     *
     * @param paramMap 替换模板占位符的参数
     * @return 邮件正文
     */
    public final String getEmailText(Map<String, String> paramMap) {
        return emailContent.getEmailText(paramMap);
    }

}

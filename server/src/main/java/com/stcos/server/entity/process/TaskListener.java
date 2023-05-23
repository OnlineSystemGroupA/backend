package com.stcos.server.entity.process;

import com.stcos.server.entity.form.FormIndex;
import com.stcos.server.database.mysql.FormMapper;
import com.stcos.server.service.EmailService;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/22 19:08
 */
@Component
public class TaskListener {

    private  Map<String, TaskConfig> taskConfigMap;

    @Autowired
    public void setTaskConfigMap(Map<String, TaskConfig> taskConfigMap) {
        this.taskConfigMap = taskConfigMap;
    }

    private FormMapper formMapper;

    @Autowired
    public void setFormMapper(FormMapper formMapper) {
        this.formMapper = formMapper;
    }

    private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }


    public void create(DelegateTask task){
        /*TODO 重置任务参数
               判断是否需要为被分配人开启样品的读或写权限
               发送邮箱通知被分配人
         */
        TaskConfig taskConfig = taskConfigMap.get(task.getName());

        //重置任务参数
        task.setVariable("passable", true);
        task.setVariable("description", null);
        //更新流程摘要和流程详情
        task.setVariable("currentTask", task.getName());

        //如果没有创建表单索引，则创建它
        List<String> requiredForms = taskConfig.getRequiredForms();
        for (String requiredForm : requiredForms) {
            if(task.getVariable(requiredForm) == null){
                FormIndex formIndex = new FormIndex();
                formMapper.saveFormIndex(formIndex);
                task.setVariable(requiredForm, formIndex.getFormIndexId());
            }
        }

        //为被分配人开启对应的表单的读或写权限
        List<String> readableForms = taskConfig.getReadableForms();
        for (String readableForm: readableForms) {
            Long formIndexId = (Long) task.getVariable(readableForm);
            FormIndex formIndex = formMapper.selectByFormIndexId(formIndexId);
            formIndex.getReadableUsers().add(task.getAssignee());
        }
        List<String> writableForms = taskConfig.getWritableForms();
        for (String writableForm: writableForms) {
            Long formIndexId = (Long) task.getVariable(writableForm);
            FormIndex formIndex = formMapper.selectByFormIndexId(formIndexId);
            formIndex.getReadableUsers().add(task.getAssignee());
        }


    }

    public void assignment(DelegateTask task){

    }

    public void complete(DelegateTask task){
        /*TODO 关闭被分配人对应表单的读/写权限
               判断是否需要关闭被分配人对样品的读或写权限
               更新流程摘要和流程详情
        */
//        task.
    }

    public void delete(DelegateTask task){

    }
}

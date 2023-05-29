package com.stcos.server.entity.process;

import com.stcos.server.database.mongo.FormMetadataRepository;
import com.stcos.server.entity.form.FormMetadata;
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

    private Map<String, TaskConfig> taskConfigMap;

    @Autowired
    public void setTaskConfigMap(Map<String, TaskConfig> taskConfigMap) {
        this.taskConfigMap = taskConfigMap;
    }

    private FormMetadataRepository formMetadataRepository;

    @Autowired
    public void setFromIndexRepository(FormMetadataRepository formMetadataRepository) {
        this.formMetadataRepository = formMetadataRepository;
    }

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }


    public void create(DelegateTask task){
        /*TODO 其他要生成的变量（如合同）
               判断是否需要为被分配人开启样品的读或写权限
               发送邮箱通知被分配人
         */
        TaskConfig taskConfig = taskConfigMap.get(task.getName());

        //重置任务参数
        task.setVariable("passable", true);
        task.setVariable("description", null);
        //更新流程摘要和流程详情
        task.setVariable("currentTask", task.getName());

        //为被分配人开启对应的表单的读或写权限
        List<String> readableForms = taskConfig.getReadableForms();
        for (String readableForm: readableForms) {
            Long formMetadataId = (Long) task.getVariable(readableForm);
            FormMetadata formMetadata = formMetadataRepository.findByFormMetadataId(formMetadataId);
            formMetadata.getReadableUsers().add(task.getAssignee());
        }
        List<String> writableForms = taskConfig.getWritableForms();
        for (String writableForm: writableForms) {
            Long formMetadataId = (Long) task.getVariable(writableForm);
            FormMetadata formMetadata = formMetadataRepository.findByFormMetadataId(formMetadataId);
            formMetadata.getReadableUsers().add(task.getAssignee());
        }


    }

    public void assignment(DelegateTask task){

    }

    public void complete(DelegateTask task){
        /*TODO
               判断是否需要关闭被分配人对样品的读或写权限
        */
        TaskConfig taskConfig = taskConfigMap.get(task.getName());

        List<String> willDisReadableForms = taskConfig.getWillDisReadableForms();
        for (String willDisReadableForm: willDisReadableForms) {
            Long formIndexId = (Long) task.getVariable(willDisReadableForm);
            FormIndex formIndex = formMapper.selectByFormIndexId(formIndexId);
            formIndex.getReadableUsers().remove(task.getAssignee());
        }
        List<String> willDisWritableForms = taskConfig.getWillDisWritableForms();
        for (String willDisWritableForm: willDisWritableForms) {
            Long formIndexId = (Long) task.getVariable(willDisWritableForm);
            FormIndex formIndex = formMapper.selectByFormIndexId(formIndexId);
            formIndex.getReadableUsers().remove(task.getAssignee());
        }

        //更新流程摘要和流程详情
        task.setVariable("currentTask", "无");
    }

    public void delete(DelegateTask task){

    }
}

package com.stcos.server.listener;

import com.stcos.server.entity.file.SampleMetadata;
import com.stcos.server.entity.process.TaskName;
import com.stcos.server.service.SampleMetadataService;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:20
 */

@Component
public class UploadSampleListener extends ClientTaskListener {

    private SampleMetadataService sampleMetadataService;

    @Autowired
    public void setSampleMetadataService(SampleMetadataService sampleMetadataService) {
        this.sampleMetadataService = sampleMetadataService;
    }

    public UploadSampleListener() {
        super(TaskName.NAME_TASK_19);
    }



    @Override
    public void create(DelegateTask task) {
        super.create(task);

        // 为用户开启样品上传权限
        Long sampleMetadataId = (Long) task.getVariable("sampleMetadata");
        sampleMetadataService.addWritePermission(sampleMetadataId, task.getAssignee());
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);

        // 关闭样品上传权限，开启所有用户对样品的下载权限

    }
}

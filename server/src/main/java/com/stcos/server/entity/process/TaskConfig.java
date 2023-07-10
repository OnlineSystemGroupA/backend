package com.stcos.server.entity.process;

import com.stcos.server.entity.email.EmailContent;
import com.stcos.server.service.FormService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 任务配置相关的虚基类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/19 16:45
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class TaskConfig {

    private Set<String> requiredForms;

    private Set<String> requiredParticipants;

    /**
     * 判断当前任务是否满足完成条件
     *
     * @param task 当前任务对象
     * @return true 表示可被完成，否则不满足任务完成条件，不可被完成
     */
    public boolean isCompletable(Task task, FormService formService) {
        Map<String, Object> processVariables = task.getProcessVariables();
        for (String requiredForm : requiredForms) {
            if (!formService.existForm((Long) processVariables.get(requiredForm)))
                return false;
        }
        for (String requiredParticipant : requiredParticipants) {
            if (processVariables.get(requiredParticipant) == null) return false;
        }
        return true;
    }
}

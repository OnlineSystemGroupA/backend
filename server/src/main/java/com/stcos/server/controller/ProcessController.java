package com.stcos.server.controller;

import com.stcos.server.controller.api.ProcessApi;
import com.stcos.server.pojo.dto.TaskDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.flowable.engine.RuntimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/23 19:36
 */

@RestController
@Tag(name = "ProcessController")
public class ProcessController implements ProcessApi {

//    private TaskService taskService;
//
//    private RuntimeService runtimeService;
//
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Autowired
//    public void setTaskService(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @Autowired
//    public void setRuntimeService(RuntimeService runtimeService) {
//        this.runtimeService = runtimeService;
//    }
//
//    @PostMapping("/start")
//    public RespBean startProcess(@RequestBody String text, @Parameter(hidden = true) Principal principal) {
//        Map<String, Object> variables = new HashMap<>();
//        String uuid = ((Customer) userDetailsService.loadUserByUsername(principal.getName())).getUid();
//        variables.put("text", text);
//        variables.put("user", uuid);
//        variables.put("admin", "admin-742cec45-0ae5-4965-93c9-150ba49bbb32");
//        runtimeService.startProcessInstanceByKey("workflow", variables);
//        return new RespBean().setCode(600).setMessage("成功开启业务流程");
//    }
//
//    @PostMapping("/complete-task")
//    public RespBean completeTask(@RequestParam(name = "id") String id, @RequestBody Map<String, Object> variables) {
//        Task task = taskService.createTaskQuery().taskId(id).singleResult();
//        Map<String, Object> processVariables = task.getProcessVariables();
//        processVariables.putAll(variables);
//        taskService.complete(id, processVariables);
//        return new RespBean().setCode(600);
//    }
//
//    @GetMapping("/tasks")
//    public RespBean getTasks(@Parameter(hidden = true) Principal principal) {
//        List<Task> tasks = taskService.createTaskQuery().taskAssignee(principal.getName()).list();
//        RespBean respBean = new RespBean();
//        if (tasks == null) {
//            respBean.setCode(601).setMessage("未查询到相关任务");
//        } else {
//            respBean.setCode(600).setMessage("成功查询到相关任务").setObj(tasks);
//        }
//        return respBean;
//    }


    @Override
    public ResponseEntity<Void> completeTask(String taskId) {
        return ProcessApi.super.completeTask(taskId);
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTaskById(String taskId) {
        return ProcessApi.super.getTaskById(taskId);
    }

    @Override
    public ResponseEntity<Object> getTaskItem(String taskId, String itemName) {
        return ProcessApi.super.getTaskItem(taskId, itemName);
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasks() {
        return ProcessApi.super.getTasks();
    }

    @Override
    public ResponseEntity<String> startProcess() {
        return ProcessApi.super.startProcess();
    }

    @Override
    public ResponseEntity<Void> updateTaskItem(String taskId, String itemName) {
        return ProcessApi.super.updateTaskItem(taskId, itemName);
    }
}

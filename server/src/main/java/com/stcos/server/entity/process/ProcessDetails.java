package com.stcos.server.entity.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/20 14:09
 */

@Data
@Accessors(chain = true)
public class ProcessDetails {
    @TableId
    private Long projectId;

    private String title = "";

    private String version = "";

    private String testType = "";

    private String applicationDate = "";

    private String applicant = "";

    private String company = "";

    private String telephone = "";

    private String email = "";

    private String address = "";

    private LocalDateTime startDate;

    private LocalDateTime dueDate;

    @TableField(exist = false)
    List<TaskDetails> taskDetailsList;

    public ProcessDetails() {
        taskDetailsList = new ArrayList<>();
    }

    public void openTask(String taskName, String userName) {
        for (TaskDetails taskDetails : taskDetailsList) {
            if (Objects.equals(taskDetails.getTaskName(), taskName)) {
                taskDetails.setOperator(userName);
                taskDetails.setStartDate(LocalDateTime.now());
                taskDetails.setFinishDate(null);
                return;
            }
        }
        TaskDetails taskDetails = new TaskDetails();
        taskDetails.setProcessId(projectId)
                .setTaskName(taskName)
                .setOperator(userName)
                .setStartDate(LocalDateTime.now());

        taskDetailsList.add(new TaskDetails());
    }

    public void closeTask(String taskName) {
        for (TaskDetails taskDetails : taskDetailsList) {
            if (Objects.equals(taskDetails.getTaskName(), taskName)) {
                taskDetails.setFinishDate(LocalDateTime.now());
                return;
            }
        }
    }

    public void update(String softwareName,
                       String softwareVersion,
                       String testType,
                       String startDate,
                       String companyChineseName,
                       String email,
                       String address) {
        this.title = softwareName;
        this.version = softwareVersion;
        this.testType = testType;
        this.applicationDate = startDate;
        this.company = companyChineseName;
        this.email = email;
        this.address = address;
    }
}

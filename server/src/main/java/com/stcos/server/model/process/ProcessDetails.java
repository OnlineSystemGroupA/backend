package com.stcos.server.model.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 这个服务接口提供了管理流程细节的方法
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/20 14:09
 */
@Data
@Accessors(chain = true)
public class ProcessDetails {

    /**
     * 流程项目的 ID
     */
    @TableId
    private Long projectId;

    /**
     * 流程项目的标题
     */
    private String title = "";

    /**
     * 流程项目的版本
     */
    private String version = "";

    /**
     * 流程项目的测试类型
     */
    private String testType = "";

    /**
     * 流程项目的申请日期
     */
    private String applicationDate = "";

    /**
     * 流程项目的申请人
     */
    private String applicant = "";

    /**
     * 流程项目所属的公司
     */
    private String company = "";

    /**
     * 流程项目联系人的电话
     */
    private String telephone = "";

    /**
     * 流程项目联系人的电子邮箱
     */
    private String email = "";

    /**
     * 流程项目联系人的地址
     */
    private String address = "";

    /**
     * 流程项目的开始日期
     */
    private LocalDateTime startDate;

    /**
     * 流程项目的截止日期
     */
    private LocalDateTime dueDate;

    /**
     * 流程项目中的任务列表
     */
    @TableField(exist = false)
    List<TaskDetails> taskDetailsList;

    public ProcessDetails() {
        taskDetailsList = new ArrayList<>();
    }

    /**
     * 打开新任务或者重新打开已经存在的任务
     *
     * @param taskName 任务名称
     * @param userName 操作用户的名称
     */
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
        taskDetailsList.add(taskDetails);
    }

    /**
     * 关闭一个已经存在的任务
     *
     * @param taskName 任务名称
     */
    public void closeTask(String taskName) {
        for (TaskDetails taskDetails : taskDetailsList) {
            if (Objects.equals(taskDetails.getTaskName(), taskName)) {
                taskDetails.setFinishDate(LocalDateTime.now());
                return;
            }
        }
    }

    /**
     * 更新流程的详细信息
     *
     * @param softwareName 软件名称
     * @param softwareVersion 软件版本
     * @param testType 测试类型
     * @param startDate 开始日期
     * @param companyChineseName 公司中文名
     * @param email 联系邮箱
     * @param address 地址
     * @param startUser 启动用户
     * @param telephone 电话
     */
    public void update(String softwareName,
                       String softwareVersion,
                       String testType,
                       String startDate,
                       String companyChineseName,
                       String email,
                       String address, String startUser, String telephone) {
        this.title = softwareName;
        this.version = softwareVersion;
        this.testType = testType;
        this.applicationDate = startDate;
        this.company = companyChineseName;
        this.email = email;
        this.address = address;
        this.applicant = startUser;
        this.telephone = telephone;
    }

    /**
     * 获取当前的任务名称
     *
     * @return 返回当前任务的名称
     */
    public String getCurrentTaskName() {
        TaskDetails taskDetails = taskDetailsList.get(taskDetailsList.size() - 1);
        return taskDetails.getTaskName();
    }
}

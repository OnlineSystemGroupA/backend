package com.stcos.server.model.process;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 该类用于表示一个任务的详细信息，包含任务 ID，所属的流程 ID，任务名称，执行者，开始和结束时间等
 * 注意，其中的 "department"，"result" 和 "description" 字段已被弃用
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/20 14:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TaskDetails {
    /**
     * 任务 ID
     */
    @TableId
    private Long taskId;

    /**
     * 所属流程的 ID
     */
    private Long processId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 部门名称，此字段已被弃用
     */
    @Deprecated
    private String department = "";

    /**
     * 任务执行者
     */
    private String operator;

    /**
     * 任务开始时间
     */
    private LocalDateTime startDate;

    /**
     * 任务结束时间
     */
    private LocalDateTime finishDate;

    /**
     * 任务结果，此字段已被弃用
     */
    @Deprecated
    private boolean result = true;

    /**
     * 任务描述，此字段已被弃用
     */
    @Deprecated
    private String description = "";
}

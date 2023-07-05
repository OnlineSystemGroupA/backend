package com.stcos.server.entity.process;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * description
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
    @TableId
    private Long id;

    private Long processId;

    private String taskName;

    @Deprecated
    private String department = "";

    private String operator;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    @Deprecated
    private boolean result = true;

    @Deprecated
    private String description = "";
}

package com.stcos.server.entity.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/20 14:09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDetails {
    @TableId
    private Long id;

    private String title;

    private String version;

    private String testType;

    private String applicationDate;

    private String applicant;

    private String company;

    private String telephone;

    private String email;

    private String address;

    private LocalDateTime startDate;

    private LocalDateTime dueDate;

    @TableField(exist = false)
    List<TaskDetails> taskDetailsList;

}

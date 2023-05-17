package com.stcos.server.entity.form;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 表单的索引类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 9:51
 */
@Data
public class FormIndex {

    /**
     * 表单索引 ID
     */
    private Long formIndexId;

    /**
     * 表单索引对应表单的 ID
     */
    private Long formId;

    /**
     * 表单名称
     */
    private String formName;

    /**
     * 表单在哪个任务中被创建，对应的任务名
     */
    private String createdIn;

    /**
     * 表单的创建者
     */
    private String createdBy;

    /**
     * 表单创建时间
     */
    private LocalDateTime createdDate;

    /**
     * 表单最后一次被谁修改
     */
    private String lastModifiedBy;

    /**
     * 表单最后一次被修改的时间
     */
    private LocalDateTime lastModifiedDate;

    /**
     * 对表单具有读权限的用户
     */
    private List<String> readableUsers;

    /**
     * 对表单具有写权限的用户
     */
    private List<String> writableUsers;

    /**
     * 表单索引对应的表单实体，懒加载
     */
    private Form form;


}

package com.stcos.server.entity.form;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 表单的索引类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 9:51
 */
public class FormIndex {

    private String processInstanceId;

    private Long processArchivedId;

    /**
     * 在哪个任务阶段被创建
     */
    private String createdIn;

    /**
     * 是否归档
     */
    private boolean isArchived;

    private String createdBy;

    private LocalDateTime createdDateTime;


    private Long lastModifiedBy;

    private LocalDateTime lastModifiedDate;

    private String formName;

    /**
     * 表单创建的时间
     */
    private Date createdDate;


    /**
     * 表单索引对应的表单实体，懒加载
     */
    private Form form;


    public Form getForm() {
        return form;
    }





}

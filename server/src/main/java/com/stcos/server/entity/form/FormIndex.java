package com.stcos.server.entity.form;

import lombok.Data;

import java.time.LocalDateTime;

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
    private String formIndexId;

    /**
     * 表单索引对应表单的 ID
     */
    private String formId;

    /**
     * 表单名称
     */
    private String formName;

    /**
     * 表单对应的流程实例的 ID
     */
    private String processInstanceId;

    /**
     * 表单对应的流程记录的 ID
     */
    private Long processArchiveId;

    /**
     * 表单是否归档，归档之后的表单，请使用 processArchiveId 获取关联流程的归档记录 <br>
     * 否则使用 processInstanceId 查询与其关联的流程实例
     */
    private boolean archived;

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
     * 表单索引对应的表单实体，懒加载
     */
    private Form form;


}

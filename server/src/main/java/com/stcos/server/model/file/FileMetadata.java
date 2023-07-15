package com.stcos.server.model.file;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@TableName("t_file_metadata")
public class FileMetadata {
    /**
     * 文件元数据 ID，保存对象时由数据库自动赋值
     */
    @TableId(value = "file_metadata_id", type = IdType.ASSIGN_ID)
    private Long fileMetadataId = null;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName = null;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType = null;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private Long fileSize = null;

    /**
     * 文件上传者 (userId)
     */
    @TableField("updated_by")
    private String updatedBy = null;

    /**
     * 文件上传时间
     */
    @TableField("updated_date")
    private LocalDateTime updatedDate = null;

    /**
     * 文件在服务器磁盘上的路径
     */
    @TableField("file_path")
    private String filePath = null;

    public FileMetadata(String fileName, String fileType, Long fileSize, String updatedBy, LocalDateTime updatedDate, String filePath) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.filePath = filePath;
    }
}
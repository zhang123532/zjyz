package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("extra_file")
public class ExtraFileEntity {
    private String extraFileId;
    private String documentId;
    private String fileName;
    private String fileUrl;
}

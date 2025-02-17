package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
@TableName("extra_file")
public class ExtraFileEntity {
    @TableId
    private String extraFileId;
    private String documentId;
    private String fileName;
    private String fileUrl;
}

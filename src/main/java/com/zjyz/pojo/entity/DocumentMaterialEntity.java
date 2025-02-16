package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("document_material")
@Data
public class DocumentMaterialEntity {
    @TableId
    private String materialId;
    private String documentId;
    private Integer materialNumber;
    private String note;
    private String materialName;
    private String materialUnit;
    private String materialType;
    private Double dailyRent;
    private Double monthlyRent;
    //单据类型
    private String type;
}
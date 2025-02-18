package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("compensation_document")
public class CompensationDocumentEntity {
    // 主键 compensation_id
    @TableId
    private String compensationDocumentId;

    private String projectId;

    private String compensationDocumentName;

    private String compensationDate;

    private String rentalStation;

    private String personInCharge;

    private String auditor;

    private String note;
    private String createDate;

}

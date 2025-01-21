package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("return_document")
public class ReturnDocumentEntity {

    @TableId
    private String returnDocumentId;

    private String projectId;

    private String returnDocumentName;

    private String returnDate;

    private String rentalStation;

    private String personInCharge;

    private String auditor;

    private String note;
    private String createDate;

}

package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("rent_document")
public class RentDocumentEntity {

    @TableId
    private String rentDocumentId;

    private String projectId;

    private String rentDocumentName;

    private String rentDate;

    private String rentalStation;

    private String personInCharge;

    private String auditor;

    private String note;

    private String createDate;
}

package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@TableName("contract")
public class ContractEntity {
    @TableId
    private String contractId;
    private String projectId;
    private String contractName;
    private String startDate;
    private String endDate;
    private String rentalStation;
    private String companyName;
    private String customerName;
    private Double taxRate;
    private String operator;
    private String settlementUnit;
    private Integer headOrEnd;
    private Integer upperLimitReminder;
    private Integer reconciliationPeriod;
    private Boolean enableExpandPeriodFlag;
}

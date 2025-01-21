package com.zjyz.pojo.param.req;

import com.zjyz.pojo.param.bo.DocumentMaterialInfo;
import com.zjyz.pojo.param.bo.SettlementCycle;
import lombok.Data;

import java.util.List;
@Data
public class SaveContractParam {
    private String projectId;
    private String contractId;
    private String contractName;
    private String startDate;
    private String endDate;
    private String rentalStation;
    private String companyName;
    private String customerName;
    private Double taxRate;
    private String operator;
    private String settlementUnit;
    private SettlementCycle settlementCycle;
    private List<DocumentMaterialInfo> materialContractInfoList;
}

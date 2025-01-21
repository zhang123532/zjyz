package com.zjyz.pojo.param.ret;

import com.zjyz.pojo.entity.DocumentMaterialEntity;
import com.zjyz.pojo.param.bo.DocumentMaterialInfo;
import com.zjyz.pojo.param.bo.SettlementCycle;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContractInfoRet {
    private String contractId;
    private String projectId;
    private String contractName;
    private String startTime;
    private String endTime;
    private String rentalStation;
    private String companyName;
    private String customerName;
    private String taxRate;
    private String operator;
    private String settlementUnit;
    private SettlementCycle settlementCycle;
    private List<DocumentMaterialInfo> materialInfoList;


    public void setSettlementCycle(int headOrEnd, int upperLimitReminder, int reconciliationPeriod, boolean enableExpandPeriodFlag) {
        this.settlementCycle = new SettlementCycle(headOrEnd, upperLimitReminder, reconciliationPeriod, enableExpandPeriodFlag);
    }

    public void setMaterialInfoList(List<DocumentMaterialEntity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return;
        }
        materialInfoList = new ArrayList<>();
        entityList.forEach(s -> {
            DocumentMaterialInfo documentMaterialInfo = new DocumentMaterialInfo();
            BeanUtils.copyProperties(s, documentMaterialInfo);
            materialInfoList.add(documentMaterialInfo);
        });
    }

}

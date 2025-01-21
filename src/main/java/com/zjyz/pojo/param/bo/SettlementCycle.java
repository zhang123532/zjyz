package com.zjyz.pojo.param.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementCycle {
    private Integer headOrEnd;
    private Integer upperLimitReminder;
    private Integer reconciliationPeriod;
    private Boolean enableExpandPeriodFlag;
}

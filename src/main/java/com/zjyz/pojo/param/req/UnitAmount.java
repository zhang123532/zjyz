package com.zjyz.pojo.param.req;

import lombok.Data;

@Data
public class UnitAmount {
    private Double dailyRent;
    private Double MonthlyRent;
    private String unitName;
    private Boolean defaultFlag;
}

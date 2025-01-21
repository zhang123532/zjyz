package com.zjyz.pojo.param.req;

import lombok.Data;

import java.util.List;

@Data
public class UpdateMaterialInfoParam {
    private String mid;
    private String name;
    private String categoryName;
    private List<UnitAmount> unitAmounts;
    private List<UnitConversion> unitConversions;
}

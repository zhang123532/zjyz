package com.zjyz.pojo.param.req;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InsertMaterialInfoParam {
    private String name;
    private String categoryName;
    private List<UnitAmount> unitAmounts;
    private List<UnitConversion> unitConversions;

}

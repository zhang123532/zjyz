package com.zjyz.pojo.param.bo;

import lombok.Data;

@Data
public class DocumentMaterialActivityInfo {
    private Integer nonCurrentActivityNumber;
    private Integer currentActivityNumber;
    private Integer predictNumber;
    private Integer totalNumber;
    private String materialName;

    public void calculateTotalNumber(){
        totalNumber = nonCurrentActivityNumber + currentActivityNumber + predictNumber;
    }
}

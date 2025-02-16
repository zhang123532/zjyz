package com.zjyz.pojo.param.bo;

import lombok.Data;

@Data
public class DocumentMaterialInfo {
    private String materialId;
    private String compensationReason;
    private Integer materialNumber;
    private String note;
    private String materialName;
    private String materialUnit;
    private String materialType;
    private Double dailyRent;
    private Double monthlyRent;
}

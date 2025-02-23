package com.zjyz.pojo.param.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DocumentMaterialInfo {
    private String materialId;
    @NotNull(message = "材料数量不能为空")
    private Integer materialNumber;
    private String note;
    private String materialName;
    private String materialUnit;
    private String materialType;
    private Double dailyRent;
    private Double monthlyRent;
}

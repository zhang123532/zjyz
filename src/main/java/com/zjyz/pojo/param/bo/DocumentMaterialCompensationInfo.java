package com.zjyz.pojo.param.bo;

import lombok.Data;

@Data
public class DocumentMaterialCompensationInfo {
    private Integer nonCurrentActivityNumber;
    private Integer currentActivityNumber;
    private Integer returnNumber;
    private Integer rentNumber;
    private String materialName;

}

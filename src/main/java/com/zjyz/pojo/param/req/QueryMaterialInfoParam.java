package com.zjyz.pojo.param.req;

import lombok.Data;

@Data
public class QueryMaterialInfoParam {
    private String materialName;
    private String categoryName;
    private Integer pageNum;
    private Integer pageSize;
}

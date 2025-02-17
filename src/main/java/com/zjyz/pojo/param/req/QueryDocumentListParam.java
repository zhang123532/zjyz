package com.zjyz.pojo.param.req;

import lombok.Data;

@Data
public class QueryDocumentListParam {
    private Integer pageNum;
    private Integer pageSize;
    private String projectId;
    private Integer queryType;
}

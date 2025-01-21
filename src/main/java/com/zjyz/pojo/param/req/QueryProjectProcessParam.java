package com.zjyz.pojo.param.req;

import lombok.Data;

@Data
public class QueryProjectProcessParam {
    private String projectId;
    private Integer queryType;
    private Integer pageNum;
    private Integer pageSize;
}

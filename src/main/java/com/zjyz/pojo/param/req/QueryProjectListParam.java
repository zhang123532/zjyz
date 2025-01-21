package com.zjyz.pojo.param.req;

import lombok.Data;

@Data
public class QueryProjectListParam {
    private Integer pageNum;
    private Integer pageSize;
    private String projectStatusFlag;
}

package com.zjyz.pojo.param.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateProjectParam {
    @NotNull(message = "项目名称不能为空")
    private String projectName;
    @NotNull(message = "项目经理不能为空")
    private String managerName;
}

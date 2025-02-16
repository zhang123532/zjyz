package com.zjyz.pojo.param.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveProjectParam {
    @NotNull(message = "项目id不能为空")
    private String projectId;
    private String managerName;
    private String partnerName;
    private String driver;
    private String escortVehicle;
    private String publishAndReceiver;
    // 项目状态标识  0：进行中 1：已完成
    private String projectStatusFlag;
}

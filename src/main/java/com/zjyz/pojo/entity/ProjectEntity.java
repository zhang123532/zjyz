package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("project")
@Data
public class ProjectEntity {
    @TableId
    private String projectId;
    private String cid;
    private String projectName;
    private String managerName;
    private String partnerName;
    private String driver;
    private String escortVehicle;
    private String pushAndReceiver;
    private String projectStatusFlag;
    private String createDate;
}

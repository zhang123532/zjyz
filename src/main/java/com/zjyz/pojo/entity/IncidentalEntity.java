package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("incidental")
public class IncidentalEntity {
    @TableId
    private String incidentalId;
    private String documentId;
    private String incidentalName;
    private double incidentalPrice;
    private String incidentalNote;
}

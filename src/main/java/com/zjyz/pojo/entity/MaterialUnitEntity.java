package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("material_unit")
public class MaterialUnitEntity {
    private int muid;
    private String unitName;
}

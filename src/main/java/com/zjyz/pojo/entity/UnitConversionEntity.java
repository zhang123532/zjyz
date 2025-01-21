package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("unit_conversion")
public class UnitConversionEntity {
    private String unitConversionId;
    private String unitName1;
    private int Num1;
    private String unitName2;
    private int Num2;
}

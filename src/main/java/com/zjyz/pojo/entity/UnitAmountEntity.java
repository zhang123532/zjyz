package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("unit_amount")
public class UnitAmountEntity {
    private String unitAmountId;

    private Double dailyRent;

    private Double monthlyRent;

    private String unitName;

    private Boolean defaultFlag;

}

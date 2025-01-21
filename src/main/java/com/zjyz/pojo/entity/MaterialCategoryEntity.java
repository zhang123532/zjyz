package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("material_category")
public class MaterialCategoryEntity {
    private int mcid;
    private String categoryName;
}

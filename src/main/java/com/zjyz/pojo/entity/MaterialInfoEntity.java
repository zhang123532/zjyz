package com.zjyz.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@TableName("material_info")
@NoArgsConstructor
public class MaterialInfoEntity {
    private String cid;
    private String mid;
    private String categoryName;
    private String materialName;


    public MaterialInfoEntity(String cid, String mid, String categoryName, String materialName) {
        this.cid = cid;
        this.mid = mid;
        this.categoryName = categoryName;
        this.materialName = materialName;
    }

    public MaterialInfoEntity(String categoryName, String materialName) {
        this.categoryName = categoryName;
        this.materialName = materialName;
    }
}

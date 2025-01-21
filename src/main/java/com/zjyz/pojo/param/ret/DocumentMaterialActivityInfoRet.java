package com.zjyz.pojo.param.ret;

import com.zjyz.pojo.param.bo.DocumentMaterialActivityInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DocumentMaterialActivityInfoRet {
    private List<DocumentMaterialActivityInfo> materialInfos;

    public void addMaterialInfo(DocumentMaterialActivityInfo materialInfo) {
        if (materialInfos == null) {
            materialInfos = new ArrayList<>();
        }
        materialInfos.add(materialInfo);
    }

}

package com.zjyz.pojo.param.ret;

import com.zjyz.pojo.param.bo.DocumentMaterialActivityInfo;
import com.zjyz.pojo.param.bo.DocumentMaterialCompensationInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DocumentMaterialCompensationInfoRet {
    private List<DocumentMaterialCompensationInfo> materialInfos;

    public void addMaterialInfo(DocumentMaterialCompensationInfo materialInfo) {
        if (materialInfos == null) {
            materialInfos = new ArrayList<>();
        }
        materialInfos.add(materialInfo);
    }
}

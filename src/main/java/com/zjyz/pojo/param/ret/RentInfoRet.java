package com.zjyz.pojo.param.ret;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zjyz.pojo.entity.DocumentMaterialEntity;
import com.zjyz.pojo.entity.ExtraFileEntity;
import com.zjyz.pojo.entity.IncidentalEntity;
import com.zjyz.pojo.param.bo.DocumentMaterialInfo;
import com.zjyz.pojo.param.bo.ExtraFile;
import com.zjyz.pojo.param.bo.Incidental;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class RentInfoRet {
    private String rentDocumentId;
    private String projectId;
    private String rentDocumentName;
    private String rentDate;
    private String rentalStation;
    private String personInCharge;
    private String auditor;
    private String note;
    private List<Incidental> incidentalList;
    private List<DocumentMaterialInfo> materialRentInfoList;
    private List<ExtraFile> extraFileList;

    public void setIncidentalList(List<IncidentalEntity> incidentalList) {
        if (CollectionUtils.isEmpty(incidentalList)) {
            return;
        }
        if (this.incidentalList == null) {
            this.incidentalList = new ArrayList<>();
        }
        incidentalList.forEach(incidental -> {
            Incidental incidental1 = new Incidental();
            BeanUtils.copyProperties(incidental, incidental1);
            this.incidentalList.add(incidental1);
        });
    }

    public void setMaterialRentInfoList(List<DocumentMaterialEntity> materialRentInfoList) {
        if (CollectionUtils.isEmpty(materialRentInfoList)) {
            return;
        }
        if (this.materialRentInfoList == null) {
            this.materialRentInfoList = new ArrayList<>();
        }
        materialRentInfoList.forEach(documentMaterialInfo -> {
                    DocumentMaterialInfo documentMaterialInfo1 = new DocumentMaterialInfo();
                    BeanUtils.copyProperties(documentMaterialInfo, documentMaterialInfo1);
                    this.materialRentInfoList.add(documentMaterialInfo1);
                }
        );
    }

    public void setExtraFileList(List<ExtraFileEntity> extraFileList) {
        if (CollectionUtils.isEmpty(extraFileList)) {
            return;
        }
        if (this.extraFileList == null) {
            this.extraFileList = new ArrayList<>();
        }
        extraFileList.forEach(extraFile -> {
                    ExtraFile extraFile1 = new ExtraFile();
                    BeanUtils.copyProperties(extraFile, extraFile1);
                    this.extraFileList.add(extraFile1);
                }
        );
    }
}

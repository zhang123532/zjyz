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
public class CompensationInfoRet {
    private String compensationDocumentId;
    private String projectId;
    private String customerName;
    private String compensationDocumentName;
    private String compensationTime;
    private String rentalStationStation;
    private String personInCharge;
    private String auditor;
    private String note;
    private List<Incidental> incidentalList;
    private List<DocumentMaterialInfo> materialCompensationInfoList;
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

    public void setMaterialCompensationInfoInfoList(List<DocumentMaterialEntity> materialCompensationInfoInfoList) {
        if (CollectionUtils.isEmpty(materialCompensationInfoInfoList)) {
            return;
        }
        if (this.materialCompensationInfoList == null) {
            this.materialCompensationInfoList = new ArrayList<>();
        }
        materialCompensationInfoInfoList.forEach(documentMaterialInfo -> {
                    DocumentMaterialInfo documentMaterialInfo1 = new DocumentMaterialInfo();
                    BeanUtils.copyProperties(documentMaterialInfo, documentMaterialInfo1);
                    this.materialCompensationInfoList.add(documentMaterialInfo1);
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

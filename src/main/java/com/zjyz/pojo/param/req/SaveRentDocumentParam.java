package com.zjyz.pojo.param.req;

import com.zjyz.pojo.param.bo.DocumentMaterialInfo;
import com.zjyz.pojo.param.bo.ExtraFile;
import com.zjyz.pojo.param.bo.Incidental;
import lombok.Data;

import java.util.List;

@Data
public class SaveRentDocumentParam {
    private String rentDocumentId;
    private String projectId;
    private String customerName;
    private String rentDocumentName;
    private String rentDate;
    private String rentalStation;
    private String personInCharge;
    private String auditor;
    private String note;
    private List<Incidental> incidentalList;
    private List<DocumentMaterialInfo> materialRentInfoList;
    private List<ExtraFile> extraFileList;
    private boolean isForceToSave;
}

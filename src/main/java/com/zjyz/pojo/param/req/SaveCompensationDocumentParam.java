package com.zjyz.pojo.param.req;

import com.zjyz.pojo.param.bo.DocumentMaterialInfo;
import com.zjyz.pojo.param.bo.ExtraFile;
import com.zjyz.pojo.param.bo.Incidental;
import lombok.Data;

import java.util.List;
@Data
public class SaveCompensationDocumentParam {
    private String compensationDocumentId;
    private String projectId;
    private String customerName;
    private String compensationDocumentName;
    private String compensationDate;
    private String rentalStation;
    private String personInCharge;
    private String auditor;
    private String note;
    private List<Incidental> incidentalList;
    private List<DocumentMaterialInfo> materialCompensationInfoList;
    private List<ExtraFile> extraFileList;
}

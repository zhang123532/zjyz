package com.zjyz.pojo.param.req;

import com.zjyz.pojo.param.bo.DocumentMaterialInfo;
import com.zjyz.pojo.param.bo.ExtraFile;
import com.zjyz.pojo.param.bo.Incidental;
import lombok.Data;

import java.util.List;
@Data
public class SaveReturnDocumentParam {
    private String returnDocumentId;
    private String projectId;
    private String customerName;
    private String returnDocumentName;
    private String returnDate;
    private String rentStation;
    private String personInCharge;
    private String auditor;
    private String note;
    private List<Incidental> incidentalList;
    private List<DocumentMaterialInfo> materialReturnInfoList;
    private List<ExtraFile> extraFileList;
}

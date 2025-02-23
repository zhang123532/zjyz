package com.zjyz.pojo.param.req;

import com.zjyz.pojo.param.bo.DocumentMaterialInfo;
import com.zjyz.pojo.param.bo.ExtraFile;
import com.zjyz.pojo.param.bo.Incidental;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class SaveRentDocumentParam {
    private String rentDocumentId;
    @NotBlank(message = "项目编号不能为空")
    private String projectId;
    private String customerName;
    private String rentDocumentName;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "租赁日期格式必须为 yyyy-MM-dd")
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

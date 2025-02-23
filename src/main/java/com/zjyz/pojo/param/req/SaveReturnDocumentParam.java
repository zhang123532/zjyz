package com.zjyz.pojo.param.req;

import com.zjyz.pojo.param.bo.DocumentMaterialInfo;
import com.zjyz.pojo.param.bo.ExtraFile;
import com.zjyz.pojo.param.bo.Incidental;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
@Data
public class SaveReturnDocumentParam {
    private String returnDocumentId;
    @NotBlank(message = "项目编号不能为空")
    private String projectId;
    private String customerName;
    private String returnDocumentName;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "归还日期格式必须为 yyyy-MM-dd")
    private String returnDate;
    private String rentalStation;
    private String personInCharge;
    private String auditor;
    private String note;
    private List<Incidental> incidentalList;
    @Valid
    private List<DocumentMaterialInfo> materialReturnInfoList;
    private List<ExtraFile> extraFileList;
}

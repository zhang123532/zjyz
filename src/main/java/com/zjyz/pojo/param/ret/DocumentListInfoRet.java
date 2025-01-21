package com.zjyz.pojo.param.ret;

import com.zjyz.pojo.param.bo.DocumentBriefInfo;
import lombok.Data;

import java.util.List;

@Data
public class DocumentListInfoRet {
    private Long totalNum;
    private Long current;
    private List<DocumentBriefInfo> documentBriefInfoList;


}

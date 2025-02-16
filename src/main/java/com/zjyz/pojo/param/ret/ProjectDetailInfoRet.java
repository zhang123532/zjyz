package com.zjyz.pojo.param.ret;

import com.zjyz.pojo.param.bo.SettlementCycle;
import lombok.Data;

import java.util.List;
@Data
public class ProjectDetailInfoRet {
    private String projectId;
    private String managerName;
    private String createDate;
    private String partnerName;
    private String driver;
    private String escortVehicle;
    private String publishAndReceiver;
    private String projectStatusFlag;
    private Integer rentOutProgress;
    private Integer returnProgress;
    private SettlementCycle settlementCycle;
    private List<RelatedDocument> relatedDocuments;
    @Data
    public static class RelatedDocument {
        private String documentId;
        private String documentName;
        private String documentType;
    }

}

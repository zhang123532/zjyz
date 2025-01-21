package com.zjyz.pojo.param.ret;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectListRet {
    private Long totalNum;
    private Long current;
    private List<ProjectBriefInfo> projectBriefInfos;

    public void addProjectBriefInfo(ProjectBriefInfo briefInfo) {
        if (projectBriefInfos == null) {
            projectBriefInfos = new ArrayList<>();
        }
        projectBriefInfos.add(briefInfo);
    }

    @Data
    public static class ProjectBriefInfo {
        private String projectId;
        private String projectName;
        private String managerName;
        private String createDate;
    }
}

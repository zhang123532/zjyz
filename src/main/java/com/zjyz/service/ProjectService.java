package com.zjyz.service;

import com.zjyz.pojo.param.req.CreateProjectParam;
import com.zjyz.pojo.param.req.QueryProjectListParam;
import com.zjyz.pojo.param.req.SaveProjectParam;
import com.zjyz.pojo.param.ret.ProjectDetailInfoRet;
import com.zjyz.pojo.param.ret.ProjectListRet;

public interface ProjectService {
    String createProject(CreateProjectParam createParam);

    boolean saveProject(SaveProjectParam saveParam);

    ProjectDetailInfoRet queryProjectInfo(String projectId);

    ProjectListRet queryProjectList(QueryProjectListParam queryParam);
}

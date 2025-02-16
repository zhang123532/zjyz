package com.zjyz.controller;

import com.zjyz.common.annotation.ZeeController;
import com.zjyz.common.bean.RetBaseParam;
import com.zjyz.pojo.param.req.CreateProjectParam;
import com.zjyz.pojo.param.req.QueryProjectListParam;
import com.zjyz.pojo.param.req.SaveProjectParam;
import com.zjyz.pojo.param.ret.ProjectDetailInfoRet;
import com.zjyz.pojo.param.ret.ProjectListRet;
import com.zjyz.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ZeeController
@RequestMapping("/project")
@Api(tags = "项目管理")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    // 创建项目
    @PostMapping("/createProject")
    @ApiOperation(value = "创建项目")
    public RetBaseParam createProject(@RequestBody CreateProjectParam createParam) {
        String id= projectService.createProject(createParam);
        return new RetBaseParam(id);
    }

    // 保存项目
    @PostMapping("/saveProject")
    @ApiOperation(value = "保存项目")
    public boolean saveProject(@RequestBody SaveProjectParam saveParam) {
        return projectService.saveProject(saveParam);
    }

    // 查询项目信息
    @GetMapping("/queryProjectInfo")
    @ApiOperation(value = "查询项目信息")
    public ProjectDetailInfoRet queryProjectInfo(@RequestParam String projectId) {
        return projectService.queryProjectInfo(projectId);
    }

    // 查询项目列表
    @GetMapping("/queryProjectList")
    @ApiOperation(value = "查询项目列表")
    public ProjectListRet queryProjectList(QueryProjectListParam queryParam) {
        return projectService.queryProjectList(queryParam);
    }
    @GetMapping("/deleteProject")
    @ApiOperation(value = "删除项目")
    public boolean deleteProject(@RequestParam String projectId) {
        return projectService.deleteProject(projectId);
    }

}

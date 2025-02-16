package com.zjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjyz.common.exception.MyBizException;
import com.zjyz.common.util.CommonUtil;
import com.zjyz.common.util.TimeUtil;
import com.zjyz.dao.ProjectMapper;
import com.zjyz.pojo.entity.ProjectEntity;
import com.zjyz.pojo.param.req.CreateProjectParam;
import com.zjyz.pojo.param.req.QueryProjectListParam;
import com.zjyz.pojo.param.req.SaveProjectParam;
import com.zjyz.pojo.param.ret.ProjectDetailInfoRet;
import com.zjyz.pojo.param.ret.ProjectListRet;
import com.zjyz.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public String createProject(CreateProjectParam createParam) {
        // 根据 cid 和 project_name 查询项目是否存在
        QueryWrapper<ProjectEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", CommonUtil.getCid()).eq("project_name", createParam.getProjectName());
        ProjectEntity existingProject = projectMapper.selectOne(queryWrapper);
        if (existingProject != null) {
            throw new MyBizException(createParam.getProjectName()+"项目已存在,请勿重复创建","PRCT001");
        }
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectId(CommonUtil.createUuid());
        projectEntity.setCid(CommonUtil.getCid());
        projectEntity.setProjectName(createParam.getProjectName());
        projectEntity.setManagerName(createParam.getManagerName());
        projectEntity.setProjectStatusFlag("0");
        projectEntity.setCreateDate(TimeUtil.getNowDate());
        projectMapper.insert(projectEntity);
        return projectEntity.getProjectId();
    }

    @Override
    public boolean saveProject(SaveProjectParam saveParam) {
        ProjectEntity projectEntity = new ProjectEntity();
        BeanUtils.copyProperties(saveParam, projectEntity);
        return projectMapper.insertOrUpdate(projectEntity);
    }

    @Override
    public ProjectDetailInfoRet queryProjectInfo(String projectId) {
        QueryWrapper<ProjectEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        ProjectEntity projectEntity = projectMapper.selectOne(queryWrapper);
        ProjectDetailInfoRet projectDetailInfoRet = new ProjectDetailInfoRet();
        BeanUtils.copyProperties(projectEntity, projectDetailInfoRet);
        return projectDetailInfoRet;
    }

    @Override
    public ProjectListRet queryProjectList(QueryProjectListParam queryParam) {
        Page<ProjectEntity> page = new Page<>(queryParam.getPageNum(), queryParam.getPageSize());
        QueryWrapper<ProjectEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", CommonUtil.getCid());
        if(StringUtils.hasText(queryParam.getProjectStatusFlag())){
            queryWrapper.eq("project_status_flag",queryParam.getProjectStatusFlag());
        }
        Page<ProjectEntity> pageResult = projectMapper.selectPage(page, queryWrapper);
        ProjectListRet ret=new ProjectListRet();
        ret.setCurrent(pageResult.getCurrent());
        ret.setTotalNum(pageResult.getTotal());
        for (ProjectEntity entity : pageResult.getRecords()) {
            ProjectListRet.ProjectBriefInfo briefInfo=new ProjectListRet.ProjectBriefInfo();
            BeanUtils.copyProperties(entity,briefInfo);
            ret.addProjectBriefInfo(briefInfo);
        }
        return ret;
    }
    @Override
    public boolean deleteProject(String projectId) {
        QueryWrapper<ProjectEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        int rows = projectMapper.delete(queryWrapper);
        return rows > 0;
    }
}

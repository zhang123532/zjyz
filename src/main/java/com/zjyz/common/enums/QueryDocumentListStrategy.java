package com.zjyz.common.enums;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjyz.common.util.ApplicationBeanUtil;
import com.zjyz.dao.CompensationDocumentMapper;
import com.zjyz.dao.RentDocumentMapper;
import com.zjyz.dao.ReturnDocumentMapper;
import com.zjyz.dao.StatementMapper;
import com.zjyz.pojo.entity.CompensationDocumentEntity;
import com.zjyz.pojo.entity.RentDocumentEntity;
import com.zjyz.pojo.entity.ReturnDocumentEntity;
import com.zjyz.pojo.entity.StatementEntity;
import com.zjyz.pojo.param.bo.DocumentBriefInfo;
import com.zjyz.pojo.param.ret.DocumentListInfoRet;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public enum QueryDocumentListStrategy {
    RENT_DOCUMENT(0) {
        @Override
        public DocumentListInfoRet toDo(String projectId, int pageSize, int pageNum) {
            DocumentListInfoRet ret = new DocumentListInfoRet();
            RentDocumentMapper mapper = ApplicationBeanUtil.getBean(RentDocumentMapper.class);
            Page<RentDocumentEntity> page = new Page<>(pageNum, pageSize);
            QueryWrapper<RentDocumentEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id", projectId);
            Page<RentDocumentEntity> pageResult = mapper.selectPage(page, queryWrapper);
            ret.setCurrent(pageResult.getCurrent());
            ret.setTotalNum(pageResult.getTotal());
            List<DocumentBriefInfo> briefInfos = new ArrayList<>();
            if (CollectionUtils.isEmpty(pageResult.getRecords())) {
                return ret;
            }
            pageResult.getRecords().forEach(s -> {
                DocumentBriefInfo briefInfo = new DocumentBriefInfo();
                BeanUtils.copyProperties(s, briefInfo);
                briefInfo.setDocumentId(s.getRentDocumentId());
                briefInfo.setDocumentName(s.getRentDocumentName());
                briefInfos.add(briefInfo);
            });
            ret.setDocumentBriefInfoList(briefInfos);
            return ret;
        }
    },
    RETURN_DOCUMENT(1) {
        @Override
        public DocumentListInfoRet toDo(String projectId, int pageSize, int pageNum) {
            DocumentListInfoRet ret = new DocumentListInfoRet();
            ReturnDocumentMapper mapper = ApplicationBeanUtil.getBean(ReturnDocumentMapper.class);
            Page<ReturnDocumentEntity> page = new Page<>(pageNum, pageSize);
            QueryWrapper<ReturnDocumentEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id", projectId);
            Page<ReturnDocumentEntity> pageResult = mapper.selectPage(page, queryWrapper);
            ret.setCurrent(pageResult.getCurrent());
            ret.setTotalNum(pageResult.getTotal());
            List<DocumentBriefInfo> briefInfos = new ArrayList<>();
            if (CollectionUtils.isEmpty(pageResult.getRecords())) {
                return ret;
            }
            pageResult.getRecords().forEach(s -> {
                DocumentBriefInfo briefInfo = new DocumentBriefInfo();
                BeanUtils.copyProperties(s, briefInfo);
                briefInfo.setDocumentId(s.getReturnDocumentId());
                briefInfo.setDocumentName(s.getReturnDocumentName());
                briefInfos.add(briefInfo);
            });
            ret.setDocumentBriefInfoList(briefInfos);
            return ret;

        }
    },
    COMPENSATION_DOCUMENT(2) {
        @Override
        public DocumentListInfoRet toDo(String projectId, int pageSize, int pageNum) {
            DocumentListInfoRet ret = new DocumentListInfoRet();
            CompensationDocumentMapper mapper = ApplicationBeanUtil.getBean(CompensationDocumentMapper.class);
            Page<CompensationDocumentEntity> page = new Page<>(pageNum, pageSize);
            QueryWrapper<CompensationDocumentEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id", projectId);
            Page<CompensationDocumentEntity> pageResult = mapper.selectPage(page, queryWrapper);
            ret.setCurrent(pageResult.getCurrent());
            ret.setTotalNum(pageResult.getTotal());
            List<DocumentBriefInfo> briefInfos = new ArrayList<>();
            if (CollectionUtils.isEmpty(pageResult.getRecords())) {
                return ret;
            }
            pageResult.getRecords().forEach(s -> {
                DocumentBriefInfo briefInfo = new DocumentBriefInfo();
                BeanUtils.copyProperties(s, briefInfo);
                briefInfo.setDocumentId(s.getCompensationDocumentId());
                briefInfo.setDocumentName(s.getCompensationDocumentName());
                briefInfos.add(briefInfo);
            });
            ret.setDocumentBriefInfoList(briefInfos);
            return ret;
        }

    },
    STATEMENT_DOCUMENT(3) {
        @Override
        public DocumentListInfoRet toDo(String projectId, int pageSize, int pageNum) {
            DocumentListInfoRet ret = new DocumentListInfoRet();
            StatementMapper mapper = ApplicationBeanUtil.getBean(StatementMapper.class);
            Page<StatementEntity> page = new Page<>(pageNum, pageSize);
            QueryWrapper<StatementEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id", projectId);
            Page<StatementEntity> pageResult = mapper.selectPage(page, queryWrapper);
            ret.setCurrent(pageResult.getCurrent());
            ret.setTotalNum(pageResult.getTotal());
            List<DocumentBriefInfo> briefInfos = new ArrayList<>();
            if (CollectionUtils.isEmpty(pageResult.getRecords())) {
                return ret;
            }
            pageResult.getRecords().forEach(s -> {
                DocumentBriefInfo briefInfo = new DocumentBriefInfo();
                BeanUtils.copyProperties(s, briefInfo);
                briefInfos.add(briefInfo);
            });
            ret.setDocumentBriefInfoList(briefInfos);
            return ret;

        }
    };
    private final Integer type;

    QueryDocumentListStrategy(Integer type) {
        this.type = type;
    }

    public static QueryDocumentListStrategy getStrategy(int type) {
        List<QueryDocumentListStrategy> strategyList = List.of(QueryDocumentListStrategy.values());
        return strategyList.stream().filter(s -> s.type == type).findFirst().orElse(RENT_DOCUMENT);
    }

    public abstract DocumentListInfoRet toDo(String projectId, int pageSize, int pageNum);
}

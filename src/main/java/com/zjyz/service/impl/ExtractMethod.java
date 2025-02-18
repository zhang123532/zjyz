package com.zjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjyz.common.util.CommonUtil;
import com.zjyz.dao.DocumentMaterialMapper;
import com.zjyz.dao.ExtraFileMapper;
import com.zjyz.dao.IncidentalMapper;
import com.zjyz.pojo.entity.DocumentMaterialEntity;
import com.zjyz.pojo.entity.ExtraFileEntity;
import com.zjyz.pojo.entity.IncidentalEntity;
import com.zjyz.pojo.param.bo.DocumentMaterialInfo;
import com.zjyz.pojo.param.bo.ExtraFile;
import com.zjyz.pojo.param.bo.Incidental;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class ExtractMethod {
    @Autowired
    private ExtraFileMapper extraFileMapper;
    @Autowired
    private IncidentalMapper incidentalMapper;
    @Autowired
    private DocumentMaterialMapper documentMaterialMapper;

    public void saveIncidental(List<Incidental> incidentalList, String documentId) {
        if (CollectionUtils.isEmpty(incidentalList)) {
            return;
        }
        incidentalList.forEach(incidental -> {
            IncidentalEntity incidentalEntity = new IncidentalEntity();
            if (!StringUtils.hasText(incidental.getIncidentalId())) {
                incidental.setIncidentalId(CommonUtil.createUuid());
            }
            BeanUtils.copyProperties(incidental, incidentalEntity);
            incidentalEntity.setDocumentId(documentId);
            incidentalMapper.insertOrUpdate(incidentalEntity);
        });
    }

    public void saveExtraFile(List<ExtraFile> extraFileList, String documentId) {
        if (CollectionUtils.isEmpty(extraFileList)) {
            return;
        }
        extraFileList.forEach(s -> {
            ExtraFileEntity extraFileEntity = new ExtraFileEntity();
            if (!StringUtils.hasText(s.getExtraFileId())) {
                s.setExtraFileId(CommonUtil.createUuid());
            }
            BeanUtils.copyProperties(s, extraFileEntity);
            extraFileEntity.setDocumentId(documentId);
            extraFileMapper.insertOrUpdate(extraFileEntity);
        });
    }

    public void saveMaterial(List<DocumentMaterialInfo> materialInfoList, String documentId,String projectId, String type) {
        if (CollectionUtils.isEmpty(materialInfoList)) {
            return;
        }
        materialInfoList.forEach(s -> {
            DocumentMaterialEntity documentMaterialEntity = new DocumentMaterialEntity();
            if (!StringUtils.hasText(s.getMaterialId())) {
                s.setMaterialId(CommonUtil.createUuid());
            }
            BeanUtils.copyProperties(s, documentMaterialEntity);
            documentMaterialEntity.setProjectId(projectId);
            documentMaterialEntity.setDocumentId(documentId);
            documentMaterialEntity.setType(type);
            documentMaterialMapper.insertOrUpdate(documentMaterialEntity);
        });
    }

    public List<DocumentMaterialEntity> getMaterialListByProjectId(String projectId) {
        QueryWrapper<DocumentMaterialEntity> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.eq("project_id", projectId);
        return documentMaterialMapper.selectList(materialQueryWrapper);
    }

    public List<DocumentMaterialEntity> getMaterialListByDocumentId(String documentId) {
        QueryWrapper<DocumentMaterialEntity> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.eq("document_id", documentId);
        return documentMaterialMapper.selectList(materialQueryWrapper);
    }

    public List<IncidentalEntity> getIncidentalListByDocumentId(String documentId) {
        QueryWrapper<IncidentalEntity> incidentalQueryWrapper = new QueryWrapper<>();
        incidentalQueryWrapper.eq("document_id", documentId);
        return incidentalMapper.selectList(incidentalQueryWrapper);
    }

    public List<ExtraFileEntity> getExtraFileListByDocumentId(String documentId) {
        QueryWrapper<ExtraFileEntity> extraFileQueryWrapper = new QueryWrapper<>();
        extraFileQueryWrapper.eq("document_id", documentId);
        return extraFileMapper.selectList(extraFileQueryWrapper);
    }
}

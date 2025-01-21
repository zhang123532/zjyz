package com.zjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjyz.common.util.CommonUtil;
import com.zjyz.dao.CompensationDocumentMapper;
import com.zjyz.dao.RentDocumentMapper;
import com.zjyz.pojo.entity.CompensationDocumentEntity;
import com.zjyz.pojo.entity.DocumentMaterialEntity;
import com.zjyz.pojo.entity.RentDocumentEntity;
import com.zjyz.pojo.param.bo.DocumentMaterialActivityInfo;
import com.zjyz.pojo.param.bo.DocumentMaterialCompensationInfo;
import com.zjyz.pojo.param.req.QueryDocumentMaterialInfoParam;
import com.zjyz.pojo.param.req.SaveCompensationDocumentParam;
import com.zjyz.pojo.param.ret.CompensationInfoRet;
import com.zjyz.pojo.param.ret.DocumentMaterialActivityInfoRet;
import com.zjyz.pojo.param.ret.DocumentMaterialCompensationInfoRet;
import com.zjyz.pojo.param.ret.RentInfoRet;
import com.zjyz.service.CompensationDocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CompensationDocumentServiceImpl implements CompensationDocumentService {
    @Autowired
    private CompensationDocumentMapper rentDocumentMapper;

    @Autowired
    private ExtractMethod extractMethod;

    @Override
    public String saveCompensationDocument(SaveCompensationDocumentParam param) {
        CompensationDocumentEntity compensationDocumentEntity = new CompensationDocumentEntity();
        if (!StringUtils.hasText(param.getCompensationDocumentId())) {
            param.setCompensationDocumentId(CommonUtil.createUuid());
        }
        BeanUtils.copyProperties(param, compensationDocumentEntity);
        rentDocumentMapper.insertOrUpdate(compensationDocumentEntity);
        extractMethod.saveExtraFile(param.getExtraFileList(), compensationDocumentEntity.getCompensationId());
        extractMethod.saveIncidental(param.getIncidentalList(), compensationDocumentEntity.getCompensationId());
        extractMethod.saveMaterial(param.getMaterialCompensationInfoList(), compensationDocumentEntity.getCompensationId(), "3");
        return param.getCompensationDocumentId();
    }

    @Override
    public CompensationInfoRet queryCompensationDocument(String compensationDocumentId) {
        CompensationInfoRet compensationInfoRet = new CompensationInfoRet();
        QueryWrapper<CompensationDocumentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rent_document_id", compensationDocumentId);
        CompensationDocumentEntity compensationDocumentEntity = rentDocumentMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(compensationDocumentEntity, compensationInfoRet);
        compensationInfoRet.setMaterialCompensationInfoInfoList(extractMethod.getMaterialListByDocumentId(compensationDocumentId));
        compensationInfoRet.setIncidentalList(extractMethod.getIncidentalListByDocumentId(compensationDocumentId));
        compensationInfoRet.setExtraFileList(extractMethod.getExtraFileListByDocumentId(compensationDocumentId));
        return compensationInfoRet;
    }

    @Override
    public DocumentMaterialCompensationInfoRet queryCompensationMaterialInfo(QueryDocumentMaterialInfoParam queryParam) {
        List<DocumentMaterialEntity> documentMaterialEntityList = extractMethod.getMaterialListByProjectId(queryParam.getProjectId());
        DocumentMaterialCompensationInfoRet ret = new DocumentMaterialCompensationInfoRet();
        if (CollectionUtils.isEmpty(documentMaterialEntityList)) {
            return ret;
        }

        //合同材料
        List<DocumentMaterialEntity> contractMaterialList = documentMaterialEntityList.stream()
                .filter(s -> s.getType().equals("0")).collect(Collectors.toList());
        //租赁单材料
        List<DocumentMaterialEntity> compensationMaterialList = documentMaterialEntityList.stream()
                .filter(s -> s.getType().equals("3")).collect(Collectors.toList());
        List<DocumentMaterialEntity> rentMaterialList = documentMaterialEntityList.stream()
                .filter(s -> s.getType().equals("1")).collect(Collectors.toList());
        List<DocumentMaterialEntity> returnMaterialList = documentMaterialEntityList.stream()
                .filter(s -> s.getType().equals("2")).collect(Collectors.toList());
        contractMaterialList.forEach(contractMaterial -> {
            DocumentMaterialCompensationInfo activityInfo = new DocumentMaterialCompensationInfo();
            activityInfo.setMaterialName(contractMaterial.getMaterialName());
            int thisCompensationNumber = compensationMaterialList.stream()
                    .filter(s -> s.getMaterialId().equals(contractMaterial.getMaterialId()) && s.getDocumentId().equals(queryParam.getDocumentId()))
                    .mapToInt(DocumentMaterialEntity::getMaterialNumber).sum();
            activityInfo.setCurrentActivityNumber(thisCompensationNumber);
            int historyCompensationNumber = compensationMaterialList.stream()
                    .filter(s -> s.getMaterialId().equals(contractMaterial.getMaterialId()) && !s.getDocumentId().equals(queryParam.getDocumentId()))
                    .mapToInt(DocumentMaterialEntity::getMaterialNumber).sum();
            activityInfo.setNonCurrentActivityNumber(historyCompensationNumber);
            int rentNumber = rentMaterialList.stream()
                    .filter(s -> s.getMaterialId().equals(contractMaterial.getMaterialId())).mapToInt(DocumentMaterialEntity::getMaterialNumber).sum();
            int returnNumber = returnMaterialList.stream()
                    .filter(s -> s.getMaterialId().equals(contractMaterial.getMaterialId())).mapToInt(DocumentMaterialEntity::getMaterialNumber).sum();
            activityInfo.setRentNumber(rentNumber);
            activityInfo.setReturnNumber(returnNumber);
            ret.addMaterialInfo(activityInfo);
        });
        return ret;
    }


}

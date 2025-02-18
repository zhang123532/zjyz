package com.zjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjyz.common.util.CommonUtil;
import com.zjyz.common.util.TimeUtil;
import com.zjyz.dao.ReturnDocumentMapper;
import com.zjyz.pojo.entity.DocumentMaterialEntity;
import com.zjyz.pojo.entity.ReturnDocumentEntity;
import com.zjyz.pojo.param.bo.DocumentMaterialActivityInfo;
import com.zjyz.pojo.param.req.QueryDocumentMaterialInfoParam;
import com.zjyz.pojo.param.req.SaveReturnDocumentParam;
import com.zjyz.pojo.param.ret.DocumentMaterialActivityInfoRet;
import com.zjyz.pojo.param.ret.ReturnInfoRet;
import com.zjyz.service.ReturnDocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReturnDocumentServiceImpl implements ReturnDocumentService {

    @Autowired
    private ReturnDocumentMapper returnDocumentMapper;

    @Autowired
    private ExtractMethod extractMethod;

    @Override
    public String saveReturnDocument(SaveReturnDocumentParam param) {
        ReturnDocumentEntity returnDocumentEntity = new ReturnDocumentEntity();
        if (!StringUtils.hasText(param.getReturnDocumentId())) {
            param.setReturnDocumentId(CommonUtil.createUuid());
            returnDocumentEntity.setCreateDate(TimeUtil.getNowDate());
        }
        BeanUtils.copyProperties(param, returnDocumentEntity);
        returnDocumentMapper.insertOrUpdate(returnDocumentEntity);
        extractMethod.saveExtraFile(param.getExtraFileList(), returnDocumentEntity.getReturnDocumentId());
        extractMethod.saveIncidental(param.getIncidentalList(), returnDocumentEntity.getReturnDocumentId());
        extractMethod.saveMaterial(param.getMaterialReturnInfoList(), returnDocumentEntity.getReturnDocumentId(), param.getProjectId(), "2");
        return param.getReturnDocumentId();
    }

    @Override
    public ReturnInfoRet queryReturnDocument(String returnDocumentId) {
        ReturnInfoRet returnInfoRet = new ReturnInfoRet();
        QueryWrapper<ReturnDocumentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("return_document_id", returnDocumentId);
        ReturnDocumentEntity returnDocumentEntity = returnDocumentMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(returnDocumentEntity, returnInfoRet);
        returnInfoRet.setMaterialReturnInfoList(extractMethod.getMaterialListByDocumentId(returnDocumentId));
        returnInfoRet.setIncidentalList(extractMethod.getIncidentalListByDocumentId(returnDocumentId));
        returnInfoRet.setExtraFileList(extractMethod.getExtraFileListByDocumentId(returnDocumentId));
        return returnInfoRet;
    }

    @Override
    public DocumentMaterialActivityInfoRet queryReturnMaterialInfo(QueryDocumentMaterialInfoParam queryParam) {
        List<DocumentMaterialEntity> documentMaterialEntityList = extractMethod.getMaterialListByProjectId(queryParam.getProjectId());
        DocumentMaterialActivityInfoRet ret = new DocumentMaterialActivityInfoRet();
        if (CollectionUtils.isEmpty(documentMaterialEntityList)) {
            return ret;
        }
        //合同材料
        List<DocumentMaterialEntity> contractMaterialList = documentMaterialEntityList.stream()
                .filter(s -> s.getType().equals("0")).collect(Collectors.toList());
        //归还单材料
        List<DocumentMaterialEntity> rentMaterialList = documentMaterialEntityList.stream()
                .filter(s -> s.getType().equals("2")).collect(Collectors.toList());
        contractMaterialList.forEach(contractMaterial -> {
            DocumentMaterialActivityInfo activityInfo = new DocumentMaterialActivityInfo();
            activityInfo.setMaterialName(contractMaterial.getMaterialName());
            activityInfo.setPredictNumber(contractMaterial.getMaterialNumber());
            int thisRentNumber = rentMaterialList.stream()
                    .filter(s -> s.getMaterialId().equals(contractMaterial.getMaterialId()) && s.getDocumentId().equals(queryParam.getDocumentId()))
                    .mapToInt(DocumentMaterialEntity::getMaterialNumber).sum();
            activityInfo.setCurrentActivityNumber(thisRentNumber);
            int historyRentNumber = rentMaterialList.stream()
                    .filter(s -> s.getMaterialId().equals(contractMaterial.getMaterialId()) && !s.getDocumentId().equals(queryParam.getDocumentId()))
                    .mapToInt(DocumentMaterialEntity::getMaterialNumber).sum();
            activityInfo.setNonCurrentActivityNumber(historyRentNumber);
            activityInfo.calculateTotalNumber();
            ret.addMaterialInfo(activityInfo);
        });
        return ret;
    }


}

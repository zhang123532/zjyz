package com.zjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjyz.common.enums.QueryDocumentListStrategy;
import com.zjyz.common.util.CommonUtil;
import com.zjyz.dao.*;
import com.zjyz.pojo.entity.DocumentMaterialEntity;
import com.zjyz.pojo.entity.RentDocumentEntity;
import com.zjyz.pojo.param.bo.DocumentMaterialActivityInfo;
import com.zjyz.pojo.param.req.QueryDocumentListParam;
import com.zjyz.pojo.param.req.QueryDocumentMaterialInfoParam;
import com.zjyz.pojo.param.req.SaveRentDocumentParam;
import com.zjyz.pojo.param.ret.DocumentListInfoRet;
import com.zjyz.pojo.param.ret.DocumentMaterialActivityInfoRet;
import com.zjyz.pojo.param.ret.RentInfoRet;
import com.zjyz.service.RentDocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentDocumentServiceImpl implements RentDocumentService {
    @Autowired
    private RentDocumentMapper rentDocumentMapper;

    @Autowired
    private ExtractMethod extractMethod;

    @Override
    public DocumentListInfoRet queryDocumentList(QueryDocumentListParam queryParam) {
        QueryDocumentListStrategy strategy = QueryDocumentListStrategy.getStrategy(queryParam.getQueryType());
        return strategy.toDo(queryParam.getDocumentId(), queryParam.getPageNum(), queryParam.getPageSize());
    }

    @Override
    public String saveRentDocument(SaveRentDocumentParam param) {
        RentDocumentEntity rentDocumentEntity = new RentDocumentEntity();
        if (!StringUtils.hasText(param.getRentDocumentId())) {
            param.setRentDocumentId(CommonUtil.createUuid());
        }
        BeanUtils.copyProperties(param, rentDocumentEntity);
        rentDocumentMapper.insertOrUpdate(rentDocumentEntity);
        extractMethod.saveExtraFile(param.getExtraFileList(), rentDocumentEntity.getRentDocumentId());
        extractMethod.saveIncidental(param.getIncidentalList(), rentDocumentEntity.getRentDocumentId());
        extractMethod.saveMaterial(param.getMaterialRentInfoList(), rentDocumentEntity.getRentDocumentId(), "1");
        return param.getRentDocumentId();
    }

    @Override
    public RentInfoRet queryRentDocument(String rentDocumentId) {
        RentInfoRet rentInfoRet = new RentInfoRet();
        QueryWrapper<RentDocumentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rent_document_id", rentDocumentId);
        RentDocumentEntity rentDocumentEntity = rentDocumentMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(rentDocumentEntity, rentInfoRet);
        rentInfoRet.setMaterialRentInfoList( extractMethod.getMaterialListByDocumentId(rentDocumentId));
        rentInfoRet.setIncidentalList(extractMethod.getIncidentalListByDocumentId(rentDocumentId));
        rentInfoRet.setExtraFileList(extractMethod.getExtraFileListByDocumentId(rentDocumentId));
        return rentInfoRet;
    }

    @Override
    public DocumentMaterialActivityInfoRet queryRentMaterialInfo(QueryDocumentMaterialInfoParam queryParam) {
        List<DocumentMaterialEntity> documentMaterialEntityList = extractMethod.getMaterialListByProjectId(queryParam.getProjectId());
        DocumentMaterialActivityInfoRet ret = new DocumentMaterialActivityInfoRet();
        if (CollectionUtils.isEmpty(documentMaterialEntityList)) {
            return ret;
        }
        //合同材料
        List<DocumentMaterialEntity> contractMaterialList = documentMaterialEntityList.stream()
                .filter(s -> s.getType().equals("0")).collect(Collectors.toList());
        //租赁单材料
        List<DocumentMaterialEntity> rentMaterialList = documentMaterialEntityList.stream()
                .filter(s -> s.getType().equals("1")).collect(Collectors.toList());
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

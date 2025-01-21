package com.zjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjyz.common.bean.RetBaseParam;
import com.zjyz.common.exception.MyBizException;
import com.zjyz.common.util.CommonUtil;
import com.zjyz.dao.MaterialMapper;
import com.zjyz.dao.UnitAmountMapper;
import com.zjyz.dao.UnitConversionMapper;
import com.zjyz.pojo.entity.*;
import com.zjyz.pojo.param.req.*;
import com.zjyz.pojo.param.ret.MaterialInfoRet;
import com.zjyz.service.MaterialService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MaterialServiceImpl implements MaterialService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
    private static List<String> unitList = new ArrayList<>();
    private static List<String> categoryList = new ArrayList<>();

    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private UnitAmountMapper unitAmountMapper;
    @Autowired
    private UnitConversionMapper unitConversionMapper;

    @Override
    public List<String> getMaterialUnit() {
        if (unitList.isEmpty()) {
            unitList = materialMapper.queryMaterialUnit();
        }
        return unitList;
    }

    @Override
    public List<String> getMaterialCategory() {
        if (categoryList.isEmpty()) {
            categoryList = materialMapper.queryMaterialCategory();
        }
        return categoryList;
    }

    @Override
    public RetBaseParam insertMaterialInfo(InsertMaterialInfoParam param) {
        String randomString = createRandomString();
        if (insertMaterialInfo(randomString, param.getCategoryName(), param.getName()) <= 0) {
            throw new MyBizException("材料新增失败,请重试", "MTAL002");
        }
        insertUnitAmountAndUnitConversion(param.getUnitAmounts(), param.getUnitConversions(), randomString);
        return new RetBaseParam(randomString);
    }

    @Override
    public boolean deleteMaterialInfo(String mid) {
        QueryWrapper<MaterialInfoEntity> infoDeleteWrapper = new QueryWrapper<>();
        infoDeleteWrapper.eq("cid", CommonUtil.getCid());
        infoDeleteWrapper.eq("mid", mid);
        if (materialMapper.delete(infoDeleteWrapper) >= 1) {
            deleteUnitAmountAndUnitConversion(mid);
        }
        return true;
    }

    @Override
    public boolean updateMaterialInfo(UpdateMaterialInfoParam param) {
        MaterialInfoEntity materialInfoEntity = new MaterialInfoEntity(param.getCategoryName(), param.getName());
        UpdateWrapper<MaterialInfoEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cid", CommonUtil.getCid()).eq("mid", param.getMid());
        if (materialMapper.update(materialInfoEntity, updateWrapper) >= 1) {
            deleteUnitAmountAndUnitConversion(param.getMid());
            insertUnitAmountAndUnitConversion(param.getUnitAmounts(), param.getUnitConversions(), param.getMid());
        }
        return true;
    }

    @Override
    public MaterialInfoRet queryMaterialInfo(QueryMaterialInfoParam param) {
        Page<MaterialInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper<MaterialInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", CommonUtil.getCid());
        queryWrapper.orderByDesc("mid");
        if (StringUtils.hasText(param.getMaterialName())) {
            queryWrapper.eq("material_name", param.getMaterialName());
        }
        if (param.getCategoryName() != null) {
            queryWrapper.eq("category_name", param.getCategoryName());
        }
        Page<MaterialInfoEntity> pageResult = materialMapper.selectPage(page, queryWrapper);
        MaterialInfoRet ret = new MaterialInfoRet();
        ret.setTotalNum(pageResult.getTotal());
        ret.setCurrent(pageResult.getCurrent());
        for (MaterialInfoEntity entity : pageResult.getRecords()) {
            MaterialInfoRet.MaterialInfo materialInfo = new MaterialInfoRet.MaterialInfo();
            BeanUtils.copyProperties(entity, materialInfo);
            QueryWrapper<UnitAmountEntity> queryAmountWrapper = new QueryWrapper<>();
            queryAmountWrapper.eq("unit_amount_id", entity.getMid());
            QueryWrapper<UnitConversionEntity> queryConversionWrapper = new QueryWrapper<>();
            queryConversionWrapper.eq("unit_conversion_id", entity.getMid());
            List<UnitConversionEntity> unitConversionEntityList = unitConversionMapper.selectList(queryConversionWrapper);
            List<UnitAmountEntity> unitAmountEntityList = unitAmountMapper.selectList(queryAmountWrapper);
            if (!CollectionUtils.isEmpty(unitAmountEntityList)) {
                unitAmountEntityList.forEach(s -> {
                    UnitAmount unitAmount = new UnitAmount();
                    BeanUtils.copyProperties(s, unitAmount);
                    materialInfo.addUnitAmount(unitAmount);
                });
            }
            if (!CollectionUtils.isEmpty(unitConversionEntityList)) {
                unitConversionEntityList.forEach(s -> {
                    UnitConversion unitConversion = new UnitConversion();
                    BeanUtils.copyProperties(s, unitConversion);
                    materialInfo.addUnitConversion(unitConversion);
                });
            }
            ret.addMaterialInfo(materialInfo);
        }
        return ret;
    }

    private int insertMaterialInfo(String mid, String categoryName, String name) {
        QueryWrapper<MaterialInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("material_name", name);
        queryWrapper.eq("category_name", categoryName);
        if (materialMapper.selectOne(queryWrapper) != null) {
            throw new MyBizException("该材料已存在,请勿重复添加", "MTAL001");
        }
        MaterialInfoEntity materialInfoEntity = new MaterialInfoEntity(CommonUtil.getCid(), mid, categoryName, name);
        return materialMapper.insert(materialInfoEntity);
    }

    private String createRandomString() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER) + RandomStringUtils.randomAlphanumeric(2);
    }


    private List<UnitAmountEntity> convertToUnitAmountEntityList(List<UnitAmount> unitAmounts, String unitAmountId) {
        if (CollectionUtils.isEmpty(unitAmounts)) {
            return new ArrayList<>();
        }
        List<UnitAmountEntity> unitAmountEntities = new ArrayList<>();
        for (UnitAmount unitAmount : unitAmounts) {
            UnitAmountEntity unitAmountEntity = new UnitAmountEntity();
            unitAmountEntity.setUnitName(unitAmount.getUnitName());
            unitAmountEntity.setUnitAmountId(unitAmountId);
            BeanUtils.copyProperties(unitAmount, unitAmountEntity);
            unitAmountEntities.add(unitAmountEntity);
        }
        return unitAmountEntities;
    }

    private List<UnitConversionEntity> convertToUnitConversionEntityList(List<UnitConversion> unitConversions, String unitConversionId) {
        if (CollectionUtils.isEmpty(unitConversions)) {
            return new ArrayList<>();
        }
        List<UnitConversionEntity> conversionEntities = new ArrayList<>();
        for (UnitConversion unitConversion : unitConversions) {
            UnitConversionEntity unitConversionEntity = new UnitConversionEntity();
            unitConversionEntity.setUnitConversionId(unitConversionId);
            unitConversionEntity.setUnitName1(unitConversion.getUnitName1());
            unitConversionEntity.setUnitName2(unitConversion.getUnitName2());
            BeanUtils.copyProperties(unitConversion, unitConversionEntity);
            conversionEntities.add(unitConversionEntity);
        }
        return conversionEntities;
    }

    private void deleteUnitAmountAndUnitConversion(String id) {
        QueryWrapper<UnitAmountEntity> amountDeleteWrapper = new QueryWrapper<>();
        QueryWrapper<UnitConversionEntity> conversionDeleteWrapper = new QueryWrapper<>();
        amountDeleteWrapper.eq("unit_amount_id", id);
        conversionDeleteWrapper.eq("unit_conversion_id", id);
        unitAmountMapper.delete(amountDeleteWrapper);
        unitConversionMapper.delete(conversionDeleteWrapper);
    }

    private void insertUnitAmountAndUnitConversion(List<UnitAmount> unitAmountList, List<UnitConversion> unitConversions, String mid) {
        List<UnitAmountEntity> unitAmountEntities = convertToUnitAmountEntityList(unitAmountList, mid);
        List<UnitConversionEntity> conversionEntities = convertToUnitConversionEntityList(unitConversions, mid);
        if (!CollectionUtils.isEmpty(unitAmountEntities)) {
            unitAmountMapper.insert(unitAmountEntities);
        }
        if (!CollectionUtils.isEmpty(conversionEntities)) {
            unitConversionMapper.insert(conversionEntities);
        }
    }
}

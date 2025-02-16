package com.zjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjyz.common.exception.MyBizException;
import com.zjyz.common.util.CommonUtil;
import com.zjyz.dao.ContractMapper;
import com.zjyz.dao.DocumentMaterialMapper;
import com.zjyz.pojo.entity.ContractEntity;
import com.zjyz.pojo.entity.DocumentMaterialEntity;
import com.zjyz.pojo.param.req.SaveContractParam;
import com.zjyz.pojo.param.ret.ContractInfoRet;
import com.zjyz.service.ContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private DocumentMaterialMapper documentMaterialMapper;

    @Autowired
    private ExtractMethod extractMethod;

    @Override
    public String saveContract(SaveContractParam saveParam) {
        ContractEntity contractEntity = new ContractEntity();
        BeanUtils.copyProperties(saveParam, contractEntity);
        BeanUtils.copyProperties(saveParam.getSettlementCycle(), contractEntity);
        QueryWrapper<ContractEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", saveParam.getProjectId());
        if (!StringUtils.hasText(saveParam.getContractId())) {
            // 插入前检查当前 projectId 下是否存在合同
            QueryWrapper<ContractEntity> checkWrapper = new QueryWrapper<>();
            checkWrapper.eq("project_id", saveParam.getProjectId());
            ContractEntity existingContract = contractMapper.selectOne(checkWrapper);
            if (existingContract != null) {
                throw new MyBizException("当前项目下已存在合同，请勿重复生成合同","CONT001");
            }
            contractEntity.setContractId(CommonUtil.createUuid());
        }
        contractMapper.insertOrUpdate(contractEntity);
        extractMethod.saveMaterial(saveParam.getMaterialContractInfoList(), contractEntity.getContractId(), "0");
        return contractEntity.getContractId();
    }

    @Override
    public ContractInfoRet queryContract(String projectId) {
        ContractInfoRet ret = new ContractInfoRet();
        QueryWrapper<ContractEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        ContractEntity contractEntity = contractMapper.selectOne(queryWrapper);
        if(contractEntity == null){
            return null;
        }
        List<DocumentMaterialEntity> materialEntityList = extractMethod.getMaterialListByDocumentId(contractEntity.getContractId());
        BeanUtils.copyProperties(contractEntity, ret);
        ret.setSettlementCycle(contractEntity.getHeadOrEnd(), contractEntity.getUpperLimitReminder(), contractEntity.getReconciliationPeriod(), contractEntity.getEnableExpandPeriodFlag());
        ret.setMaterialInfoList(materialEntityList);
        return ret;
    }
}

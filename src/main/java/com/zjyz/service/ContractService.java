package com.zjyz.service;

import com.zjyz.pojo.param.req.SaveContractParam;
import com.zjyz.pojo.param.ret.ContractInfoRet;

public interface ContractService {
    String saveContract(SaveContractParam saveParam);
    ContractInfoRet queryContract(String projectId);
}

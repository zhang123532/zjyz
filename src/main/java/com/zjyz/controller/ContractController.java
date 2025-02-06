package com.zjyz.controller;

import com.zjyz.common.annotation.ZeeController;
import com.zjyz.pojo.param.req.SaveContractParam;
import com.zjyz.pojo.param.ret.ContractInfoRet;
import com.zjyz.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ZeeController
@RequestMapping("/contract")
@Api(tags = "合同管理")
public class ContractController {
    @Autowired
    private ContractService contractService;
    // 保存合同
    @PostMapping("/saveContract")
    @ApiOperation(value = "保存合同")
    public String saveContract(@RequestBody SaveContractParam saveParam) {
        return contractService.saveContract(saveParam);
    }

    // 查询合同信息
    @GetMapping("/queryContract")
    @ApiOperation(value = "查询合同信息")
    public ContractInfoRet queryContract(@RequestParam String projectId) {
        return contractService.queryContract(projectId);
    }
}

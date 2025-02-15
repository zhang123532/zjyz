package com.zjyz.controller;

import com.zjyz.common.annotation.ZeeController;
import com.zjyz.common.bean.RetBaseParam;
import com.zjyz.pojo.param.req.*;
import com.zjyz.pojo.param.ret.*;
import com.zjyz.service.CompensationDocumentService;
import com.zjyz.service.RentDocumentService;
import com.zjyz.service.ReturnDocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ZeeController
@RequestMapping("/document")
@Api(tags = "单据管理")
public class DocumentController {
    @Autowired
    private CompensationDocumentService compensationDocumentService;

    @Autowired
    private RentDocumentService rentDocumentService;

    @Autowired
    private ReturnDocumentService returnDocumentService;

    /**
     * 保存补偿文档
     */
    @PostMapping("/saveCompensationDocument")
    @ApiOperation(value = "保存补偿文档")
    public RetBaseParam saveCompensationDocument(@RequestBody SaveCompensationDocumentParam param) {
        String id = compensationDocumentService.saveCompensationDocument(param);
        return new RetBaseParam(id);
    }

    /**
     * 查询补偿文档信息
     */
    @GetMapping("/queryCompensationDocument")
    @ApiOperation(value = "查询补偿文档信息")
    public CompensationInfoRet queryCompensationDocument(@RequestParam String compensationDocumentId) {
        return compensationDocumentService.queryCompensationDocument(compensationDocumentId);
    }

    /**
     * 查询补偿材料信息
     */
    @PostMapping("/queryCompensationMaterialInfo")
    @ApiOperation(value = "查询补偿材料信息")
    public DocumentMaterialCompensationInfoRet queryCompensationMaterialInfo(@RequestBody QueryDocumentMaterialInfoParam queryParam) {
        return compensationDocumentService.queryCompensationMaterialInfo(queryParam);
    }

    // RentDocumentService 相关接口

    /**
     * 查询文档列表
     */
    @PostMapping("/queryDocumentList")
    @ApiOperation(value = "查询文档列表")
    public DocumentListInfoRet queryDocumentList(@RequestBody QueryDocumentListParam queryParam) {
        return rentDocumentService.queryDocumentList(queryParam);
    }

    /**
     * 保存租赁文档
     */
    @PostMapping("/saveRentDocument")
    @ApiOperation(value = "保存租赁文档")
    public RetBaseParam saveRentDocument(@RequestBody SaveRentDocumentParam param) {
        String id = rentDocumentService.saveRentDocument(param);
        return new RetBaseParam(id);
    }

    /**
     * 查询租赁文档信息
     */
    @GetMapping("/queryRentDocument")
    @ApiOperation(value = "查询租赁文档信息")
    public RentInfoRet queryRentDocument(@RequestParam String rentDocumentId) {
        return rentDocumentService.queryRentDocument(rentDocumentId);
    }

    /**
     * 查询租赁材料信息
     */
    @PostMapping("/queryRentMaterialInfo")
    @ApiOperation(value = "查询租赁材料信息")
    public DocumentMaterialActivityInfoRet queryRentMaterialInfo(@RequestBody QueryDocumentMaterialInfoParam queryParam) {
        return rentDocumentService.queryRentMaterialInfo(queryParam);
    }

    // ReturnDocumentService 相关接口

    /**
     * 保存归还文档
     */
    @PostMapping("/saveReturnDocument")
    @ApiOperation(value = "保存归还文档")
    public RetBaseParam saveReturnDocument(@RequestBody SaveReturnDocumentParam param) {
        String id = returnDocumentService.saveReturnDocument(param);
        return new RetBaseParam(id);
    }

    /**
     * 查询归还文档信息
     */
    @GetMapping("/queryReturnDocument")
    @ApiOperation(value = "查询归还文档信息")
    public ReturnInfoRet queryReturnDocument(@RequestParam String returnDocumentId) {
        return returnDocumentService.queryReturnDocument(returnDocumentId);
    }

    /**
     * 查询归还材料信息
     */
    @PostMapping("/queryReturnMaterialInfo")
    @ApiOperation(value = "查询归还材料信息")
    public DocumentMaterialActivityInfoRet queryReturnMaterialInfo(@RequestBody QueryDocumentMaterialInfoParam queryParam) {
        return returnDocumentService.queryReturnMaterialInfo(queryParam);
    }
}

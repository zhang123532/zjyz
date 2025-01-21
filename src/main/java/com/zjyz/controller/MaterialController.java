package com.zjyz.controller;

import com.zjyz.common.annotation.ZeeController;
import com.zjyz.common.bean.RetBaseParam;
import com.zjyz.pojo.entity.MaterialCategoryEntity;
import com.zjyz.pojo.entity.MaterialUnitEntity;
import com.zjyz.pojo.param.req.InsertMaterialInfoParam;
import com.zjyz.pojo.param.req.QueryMaterialInfoParam;
import com.zjyz.pojo.param.req.UpdateMaterialInfoParam;
import com.zjyz.pojo.param.ret.MaterialInfoRet;
import com.zjyz.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ZeeController
@RequestMapping("/material")
@Api(tags = "材料管理")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping("get-material-unit")
    @ApiOperation(value = "获取材料单位列表")
    public List<String> getMaterialUnit() {
        return materialService.getMaterialUnit();
    }
    @ApiOperation(value = "获取材料类别列表")
    @GetMapping("get-material-category")
    public List<String> getMaterialCategory() {
        return materialService.getMaterialCategory();
    }

    @ApiOperation(value = "新增材料信息")
    @PostMapping("insert-material-info")
    public RetBaseParam insertMaterialInfo(@RequestBody InsertMaterialInfoParam param) {
        return materialService.insertMaterialInfo(param);
    }

    @ApiOperation(value = "删除材料信息")
    @GetMapping("delete-material-info")
    public Boolean deleteMaterialInfo(@RequestParam String mid) {
        return materialService.deleteMaterialInfo(mid);
    }
    @ApiOperation(value = "修改材料信息")
    @PostMapping("update-material-info")
    public Boolean updateMaterialInfo(@RequestBody UpdateMaterialInfoParam param) {
        return materialService.updateMaterialInfo(param);
    }

    @ApiOperation(value = "查询材料信息")
    @PostMapping("query-material-info")
    public MaterialInfoRet queryMaterialInfo(@RequestBody QueryMaterialInfoParam param) {
        return materialService.queryMaterialInfo(param);
    }



}

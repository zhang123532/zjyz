package com.zjyz.service;

import com.zjyz.common.bean.RetBaseParam;
import com.zjyz.pojo.entity.MaterialCategoryEntity;
import com.zjyz.pojo.entity.MaterialUnitEntity;
import com.zjyz.pojo.param.req.InsertMaterialInfoParam;
import com.zjyz.pojo.param.req.QueryMaterialInfoParam;
import com.zjyz.pojo.param.req.UpdateMaterialInfoParam;
import com.zjyz.pojo.param.ret.MaterialInfoRet;

import java.util.List;

public interface MaterialService {
    List<String> getMaterialUnit();

    List<String> getMaterialCategory();

    RetBaseParam insertMaterialInfo(InsertMaterialInfoParam param);

    boolean deleteMaterialInfo(String mid);

    boolean updateMaterialInfo(UpdateMaterialInfoParam param);

    MaterialInfoRet queryMaterialInfo(QueryMaterialInfoParam param);
}

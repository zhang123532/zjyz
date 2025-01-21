package com.zjyz.service;

import com.zjyz.pojo.param.req.QueryDocumentMaterialInfoParam;
import com.zjyz.pojo.param.req.SaveRentDocumentParam;
import com.zjyz.pojo.param.req.SaveReturnDocumentParam;
import com.zjyz.pojo.param.ret.DocumentMaterialActivityInfoRet;
import com.zjyz.pojo.param.ret.RentInfoRet;
import com.zjyz.pojo.param.ret.ReturnInfoRet;

import java.util.List;

public interface ReturnDocumentService {
    String saveReturnDocument(SaveReturnDocumentParam param);

    ReturnInfoRet queryReturnDocument(String returnDocumentId);

    DocumentMaterialActivityInfoRet queryReturnMaterialInfo(QueryDocumentMaterialInfoParam queryParam);

}

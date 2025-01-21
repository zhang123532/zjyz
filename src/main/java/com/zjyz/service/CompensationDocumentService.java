package com.zjyz.service;

import com.zjyz.pojo.param.req.QueryDocumentMaterialInfoParam;
import com.zjyz.pojo.param.req.SaveCompensationDocumentParam;
import com.zjyz.pojo.param.req.SaveRentDocumentParam;
import com.zjyz.pojo.param.ret.CompensationInfoRet;
import com.zjyz.pojo.param.ret.DocumentMaterialActivityInfoRet;
import com.zjyz.pojo.param.ret.DocumentMaterialCompensationInfoRet;
import com.zjyz.pojo.param.ret.RentInfoRet;

public interface CompensationDocumentService {
    String saveCompensationDocument(SaveCompensationDocumentParam param);

    CompensationInfoRet queryCompensationDocument(String compensationDocumentId);


    DocumentMaterialCompensationInfoRet queryCompensationMaterialInfo(QueryDocumentMaterialInfoParam queryParam);
}

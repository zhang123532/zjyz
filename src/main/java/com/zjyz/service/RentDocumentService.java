package com.zjyz.service;

import com.zjyz.pojo.param.req.QueryDocumentListParam;
import com.zjyz.pojo.param.req.QueryDocumentMaterialInfoParam;
import com.zjyz.pojo.param.req.SaveRentDocumentParam;
import com.zjyz.pojo.param.ret.DocumentListInfoRet;
import com.zjyz.pojo.param.ret.DocumentMaterialActivityInfoRet;
import com.zjyz.pojo.param.ret.RentInfoRet;

import java.util.List;

public interface RentDocumentService {
    DocumentListInfoRet queryDocumentList(QueryDocumentListParam queryParam);

    String saveRentDocument(SaveRentDocumentParam param);

    RentInfoRet queryRentDocument(String rentDocumentId);

    DocumentMaterialActivityInfoRet queryRentMaterialInfo(QueryDocumentMaterialInfoParam queryParam);

}

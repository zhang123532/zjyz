package com.zjyz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjyz.pojo.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialMapper extends BaseMapper<MaterialInfoEntity> {
    List<String> queryMaterialUnit();

    List<String> queryMaterialCategory();

}
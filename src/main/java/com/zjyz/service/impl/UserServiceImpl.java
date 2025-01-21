package com.zjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zjyz.common.exception.MyBizException;
import com.zjyz.common.exception.MyDatabaseException;
import com.zjyz.dao.CompanyMapper;
import com.zjyz.dao.UserMapper;
import com.zjyz.pojo.entity.CompanyEntity;
import com.zjyz.pojo.entity.UserEntity;
import com.zjyz.pojo.param.req.LoginParam;
import com.zjyz.pojo.param.req.UpdatePasswordParam;
import com.zjyz.pojo.param.req.UserRegisterParam;
import com.zjyz.pojo.param.ret.LoginRet;
import com.zjyz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public String userRegister(UserRegisterParam param) {
        if (StringUtils.hasText(queryUidByName(param.getUserName()))) {
            throw new MyDatabaseException("用户名已存在", "USER001");
        }
        String uid = UUID.randomUUID().toString().replace("-", "");
        String cid = getCompanyIdByName(param.getCompanyName());
        UserEntity userEntity = new UserEntity(uid, cid, param.getUserName(), param.getPassword(), param.getMobileNumber());
        int result = userMapper.insert(userEntity);
        if (result <= 0) {
            throw new MyDatabaseException("用户创建失败", "USER001");
        }
        return uid;
    }

    @Override
    public LoginRet userLogin(LoginParam param) {
        UserEntity user = login(param.getUserName(), param.getPassword());
        if (user == null) {
            throw new MyBizException("用户名或密码错误", "USER002");
        }
        return new LoginRet(user.getUid(), user.getCid());
    }

    @Override
    public boolean updatePassword(UpdatePasswordParam param) {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", param.getUserName()).eq("password", param.getOldPassword());
        UserEntity user = new UserEntity();
        user.setPassword(param.getNewPassword());
        int updateResult = userMapper.update(user, updateWrapper);
        if( updateResult <= 0){
            throw new MyBizException("请检查用户名和密码是否正确", "USER002");
        }
        return true;
    }

    private UserEntity login(String userName, String password) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("password", password);
        return userMapper.selectOne(queryWrapper);
    }

    private String queryUidByName(String userName) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity != null) {
            return userEntity.getUid();
        }
        return "";
    }

    private String getCompanyIdByName(String companyName) {
        QueryWrapper<CompanyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_name", companyName);
        CompanyEntity companyEntity = companyMapper.selectOne(queryWrapper);
        if (companyEntity != null) {
            return companyEntity.getCid();
        } else {
            String cid = UUID.randomUUID().toString().replace("-", "");
            companyEntity = new CompanyEntity(cid, companyName);
            companyMapper.insert(companyEntity);
            return cid;
        }
    }


}

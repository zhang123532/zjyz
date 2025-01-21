package com.zjyz.service;

import com.zjyz.pojo.param.req.LoginParam;
import com.zjyz.pojo.param.req.UpdatePasswordParam;
import com.zjyz.pojo.param.req.UserRegisterParam;
import com.zjyz.pojo.param.ret.LoginRet;

public interface UserService {

    String userRegister(UserRegisterParam param);

    LoginRet userLogin(LoginParam param);

    boolean updatePassword(UpdatePasswordParam param);
}

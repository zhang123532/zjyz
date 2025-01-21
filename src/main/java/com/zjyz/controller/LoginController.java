package com.zjyz.controller;

import com.zjyz.common.annotation.ZeeController;
import com.zjyz.common.bean.RetBaseParam;
import com.zjyz.pojo.param.req.LoginParam;
import com.zjyz.pojo.param.req.UpdatePasswordParam;
import com.zjyz.pojo.param.req.UserRegisterParam;
import com.zjyz.pojo.param.ret.LoginRet;
import com.zjyz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ZeeController
@RequestMapping("/user")
@Api(tags = "用户服务")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "注册用户")
    public RetBaseParam userRegister(@RequestBody UserRegisterParam param) {
        String uid = userService.userRegister(param);
        return new RetBaseParam(uid);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public LoginRet login(@RequestBody LoginParam param) {
        return userService.userLogin(param);
    }
    @ApiOperation(value = "修改密码")
    @PostMapping("update-password")
    public boolean updatePassword(@RequestBody UpdatePasswordParam param){
        return userService.updatePassword(param);
    }
}

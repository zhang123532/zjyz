package com.zjyz.pojo.param.req;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterParam {
    private String userName;
    private String password;
    private String mobileNumber;
    private String companyName;
}

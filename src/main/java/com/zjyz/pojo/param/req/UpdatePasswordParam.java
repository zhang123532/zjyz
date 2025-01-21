package com.zjyz.pojo.param.req;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePasswordParam {
    private String userName;
    private String oldPassword;
    private String newPassword;
}

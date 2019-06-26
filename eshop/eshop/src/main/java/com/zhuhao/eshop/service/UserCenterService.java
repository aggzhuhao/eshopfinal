package com.zhuhao.eshop.service;

import com.zhuhao.eshop.entity.User;

public interface UserCenterService {
    //修改密码
    int updateUserpassword(User user);

    //忘记密码
    int forgetmailpassword(User user);

    //校验数据库是否存在相同的账号
    User checkemail(String email);
}

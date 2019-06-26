package com.zhuhao.eshop.service;

import com.zhuhao.eshop.entity.User;

/**
 * service 接口
 */
public interface LoginService {
    /*
     * 根据用户判断是否可以登陆
     */
    User selectUserByIdPsw(User user);

    /*
     * 判断数据库是否存在用户
     */
    boolean checkusername(String username);

    /*
     *注册账号
     */
    boolean createUser(User user);
}
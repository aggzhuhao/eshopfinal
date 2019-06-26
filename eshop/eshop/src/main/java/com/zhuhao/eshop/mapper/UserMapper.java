package com.zhuhao.eshop.mapper;

import com.zhuhao.eshop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * mybatis mapper --> UserMapper.xml
 * 为service层 提供数据
 */
@Repository
public interface UserMapper {
    //根据账号密码登录
    User selectUserByIdPsw(User user);

    //校验数据库是否存在相同的账号
    User checkusername(String username);

    //注册账号
    int createUser(User user);

    //修改密码
    int updateUserpassword(User user);

    //忘记密码
    int forgetmailpassword(User user);

    //校验数据库是否存在相同的账号
    User checkemail(String email);
}
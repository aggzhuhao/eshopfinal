package com.zhuhao.eshop.serviceImpl;

import com.zhuhao.eshop.entity.User;
import com.zhuhao.eshop.mapper.UserMapper;
import com.zhuhao.eshop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service 业务逻辑
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    public User selectUserByIdPsw(User user){
        User user1 =userMapper.selectUserByIdPsw(user);
        return user1;
    }

    public boolean checkusername(String username) {
        User user = userMapper.checkusername(username);
        if(user == null){
            return true;
        }
        return false;
    }

    public boolean createUser(User user) {
        int count = userMapper.createUser(user);
        if(count >0){
            return true;
        }

        return false;
    }


}

package com.jk2102.service.impl;

import com.jk2102.mapper.LoginMapper;
import com.jk2102.pojo.Users;
import com.jk2102.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Users login(Users users) {
        return loginMapper.getByUsernameAndPassword(users);
    }
}

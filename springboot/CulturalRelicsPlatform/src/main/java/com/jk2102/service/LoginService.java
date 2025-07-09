package com.jk2102.service;

import com.jk2102.pojo.Users;

public interface LoginService {
    /**
     * 登录
     * @param users
     * @return
     */
    Users login(Users users);
}

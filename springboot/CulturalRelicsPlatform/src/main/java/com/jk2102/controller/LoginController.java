package com.jk2102.controller;

import com.jk2102.pojo.Result;
import com.jk2102.pojo.Users;
import com.jk2102.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result Login(@RequestBody Users users) {
        log.info("员工登录: {}", users);
        Users u = loginService.login(users);
        return u != null?Result.success():Result.error("用户名或密码错误");
    }
}

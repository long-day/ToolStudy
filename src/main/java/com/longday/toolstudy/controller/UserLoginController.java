package com.longday.toolstudy.controller;

import com.longday.toolstudy.domain.FdUser;
import com.longday.toolstudy.service.UserLoginService;
import com.longday.toolstudy.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 君
 * @version 1.0
 * @date 2022/7/30 9:13
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody() FdUser fdUser){
        return userLoginService.login(fdUser);
    }
}

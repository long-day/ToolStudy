package com.longday.toolstudy.service.impl;

import com.longday.toolstudy.domain.FdUser;
import com.longday.toolstudy.domain.LoginUser;
import com.longday.toolstudy.service.UserLoginService;
import com.longday.toolstudy.utils.JwtUtil;
import com.longday.toolstudy.utils.RedisCache;
import com.longday.toolstudy.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author 君
 * @version 1.0
 * @date 2022/7/30 9:17
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(FdUser fdUser) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(fdUser.getUserName(), fdUser.getUserPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(Objects.isNull(authenticate)){
            throw new RuntimeException("账号或密码错误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String username = loginUser.getUsername();
        String jwt = JwtUtil.createJwt(username);
        //存入Redis
        redisCache.setCacheObject(username, loginUser);

        //响应数据给前端
        HashMap<String, String> dateMap = new HashMap<>(5);
        dateMap.put("token",jwt);
        return new ResponseResult<>(200,"验证成功",dateMap);
    }
}

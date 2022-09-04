package com.longday.toolstudy.handle;

import com.longday.toolstudy.utils.JacksonUtils;
import com.longday.toolstudy.utils.WebUtils;
import com.longday.toolstudy.vo.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 君
 * @version 1.0
 * @date 2022/7/31 10:26
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败,请重新登陆");
        //处理异常
        WebUtils.renderString(response, JacksonUtils.getMapper().writeValueAsString(result));
    }
}

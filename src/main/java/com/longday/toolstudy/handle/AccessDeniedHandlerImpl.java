package com.longday.toolstudy.handle;

import com.longday.toolstudy.utils.JacksonUtils;
import com.longday.toolstudy.utils.WebUtils;
import com.longday.toolstudy.vo.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 君
 * @version 1.0
 * @date 2022/7/31 10:37
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足,请联系管理员");
        //处理异常
        WebUtils.renderString(response, JacksonUtils.getMapper().writeValueAsString(result));
    }
}

package com.longday.toolstudy.service;

import com.longday.toolstudy.domain.FdUser;
import com.longday.toolstudy.vo.ResponseResult;

/**
 * @author 君
 * @version 1.0
 * @date 2022/7/30 9:16
 */
public interface UserLoginService {


    /**
     * 登录
     * @param fdUser 实体用户
     * @return 响应数据
     */
    ResponseResult login(FdUser fdUser);
}

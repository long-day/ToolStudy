package com.longday.toolstudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.longday.toolstudy.domain.FdUser;
import com.longday.toolstudy.domain.LoginUser;
import com.longday.toolstudy.mapper.FdMenuMapper;
import com.longday.toolstudy.mapper.FdUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author 君
 * @version 1.0
 * @date 2022/7/29 21:50
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FdUserMapper fdUserMapper;

    @Autowired
    private FdMenuMapper fdMenuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<FdUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FdUser::getUserName,username);
        FdUser fdUser = fdUserMapper.selectOne(queryWrapper);

        if (Objects.isNull(fdUser)){
            throw new UsernameNotFoundException("账号或密码错误");
        }

        // 获取权限数组并返回LoginUser对象
        List<String> userAuthList = fdMenuMapper.selectPermsByUserId(fdUser.getUserId());
        return new LoginUser(fdUser,userAuthList);
    }
}

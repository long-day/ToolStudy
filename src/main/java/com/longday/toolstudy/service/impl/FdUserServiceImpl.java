package com.longday.toolstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longday.toolstudy.domain.FdUser;
import com.longday.toolstudy.service.FdUserService;
import com.longday.toolstudy.mapper.FdUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 君
* @description 针对表【fd_user(用户表)】的数据库操作Service实现
* @createDate 2022-07-29 21:46:41
*/
@Service
public class FdUserServiceImpl extends ServiceImpl<FdUserMapper, FdUser>
    implements FdUserService{

}





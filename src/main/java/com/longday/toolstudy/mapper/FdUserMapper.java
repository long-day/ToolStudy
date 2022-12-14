package com.longday.toolstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longday.toolstudy.domain.FdUser;
import org.springframework.stereotype.Repository;

/**
* @author 君
* @description 针对表【fd_user(用户表)】的数据库操作Mapper
* @createDate 2022-07-29 21:46:41
* @Entity com.longday.toolstudy.domain.FdUser
*/
@Repository
public interface FdUserMapper extends BaseMapper<FdUser> {

}





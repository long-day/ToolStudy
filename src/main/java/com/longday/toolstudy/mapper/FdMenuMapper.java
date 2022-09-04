package com.longday.toolstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longday.toolstudy.domain.FdMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 君
* @description 针对表【fd_menu(菜单表)】的数据库操作Mapper
* @createDate 2022-07-29 22:07:43
* @Entity com.longday.toolstudy.domain.FdMenu
*/
@Repository
public interface FdMenuMapper extends BaseMapper<FdMenu> {
    /**
     * 根据用户ID获取用户全部权限
     * @param userId 用户ID
     * @return 权限集合
     */
   List<String> selectPermsByUserId(Long userId);
}





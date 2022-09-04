package com.longday.toolstudy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 * @TableName fd_menu
 */
@TableName(value ="fd_menu")
@Data
public class FdMenu implements Serializable {
    /**
     * 菜单Id
     */
    @TableId(type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 路由地址
     */
    private String menuPath;

    /**
     * 组件路径
     */
    private String menuComponent;

    /**
     * 菜单状态(0显示 1隐藏)
     */
    private String menuVisible;

    /**
     * 菜单状态(0正常 1停用)
     */
    private String menuStatus;

    /**
     * 权限标识
     */
    private String menuPerms;

    /**
     * 
     */
    private Date createTime;

    /**
     * 删除标志(0代表未删除，1代表已删除)
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
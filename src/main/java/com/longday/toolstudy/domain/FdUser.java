package com.longday.toolstudy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @author 君
 * @TableName fd_user
 */
@TableName(value ="fd_user")
@Data
public class FdUser implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 账号状态(0正常 1停用)
     */
    private String userStatus;

    /**
     * 手机号
     */
    private String userPhonenumber;

    /**
     * 用户性别(0男，1女，2未知)
     */
    private String userSex;

    /**
     * 用户类型(0管理员，1普通用户)
     */
    private String userType;

    /**
     * 创建时间
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
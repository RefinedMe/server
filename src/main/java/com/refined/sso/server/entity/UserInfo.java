package com.refined.sso.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * userInfo
 *
 * @author zhang
 * @create 2018-10-18 15:00
 **/
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -2327576561811731823L;
    /**
     * 用户基本信息
     */
    //private SysUser sysUser;
    /**
     * 权限标识集合
     */
    private String[] permissions;
    /**
     * 角色集合
     */
    private String[] roles;
    /**
     * 用户基本信息
     */
    private BaseMaccount baseMaccount;
}

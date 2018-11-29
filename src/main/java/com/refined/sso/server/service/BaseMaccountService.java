package com.refined.sso.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.refined.sso.server.entity.BaseMaccount;
import com.refined.sso.server.entity.UserInfo;

/**
 * 用户表
 *
 * @author zhang
 * @create 2018-11-06 10:25
 **/
public interface BaseMaccountService extends IService<BaseMaccount> {
    /**
     * 查询用户信息
     *
     * @param username 用户名
     * @return userInfo
     */
    UserInfo findUserInfo(String username);
}

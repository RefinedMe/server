package com.refined.sso.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.refined.sso.server.entity.BaseMrole;

import java.util.List;

/**
 * 用户角色表
 *
 * @author zhang
 * @create 2018-11-06 10:51
 **/
public interface BaseMroleService extends IService<BaseMrole> {
    /**
     * 通过用户ID，查询角色信息
     *
     * @param maAid
     * @return
     */
    List<BaseMrole> findRolesByUserId(String maAid);
}

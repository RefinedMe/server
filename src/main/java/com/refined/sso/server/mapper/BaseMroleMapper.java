package com.refined.sso.server.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.refined.sso.server.entity.BaseMrole;

import java.util.List;

/**
 * 用户角色Mapper接口
 *
 * @author zhang
 * @create 2018-11-06 10:56
 **/
public interface BaseMroleMapper extends BaseMapper<BaseMrole> {

    /**
     * 通过用户ID，查询角色信息
     *
     * @param maAid
     * @return
     */
    List<BaseMrole> findRolesByUserId(String maAid);
}

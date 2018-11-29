package com.refined.sso.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refined.sso.server.entity.BaseMaccount;
import com.refined.sso.server.entity.BaseMrole;
import com.refined.sso.server.entity.UserInfo;
import com.refined.sso.server.mapper.BaseMaccountMapper;
import com.refined.sso.server.service.BaseMaccountService;
import com.refined.sso.server.service.BaseMroleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户表
 *
 * @author zhang
 * @create 2018-11-06 12:10
 **/
@Service
@AllArgsConstructor
public class BaseMaccountServiceImpl extends ServiceImpl<BaseMaccountMapper, BaseMaccount> implements BaseMaccountService {
    private final BaseMroleService baseMroleService;

    @Override
    public UserInfo findUserInfo(String username) {
        //SysUser condition = new SysUser();
        BaseMaccount condition = new BaseMaccount();
        condition.setMaMobile(username);
        //SysUser sysUser = this.getOne(new QueryWrapper<>(condition));
        BaseMaccount baseMaccount = this.getOne(new QueryWrapper<>(condition));
        if (baseMaccount == null) {
            return null;
        }

        UserInfo userInfo = new UserInfo();
        //userInfo.setSysUser(sysUser);
        userInfo.setBaseMaccount(baseMaccount);
        //设置角色列表
        List<BaseMrole> baseMroleList = baseMroleService.findRolesByUserId(baseMaccount.getMaAid());
        //List<SysRole> roleList = sysRoleService.findRolesByUserId(baseMaccount.getUuid());
        List<String> roleCodes = new ArrayList<>();
        if (CollUtil.isNotEmpty(baseMroleList)) {
            baseMroleList.forEach(baseMrole -> roleCodes.add(baseMrole.getMrId()));
        }
        userInfo.setRoles(ArrayUtil.toArray(roleCodes, String.class));
        userInfo.setRoles(new String[]{"ROLE_ADMIN"});
        userInfo.setPermissions(new String[]{"sys_dict_add", "sys_user_del", "sys_dept_del", "sys_menu_add", "sys_role_add", "sys_client_add", "sys_dept_edit", "sys_route_add", "sys_user_upd", "sys_menu_del", "sys_menu_edit", "sys_route_del", "sys_role_edit", "sys_client_upd", "sys_role_perm", "sys_route_upd", "sys_user_add", "sys_dept_add", "sys_dict_upd", "sys_role_del", "sys_log_del", "sys_dict_del", "sys_client_del"});
        return userInfo;
    }
}

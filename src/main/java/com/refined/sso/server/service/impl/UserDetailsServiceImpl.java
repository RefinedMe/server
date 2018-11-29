package com.refined.sso.server.service.impl;


import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.refined.sso.server.entity.BaseMaccount;
import com.refined.sso.server.entity.CalfUser;
import com.refined.sso.server.entity.UserInfo;
import com.refined.sso.server.service.BaseMaccountService;
import com.refined.sso.server.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhang
 * @create 2018-11-19 14:19
 **/
@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements MyUserDetailsService {
    private final BaseMaccountService baseMaccountService;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //SysUser condition = new SysUser();
        BaseMaccount condition = new BaseMaccount();
        condition.setMaMobile(username);
        //SysUser sysUser = this.getOne(new QueryWrapper<>(condition));
        BaseMaccount baseMaccount = baseMaccountService.getOne(new QueryWrapper<>(condition));
        if (baseMaccount == null) {
            return null;
        }

        UserInfo userInfo = new UserInfo();
        //userInfo.setSysUser(sysUser);
        userInfo.setBaseMaccount(baseMaccount);
        //设置角色列表
        //List<BaseMrole> baseMroleList = baseMroleService.findRolesByUserId(baseMaccount.getMaAid());
        //List<SysRole> roleList = sysRoleService.findRolesByUserId(baseMaccount.getUuid());
        List<String> roleCodes = new ArrayList<>();
        //if (CollUtil.isNotEmpty(baseMroleList)) {
        //    baseMroleList.forEach(baseMrole -> roleCodes.add(baseMrole.getMrId()));
        //}
        userInfo.setRoles(ArrayUtil.toArray(roleCodes, String.class));
        userInfo.setRoles(new String[]{"ROLE_ADMIN"});
        userInfo.setPermissions(new String[]{"sys_dict_add", "sys_user_del", "sys_dept_del", "sys_menu_add", "sys_role_add", "sys_client_add", "sys_dept_edit", "sys_route_add", "sys_user_upd", "sys_menu_del", "sys_menu_edit", "sys_route_del", "sys_role_edit", "sys_client_upd", "sys_role_perm", "sys_route_upd", "sys_user_add", "sys_dept_add", "sys_dict_upd", "sys_role_del", "sys_log_del", "sys_dict_del", "sys_client_del"});
        return getUserDetails(userInfo);
    }

    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(UserInfo result) {

        UserInfo info = result;
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add("ROLE_" + role));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        //SysUser user = info.getSysUser();
        BaseMaccount user = info.getBaseMaccount();

        // 构造security用户
        return new CalfUser(user.getMaAid(), null, user.getMaMobile(), user.getMaPassword(),
                true, true, true, true, authorities);
        //StrUtil.equals(user.getDelFlag(), CommonConstant.STATUS_NORMAL)
        //SecurityConstants.BCRYPT + user.getMaPassword()
    }
}

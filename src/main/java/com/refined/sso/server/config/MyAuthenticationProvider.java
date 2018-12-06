package com.refined.sso.server.config;

import com.refined.sso.server.service.MyUserDetailsService;
import com.refined.sso.server.util.BCrypt;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 提供自定义验证密码方式
 */
@Component
@AllArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {
    private MyUserDetailsService userDetailsService;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        System.out.println("前端传过来的明文密码:" + password);
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            return null;
        }
        //加密过程在这里体现
        System.out.println("已经查询出来的数据库存储密码:" + user.getPassword());
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new DisabledException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}

package com.refined.sso.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http
                .logout()
                .and()
                .cors().and()
                .formLogin()
                .loginPage("/token/login")
                .loginProcessingUrl("/token/form").permitAll()
                .and()
                .requestMatchers()
                .antMatchers(
                        "/login/**",
                        "/actuator/**",
                        "/oauth/**", "/token/**","/css/**","/images/**","/**/favicon.ico")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and().csrf().disable();
    }

    /**
     * 不拦截静态资源
     *
     * @param web web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**","/images/**","/**/favicon.ico");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

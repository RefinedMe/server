package com.refined.sso.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("SampleClientId")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("authorization_code")
                .scopes("user_info")
                .autoApprove(true)
                .redirectUris("http://localhost:8082/ui/login", "http://localhost:8083/ui2/login",
                        "http://localhost:8082/login","http://refinedall.cn:8082/ui/login",
                        "http://refinedall.cn:8082/login","http://167f6370p4.iok.la/ui/login")
                .and()
                .withClient("pig")
                .secret(passwordEncoder.encode("pig"))
                .authorizedGrantTypes("authorization_code")
                .scopes("server")
                .autoApprove(true)
                .redirectUris("http://refinedall.cn:8088/oauth-authorized/egaSSO",
                        "http://localhost:4040/sso1/login",
                        "http://39.105.198.37:8088/oauth-authorized/egaSSO")
        // .accessTokenValiditySeconds(3600)
        ; // 1 hour
    }


}

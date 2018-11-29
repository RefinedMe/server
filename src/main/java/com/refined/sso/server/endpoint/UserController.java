package com.refined.sso.server.endpoint;


import com.refined.sso.server.entity.UserInfo;
import com.refined.sso.server.service.BaseMaccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@AllArgsConstructor
public class UserController {
    private BaseMaccountService baseMaccountService;
    @RequestMapping("user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
    @RequestMapping("user/info")
    public UserInfo info(Principal principal) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        System.out.println(principal);
        return baseMaccountService.findUserInfo(principal.getName());
    }
    /**
     * 认证页面
     *
     * @return ModelAndView
     */
    @GetMapping("/token/login")
    public ModelAndView require(HttpServletRequest request) {
        System.out.println("1,,,,, " + request.getSession().getId());
        return new ModelAndView("ftl/login");
    }
}

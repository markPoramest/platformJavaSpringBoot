package com.training.platform.config;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    HttpSession session;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException,
            ServletException {
        boolean hasUserRole = false;
        boolean hasAdminRole = false;

        System.out.println("SESSION : " + authentication.getName());
        session.setAttribute("session-name",authentication.getName().toString());
        System.out.println("########### SESSION USER START ##############");
        System.out.println(session.getAttribute("session-name"));
        System.out.println("########### SESSION USER END ##############");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("USER")) {
                hasUserRole = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ADMIN")) {
                hasAdminRole = true;
                break;
            }
        }
        if (hasUserRole) {
            redirectStrategy.sendRedirect(request, response, "/api/user");
        } else if (hasAdminRole) {
            redirectStrategy.sendRedirect(request, response, "/admin/user");
        } else {
            throw new IllegalStateException();
        }
    }
}


package org.shop.config;

import org.shop.data.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }

        String requestURI = request.getRequestURI();
        if ((requestURI.equals("/edit") || requestURI.equals("/remove"))
                && !user.getRole().getRoleName().equals("admin")){
            response.setStatus(SC_METHOD_NOT_ALLOWED);
            response.sendRedirect("/index");
            return false;
        }
        return true;
    }

}
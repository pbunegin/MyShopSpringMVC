package org.shop.config;

import org.shop.data.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        String requestURI = request.getRequestURI();
        if ((requestURI.equals(request.getContextPath() + "/edit") || requestURI.equals(request.getContextPath() + "/remove"))
                && !user.getRole().getRoleName().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        }
        return true;
    }

}
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

        if (request.getRequestURI().equals("/edit") && !user.getRole().equals("admin")){
            response.sendRedirect("/index");
            response.setStatus(SC_METHOD_NOT_ALLOWED);
            return false;
        }
        return true;
    }

}
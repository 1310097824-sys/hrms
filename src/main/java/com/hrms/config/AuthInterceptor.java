package com.hrms.config;

import com.hrms.model.UserAccount;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (path.startsWith("/login") || path.startsWith("/css") || path.startsWith("/js")) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserAccount user = (UserAccount) session.getAttribute("user");
            if (user != null) {
                return true;
            }
        }
        response.sendRedirect("/login");
        return false;
    }
}

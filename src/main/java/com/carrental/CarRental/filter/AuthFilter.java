package com.carrental.CarRental.filter;

import com.carrental.CarRental.bean.UserBean;
import com.carrental.CarRental.model.UserGroup;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    @Inject
    private UserBean userBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (path.startsWith("/forClient/")) {
            if (!userBean.isLogged()) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(request.getContextPath()+"/login.xhtml");
                return;
            }
        }
        if (path.startsWith("/forAdmin/")) {
            if (!userBean.isLogged()) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(request.getContextPath()+"/login.xhtml");
                return;
            }
            if (userBean.isLogged() && !userBean.getUser().hasAdminRole()) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(request.getContextPath()+"/errors/forbidden-403.xhtml");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

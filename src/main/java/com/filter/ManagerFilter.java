package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @Description 拦截所有访问后台资源的请求，对其进行权限检查。
 * @Author OneIce
 * @Date 2021/1/23 23:15
 */
// /pages/manager/*拦截后台静态资源, /manager/*拦截后台动态资源
@WebFilter({"/pages/manager/*", "/manager/*"})
public class ManagerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        System.out.println("拦截到请求: " + req.getRequestURI());
        Optional<Object> user = Optional.ofNullable(req.getSession(false)).map(session -> session.getAttribute("user"));
        if (user.isPresent()) {
            System.out.println("放行...");
            chain.doFilter(request, response);
        } else {
            System.out.println("重定向回登录页面...");
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
        }
    }
}

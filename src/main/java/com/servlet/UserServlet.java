package com.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.pojo.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description ${Description}
 * @Author IceCube
 * @Date 2020/12/2 19:29
 */
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //1.获取表单参数
        String username = request.getParameter("username");
        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User()); //自动注入

        user = userService.login(user);
        if (user != null) { //登录成功, 跳转到成功页面
            HttpSession session = request.getSession(); //创建Session域对象
            session.setAttribute("user", user);
            request.getRequestDispatcher("pages/user/login_success.jsp").forward(request, response);
        } else { //登录失败, 将错误信息和回显信息放到request域中, 然后跳转回登录页面
            request.setAttribute("msg", "账号或密码错误!");
            request.setAttribute("username", username);
            request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
        }
    }

    /** 验证用户名是否存在, 然后以json格式回传给客户端 */
    protected void ajaxExistingUsername(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        String username = request.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("existingUsername", new JsonPrimitive(existsUsername));
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        new Gson().toJson(jsonObject, writer);
    }


    /** 处理注销请求, 即让Session失效, 然后重定向到主页 */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //1.获取请求的参数
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        //2.验证验证码
        String codeParamKey = "code";
        if (!WebUtils.validateKaptcha(request, codeParamKey)) {
            request.setAttribute("msg", "验证码错误!");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
            return;
        }

        //3.检查用户名是否可用
        if (userService.existsUsername(username)) {
            request.setAttribute("msg", "用户名已存在!");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
            return;
        }

        //4.注册
        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User()); //自动注入
        boolean ans = userService.register(user);
        if (ans) {
            request.getRequestDispatcher("pages/user/regist_success.jsp").forward(request, response);
        } else {
            //注册失败时, 将错误信息和回显信息保存到request域中, 然后转发回注册页面
            request.setAttribute("msg", "注册失败, 请重试!");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
        }
    }
}

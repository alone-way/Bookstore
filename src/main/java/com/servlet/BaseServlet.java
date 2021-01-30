package com.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/12/2 20:59
 */
public abstract class BaseServlet extends HttpServlet {
    public static final String ENCODING = "utf-8";     //HTTP请求和响应的编码
    public static final String METHOD_PARAM = "action"; //一个请求参数的名字, 这个请求参数指明了要调用的目标方法

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String methodName = null;
        try {
            request.setCharacterEncoding(ENCODING);
            response.setCharacterEncoding(ENCODING);
            methodName = request.getParameter(METHOD_PARAM);
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
                    HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("请求参数错误" + METHOD_PARAM + "=" + methodName, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("没有方法访问权限" + METHOD_PARAM + "=" + methodName, e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("反射调用方法错误" + METHOD_PARAM + "=" + methodName, e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的编码格式" + ENCODING, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        this.doPost(req, resp);
    }
}

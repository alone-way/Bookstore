package com.utils;

import com.google.code.kaptcha.Constants;
import com.google.common.base.CaseFormat;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/12/2 21:44
 */
public class WebUtils {
    /**
     * 使用反射实现自动注入, bean对象的属性名和set方法名必须严格对应
     * @param paramMap 保存了<属性名, 属性值>的Map
     * @param bean     要注入的bean对象
     */
    public static <T> T copyParamToBean(Map<String, String[]> paramMap, T bean) {
        try {
            BeanUtils.populate(bean, paramMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 使用反射实现自动注入, bean对象的属性名和set方法名必须严格对应
     * @param paramMap 保存了<属性名, 属性值>的Map
     * @param bean     要注入的bean对象
     */
    public static void copyParamToBean2(Map<String, String[]> paramMap, Object bean) {
        //获得存储<属性名,属性值>的Map
        Set<Map.Entry<String, String[]>> entrySet = paramMap.entrySet();
        //遍历每个属性, 并调用相应的set方法
        for (Map.Entry<String, String[]> stringEntry : entrySet) {
            //拼接出set方法名
            String methodName = "set" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, stringEntry.getKey());
            //获得所有参数的Class对象
            String[] params = stringEntry.getValue();
            List<Class<? extends String>> paramClassList = new ArrayList<>();
            for (String param : params) {
                paramClassList.add(param.getClass());
            }
            try {
                //获得Method对象并调用方法
                Method method = bean.getClass().getDeclaredMethod(methodName, paramClassList.toArray(new Class<?>[0]));
                method.invoke(bean, params);
            } catch (NoSuchMethodException e) {
                System.out.println(methodName + "方法不存在");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println(bean);
    }

    /**
     * String转int, 失败则返回defaultValue
     */
    public static int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将请求中的验证码和Session中的验证码进行比对, 若相等且间隔时间不超过2分钟才返回true, 否则返回false
     * @param request     包含验证码参数的请求
     * @param kaptchaName 请求中的验证码参数的名字
     * @return 验证码的验证结果
     */
    public static boolean validateKaptcha(HttpServletRequest request, String kaptchaName) {
        HttpSession session = request.getSession(false);
        if (session == null) { //客户端禁用Cookie了, 直接返回false
            return false;
        }
        String token = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        Date date = (Date) session.getAttribute(Constants.KAPTCHA_SESSION_DATE);
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(Constants.KAPTCHA_SESSION_DATE);
        String code = request.getParameter(kaptchaName);
        //验证码相等且间隔时间不超过2分钟才返回true
        return token != null && token.equals(code) && System.currentTimeMillis() - date.getTime() <= 120000;
    }
}

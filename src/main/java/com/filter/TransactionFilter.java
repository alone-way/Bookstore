package com.filter;
/**
 * @Description ${Description}
 * @Author OneIce
 * @Date 2021/1/25 19:39
 */

import com.utils.JdbcUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class TransactionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  {
        try {
            chain.doFilter(request, response);
            JdbcUtils.commitAndClose(); //提交事务
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose(); //回滚事务
            throw new RuntimeException(e.getMessage(), e); //500错误
        }
    }
}

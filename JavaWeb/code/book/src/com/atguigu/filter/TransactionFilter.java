package com.atguigu.filter;

import com.atguigu.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose(); // 提交事务
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose(); // 回退事务
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

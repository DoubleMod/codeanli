package com.code.anli.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * @ClassName RequetCheckFilter
 * @Description TODO
 * @Author Administrator
 * @Date 2019/11/22 14:04
 * @Version 1.0
 **/

@WebFilter(filterName = "checkTokenFilter")
public class RequetCheckFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

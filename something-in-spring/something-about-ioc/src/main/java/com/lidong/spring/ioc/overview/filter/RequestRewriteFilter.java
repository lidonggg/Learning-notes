package com.lidong.spring.ioc.overview.filter;

import com.lidong.spring.ioc.overview.constants.RequestWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * filter -> interceptor -> controller
 *
 * @author ls J
 * @date 2020/5/8 11:13
 */
public class RequestRewriteFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!"POST".equals(servletRequest.getMethod().toUpperCase())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletRequest requestWrapper = new RequestWrapper(servletRequest);
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

}

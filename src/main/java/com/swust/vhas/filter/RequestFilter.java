package com.swust.vhas.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by liujie on 2017/5/25.
 */
public class RequestFilter extends HttpServlet implements Filter {

    private Logger logger = Logger.getLogger(getClass());

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("RequestFilter init ok");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.info(getRemoteIp(httpServletRequest) + "," + httpServletRequest.getRequestURI());
        chain.doFilter(request, response);
    }

    private String getRemoteIp(HttpServletRequest request) {
        String ip = (String) request.getAttribute("remote-ip");
        if (ip != null) {
            return ip;
        }
        ip = request.getHeader("X-real-ip");//先从nginx自定义配置获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        request.setAttribute("remote-ip", ip);
        return ip;
    }
}

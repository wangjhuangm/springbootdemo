package com.wangj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogCostInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(LogCostInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String session = request.getRequestedSessionId();
        logger.info("[" + session + "]" + method + "---" + uri);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String session = request.getRequestedSessionId();
        int code = response.getStatus();
        logger.info("[" + session + "]" + method + "---" + uri + "---" + code);
    }
}

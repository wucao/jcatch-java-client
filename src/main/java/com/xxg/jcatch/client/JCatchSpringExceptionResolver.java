package com.xxg.jcatch.client;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wucao on 17/3/15.
 */
public class JCatchSpringExceptionResolver implements HandlerExceptionResolver {

    private JCatchClient jCatchClient;

    public void setjCatchClient(JCatchClient jCatchClient) {
        this.jCatchClient = jCatchClient;
    }

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        jCatchClient.submit(e);
        return null;
    }
}

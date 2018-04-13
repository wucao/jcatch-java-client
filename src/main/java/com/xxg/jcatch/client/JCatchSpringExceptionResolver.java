package com.xxg.jcatch.client;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wucao on 17/3/15.
 */
public class JCatchSpringExceptionResolver implements HandlerExceptionResolver {

    protected JCatchClient jCatchClient;

    private String[] excludedExceptionClasses;

    public void setjCatchClient(JCatchClient jCatchClient) {
        this.jCatchClient = jCatchClient;
    }

    public void setExcludedExceptionClasses(String[] excludedExceptionClasses) {
        this.excludedExceptionClasses = excludedExceptionClasses;
    }

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if (excludedExceptionClasses != null) {
            for (String excludedExceptionClass : excludedExceptionClasses) {
                if (excludedExceptionClass.equals(e.getClass().getName())) {
                    return null;
                }
            }
        }
        jCatchClient.submit(e);
        return null;
    }
}

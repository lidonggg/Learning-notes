package com.lidong.spring.ioc.overview.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lidong.spring.ioc.overview.annotation.CustomValid;
import com.lidong.spring.ioc.overview.constants.RequestWrapper;
import com.lidong.spring.ioc.overview.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 定义拦截器，做参数校验
 *
 * @author ls J
 * @date 2020/5/8 10:00
 */
public class SourceAccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (!"POST".equals(httpServletRequest.getMethod().toUpperCase())) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) o;
        // get method parameters
        RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);
        String postData = requestWrapper.getBody();
        if (StringUtils.isBlank(postData)) {
            return true;
        }
        postData = new String(postData.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        MethodParameter[] parameters = handlerMethod.getMethodParameters();
        for (MethodParameter parameter : parameters) {
            // check CustomValid annotation
            CustomValid customValid = parameter.getParameterAnnotation(CustomValid.class);
            if (null == customValid) {
                continue;
            }
            ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(JSONObject.parseObject(postData, parameter.getParameterType()));
            if (validResult.hasErrors()) {
                try (OutputStream ops = httpServletResponse.getOutputStream()) {
                    ops.write("fail".getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

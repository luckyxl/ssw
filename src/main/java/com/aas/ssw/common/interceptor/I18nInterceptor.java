package com.aas.ssw.common.interceptor;

import com.aas.ssw.common.annotation.LocalInjection;
import com.aas.ssw.common.component.ParameterRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * 该拦截器用来获取当前语言环境，
 * 并将语言环境放到controller的参数中，
 * 后台sql就可以根据这个语言环境参数进行过滤
 *
 * @author xl
 */
public class I18nInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (o instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            Method method = handlerMethod.getMethod();
            /*只有带有LocalInjection注解的方法才需要替换request*/
            if (method.isAnnotationPresent(LocalInjection.class)) {
                LocalInjection annotation = method.getAnnotation(LocalInjection.class);
                Locale locale = RequestContextUtils.getLocaleResolver(httpServletRequest).resolveLocale(httpServletRequest);
                ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper(httpServletRequest);
                parameterRequestWrapper.addParameter(annotation.name(), new String[]{locale.toString()});
                httpServletRequest = parameterRequestWrapper;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        Locale locale = RequestContextUtils.getLocaleResolver(httpServletRequest).resolveLocale(httpServletRequest);
        Cookie cookie = new Cookie("currentLocal", locale.toString());
        String contextPath = httpServletRequest.getContextPath();
        if(StringUtils.isBlank(contextPath)){
            contextPath = "/";
        }
        cookie.setPath(contextPath);
        httpServletResponse.addCookie(cookie);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

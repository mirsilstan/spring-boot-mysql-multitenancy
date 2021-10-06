package com.mirsilstan.multitenant;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class TenantInterceptor implements HandlerInterceptor {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        String path = request.getRequestURI();
        log.info("Path: {}", path);

        var pattern = "/api/{tenantId}/**";
        String tenantId = null;
        if (antPathMatcher.match(pattern, path)) {
            var pathVariables = antPathMatcher.extractUriTemplateVariables(pattern, path);
            tenantId = pathVariables.get("tenantId");
            log.info("Tenant id {}", tenantId);
        }
        TenantContext.setCurrentTenant(tenantId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        TenantContext.clear();
    }
}
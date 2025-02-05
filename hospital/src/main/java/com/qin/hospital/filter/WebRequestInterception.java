package com.qin.hospital.filter;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WanYue
 */
@Component
public class WebRequestInterception implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取请求头，判断是否存在token或token是否有效
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return 放行或拦截
     * @throws Exception 报错
     */
//    @Override
//    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
//        String authorization = request.getHeader("Authorization");
//        if (StringUtils.isEmpty(authorization)) {
//            response.sendError(403, "非法用户");
//            return false;
//        }
//        if (Boolean.FALSE.equals(redisTemplate.hasKey(authorization))) {
//            response.sendError(401, "用户认证过期");
//            return false;
//        }
//        return true;
//    }
}

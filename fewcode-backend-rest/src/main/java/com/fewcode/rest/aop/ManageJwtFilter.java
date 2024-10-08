package com.fewcode.rest.aop;

import com.alibaba.fastjson.JSON;
import com.fewcode.common.config.GlobalConfig;
import com.fewcode.common.config.BaseConstants;
import com.fewcode.common.config.exception.ErrorCode;
import com.fewcode.common.model.ResultJson;
import com.fewcode.common.util.JwtUtil;
import com.fewcode.common.util.RedisUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 判断用户登录token
 *
 * @author 文艺倾年
 */
@WebFilter(filterName = "manageJwtFilter", urlPatterns = {"/*"})
@AllArgsConstructor
public class ManageJwtFilter implements Filter {


    private final List<String> includedUrlList;

    private final List<String> excludedUrlList;

    @Override
    public void init(FilterConfig filterConfig) {
        includedUrlList.addAll(Arrays.asList(
                "/manage/*"
        ));
        excludedUrlList.addAll(Arrays.asList(
                "/manage/sso/login",
                "/manage/sso/logout",
                "/manage/blog/initLucene"
        ));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest) request).getRequestURI();
        boolean isInclude = false;
        boolean isExclude = false;
        for (String excludedUrl : includedUrlList) {
            if (Pattern.matches(excludedUrl.replace("*", ".*"), url)) {
                isInclude = true;
                break;
            }
        }
        for (String excludedUrl : excludedUrlList) {
            if (Pattern.matches(excludedUrl.replace("*", ".*"), url)) {
                isExclude = true;
                break;
            }
        }
        if (!isInclude || isExclude) {
            chain.doFilter(request, response);
        } else {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            // 处理跨域问题，跨域的请求首先会发一个options类型的请求
            if (httpServletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
                chain.doFilter(request, response);
            }
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            RedisUtil redisService = (RedisUtil) factory.getBean("redisUtil");
            String account;
            String authorization = httpServletRequest.getHeader(BaseConstants.TOKEN_NAME);
            // 判断token是否存在，不存在代表未登录
            if (StringUtils.isEmpty(authorization)) {
                writeRsp(httpServletResponse, ErrorCode.NO_TOKEN);
                return;
            } else {
                account = JwtUtil.getAccount(authorization);
                String token = (String) redisService.get(GlobalConfig.getRedisUserKey(account));
                // 判断token是否存在，不存在代表登陆超时
                if (StringUtils.isEmpty(token)) {
                    writeRsp(httpServletResponse, ErrorCode.TOKEN_EXPIRE);
                    return;
                } else {
                    // 判断token是否相等，不相等代表在其他地方登录
                    if (!token.equalsIgnoreCase(authorization)) {
                        writeRsp(httpServletResponse, ErrorCode.TOKEN_EXCHANGE);
                        return;
                    }
                }
            }
            redisService.set(GlobalConfig.getRedisUserKey(account), authorization, GlobalConfig.EXPIRE_TIME);
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void writeRsp(HttpServletResponse response, ErrorCode errorCode) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("content-type", "application/json;charset=UTF-8");
        try {
            response.getWriter().println(JSON.toJSON(new ResultJson(errorCode.getCode(), errorCode.getMsg())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

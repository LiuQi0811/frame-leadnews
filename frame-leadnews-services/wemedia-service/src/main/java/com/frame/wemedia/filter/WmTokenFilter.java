package com.frame.wemedia.filter;

import com.frame.model.threadlocal.WmThreadLocalUtils;
import com.frame.model.wemedia.pojo.WmUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
 *@ClassName WmTokenFilter
 *@Description 过滤器
 *@Author LiuQi
 *@Date 2023/1/12 10:11
 *@Version 1.0
 */
@Component // 过滤器生效加入该注解
@WebFilter(value = "WmTokenFilter",urlPatterns = "/**") // 过滤器的配置 urlPatterns 拦截的路径 /** 表示拦截所有
@Order(-1) // 指定过滤器执行顺序
@Slf4j
public class WmTokenFilter extends GenericFilterBean
{
    /**
     *  过滤器的过滤方法
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; //请求对象 转换
        String userId = request.getHeader("userId"); // 获取请求头中的userId
        if(StringUtils.isNotBlank(userId))
        {    // 将userId 封装为WmUser对象 存入 ThreadLocal 中
            WmUser wmUser = WmUser.builder()
                    .id(userId)
                    .build();
            WmThreadLocalUtils.setUser(wmUser);
        }
         filterChain.doFilter(servletRequest,servletResponse);   //放行请求
         WmThreadLocalUtils.clear();// 请求完成后，需要清空 ThreadLocal 中的信息

    }
}

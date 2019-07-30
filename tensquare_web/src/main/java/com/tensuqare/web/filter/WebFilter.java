package com.tensuqare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebFilter extends ZuulFilter {
    /**
     * 过滤器执行方式
     * pre 表示在请求之前执行
     * post 表示在请求之后执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序 数字越小，优先级越大
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否启用当前过滤器
     * true 启用
     * false 不启用
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作
     * @return 任何object的值都表示继续执行，setsendzullResponse(false)表示不再继续执行
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul过滤器...");
        // 获取zuul 请求上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 获取request域
        HttpServletRequest request = requestContext.getRequest();
        // 获取header
        String header = request.getHeader("Authorization");
        if(header!=null && !"".equals(header)){
            requestContext.addZuulRequestHeader("Authorization",header);
        }
        return null;
    }
}

package com.tensuqare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

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
        RequestContext requestContext = RequestContext.getCurrentContext();
        // request域
        HttpServletRequest request = requestContext.getRequest();
        // 得到头信息
        String header = request.getHeader("Authorization");
        // 是否有转发请求 options是转发请求的方法
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        // 登录也放行
        String url=request.getRequestURL().toString();
        if(url.indexOf("/admin/login")>0){
            System.out.println("登陆页面"+url);
            return null;
        }

        if(header!=null && !"".equals(header)){
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if(roles.equals("admin")){
                        // 把头信息转发下去，并放行
                        requestContext.addZuulRequestHeader("Authorization",header);
                        return null;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    // 终止运行
                    requestContext.setSendZuulResponse(false);
                }
            }
        }
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(403);
        requestContext.setResponseBody("权限不足");
        requestContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}

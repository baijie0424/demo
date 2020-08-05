//package com.cetc15s.component;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @ClassName CorsFilter
// * @Description 配置拦截器在请求中加入Header来解决跨域问题
// * @Author bj
// * @Date 2020/7/15 23:06
// * @Version 1.0
// */
//@Component
//public class CorsFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse res = (HttpServletResponse) response;
//        res.setHeader("Access-Control-Allow-Credentials","true");
//        res.setHeader("Access-Control-Allow-Origin","*");
//        res.setHeader("Access-Control-Allow-Methods","GET,POST,DELETE,PUT");
//        res.setHeader("Access-Control-Allow-Headers","Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN");
//        if (((HttpServletRequest)request).getMethod().equals("OPTIONS")) {
//            response.getWriter().println("ok");
//            return;
//        }
//        chain.doFilter(request,response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}

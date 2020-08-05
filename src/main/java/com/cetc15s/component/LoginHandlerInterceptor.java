//package com.cetc15s.component;
//
//import org.springframework.lang.Nullable;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @ClassName LoginHandlerInterceptor
// * @Description 登录检查
// * @Author bj
// * @Date 2020/7/14 1:45
// * @Version 1.0
// */
//public class LoginHandlerInterceptor implements HandlerInterceptor{
//    //目标方法执行之前
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Object user = request.getAttribute("loginUser");
//        if (user == null) {
//            //未登录，返回登录页面
//            request.setAttribute("msg","没有权限请先登录");
//            request.getRequestDispatcher("登录页").forward(request, response);
//            return false;
//        } else {
//            //已登录
//            return true;
//        }
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//    }
//}

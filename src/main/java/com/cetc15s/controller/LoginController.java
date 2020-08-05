package com.cetc15s.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description @CrossOrigin注解用于解决跨域问题即该类下所有接口都可以跨域访问，@CrossOrigin("url")也可以在括号中指定允许跨域访问的站点
 * @Author bj
 * @Date 2020/7/14 1:27
 * @Version 1.0
 */
//@CrossOrigin
@RestController
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登录成功，为了防止表单重复提交可以重定向到后台首页
            session.setAttribute("loginUser",username);
            //跳转到后台首页
            return "redirect:";
        } else {
            //登陆失败
            map.put("msg", "用户名密码错误");
            //跳转到登录页面
            return "";
        }
    }
}

package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller  //注解  //控制页面
public class hiController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/register")      //发出请求
    @RequestMapping("/register")     //做出响应
    public String register(HttpServletRequest request,Map<String,Object> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User user1 = userMapper.getuser(username);
        if(user1 != null){
            map.put("msg1","用户名已经被使用,请重新注册。");
            return "login";      //返回的是页面的名称（因为有thymeleaf框架，所以不用写.html）
        }else{
            userMapper.adduser(user);
            return "register";      //返回的是页面的名称（因为有thymeleaf框架，所以不用写.html）
        }
    }
    @RequestMapping("/getuser")
    public String getuser(HttpServletRequest request,Map<String,Object> map){
        String username = request.getParameter("username");
        User user = userMapper.getuser(username);
        if(user != null){
//            map.put("msg","该用户存在！");
            map.put("msg","用户存在!");
            return "register";
        }else {
            map.put("msg","这个用户还没有被注册。");
            return "register";
        }
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request,Map<String,Object> map){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginuser = userMapper.login(username, password);
        System.out.println(loginuser);
        map.put("msg2","用户" + username + "登陆成功！");
        return "login";
    }
}

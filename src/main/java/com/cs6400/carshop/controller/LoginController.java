package com.cs6400.carshop.controller;


import com.cs6400.carshop.bean.User;
import com.cs6400.carshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/","/login"})
    public String loginPage(){

        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){

        Map<String, Object> map = userService.login(user.getUserName(), user.getPassword());

        if(map.containsKey("user")) {
            //把登陆成功的用户保存起来
            session.setAttribute("loginUser",user);
            //登录成功重定向到main.html;  重定向防止表单重复提交
            return "redirect:/main";
        } else {
            model.addAttribute("msg",map.get("msg"));
            //回到登录页面
            return "login";
        }
    }

    @GetMapping("/main")
    public String mainPage(HttpSession session,Model model){

        log.info("当前方法是：{}","mainPage");

        return "main";
    }
}

package com.cs6400.carshop.controller;


import com.cs6400.carshop.bean.RegularUser;
import com.cs6400.carshop.service.UserService;
import com.cs6400.carshop.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;

    @GetMapping(value = {"/login"})
    public String loginPage(){

        return "login";
    }

//    @PostMapping("/login")
//    public String main(RegularUser user, HttpSession session, Model model){
//
//        Map<String, Object> map = userService.login(user.getUsername(), user.getPassword());
//
//        if(map.containsKey("user")) {
//            //把登陆成功的用户保存起来
//            user.setAuthority(userService.searchUserType(user.getUsername()).getCode());
//            session.setAttribute("loginUser",user);
//            //登录成功重定向到main.html;  重定向防止表单重复提交
//            return "redirect:/search";
//        } else {
//            model.addAttribute("msg",map.get("msg"));
//            //回到登录页面
//            return "login";
//        }
//    }

    @GetMapping( "/index")
    public String getIndexPage(HttpSession session) {
        // 认证成功后,结果会通过SecurityContextHolder存入SecurityContext中.
        System.out.println("---------------");
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj instanceof RegularUser) {
            session.setAttribute("loginUser",obj);
        }
        System.out.println(obj.toString());
        return "redirect:/search";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/search";
    }

    @GetMapping(value = {"/","/search"})
    public String searchPage(Model model){

        model.addAttribute("vehicleNumber",vehicleService.selectVehicleForSale());
        return "search";
    }

    // 拒绝访问时的提示页面
    @RequestMapping(path = "/denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        return "/error/4xx";
    }
}

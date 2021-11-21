package com.cs6400.carshop.controller;


import com.cs6400.carshop.bean.RegularUser;
import com.cs6400.carshop.service.UserService;
import com.cs6400.carshop.service.VehicleService;
import com.cs6400.carshop.utils.Enum.PrivilegedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
    public String main(RegularUser user, HttpSession session, Model model){

        Map<String, Object> map = userService.login(user.getUserName(), user.getPassword());

        if(map.containsKey("user")) {
            //把登陆成功的用户保存起来
            user.setAuthority(userService.searchUserType(user.getUserName()));
            session.setAttribute("loginUser",user);
            //登录成功重定向到main.html;  重定向防止表单重复提交
            return "redirect:/search";
        } else {
            model.addAttribute("msg",map.get("msg"));
            //回到登录页面
            return "login";
        }
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
}

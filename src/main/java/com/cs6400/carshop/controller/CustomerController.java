package com.cs6400.carshop.controller;

import com.cs6400.carshop.bean.Customer;
import com.cs6400.carshop.bean.RegularUser;
import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/findCustomer")
    public String findCustomer(){
        return "findCustomer";
    }

    @PostMapping("/searchCustomer")
    public String searchCustomer(String id, boolean idType){
        System.out.println(id);
        System.out.println(idType);
        return "redirect:/search";
    }

    @GetMapping("/addCustomer")
    public String addCustomer(){
        return "increCustomer";
    }

    @PostMapping("/increCustomer")
    public String increCustomer(Customer customer){
        log.info(customer.toString());
        return "redirect:/search";
    }
}

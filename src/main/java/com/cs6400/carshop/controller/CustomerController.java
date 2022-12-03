package com.cs6400.carshop.controller;

import com.cs6400.carshop.bean.Customer;
import com.cs6400.carshop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String searchCustomer(String id, boolean idType, Model model){
        System.out.println(id);
        System.out.println(idType);
        Customer customer = idType ?
                customerService.searchIndividual(id) : customerService.searchBusiness(id);
        if (customer == null) {
            model.addAttribute("msg", "Sorry, customer didn't exist");
            return "wrongInfo";
        }
        model.addAttribute("customer", customer);
        if (idType) {
            return "customer_detail_individual";
        } else {
            return "customer_detail_business";
        }
    }

    @GetMapping("/addCustomer")
    public String addCustomer(){
        return "addCustomer";
    }

    @PostMapping("/increCustomer")
    public String increCustomer(Customer customer, boolean idType){
        log.info(customer.toString());
        if (idType) {
            customerService.insertIndividual(customer);
        } else {
            customerService.insertBusiness(customer);
        }
        return "redirect:/search";
    }

    private void ttt() {
        System.out.println(123);
        System.out.println(321);
    }
}

package com.cs6400.carshop.controller;

import com.cs6400.carshop.bean.Customer;
import com.cs6400.carshop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public Customer searchCustomer(String id, boolean idType){
        Customer customer = idType ?
                customerService.searchIndividual(id) : customerService.searchBusiness(id);
        System.out.println(customer.toString());
        return customer;
//        return "redirect:/search";
    }

    @GetMapping("/addCustomer")
    public String addCustomer(){
        return "addCustomer";
    }

    @PostMapping("/increCustomer")
    public String increCustomer(Customer customer){
        log.info(customer.toString());
        if (customer.getDriver_license() != null) {
            customerService.insertIndividual(customer);
        } else {
            customerService.insertBusiness(customer);
        }
        return "redirect:/search";
    }
}

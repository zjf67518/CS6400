package com.cs6400.carshop;

import com.cs6400.carshop.bean.Customer;
import com.cs6400.carshop.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTest {
    @Autowired
    private CustomerService customerService;

    @Test
    void testCustomer(){
//        String driver = "driver_license_1";
//        Customer customer = customerService.SearchIndividual(driver);
//        System.out.println(customer);

        Customer customer = new Customer();
        customer.setPhone_number("18819292934");
        customer.setAddress("Beijing");
        customer.setEmail("123@gatech.edu");
        customer.setBusiness_name("Baidu");
        customer.setTax_id("1823234");
        customer.setContact("contact1");
        customer.setTitle("Boss");
        customerService.InsertBusiness(customer);


//        Customer customer1 = new Customer();
//        customer1.setPhone_number("1101111");
//        customer1.setAddress("Shanghai");
//        customer1.setEmail("124@gatech.edu");
//        customer1.setFirst_name("hello");
//        customer1.setLast_name("big");
//        customer1.setDriver_license("license_11112");
//        customerService.InsertIndividual(customer1);




    }
}

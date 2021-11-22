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
    void findCustomer(){
        String driver = "driver_license_1";
        Customer customer = customerService.searchIndividual(driver);
        System.out.println(customer);
    }

    @Test
    void increCustomer() {
//        Customer customer = new Customer();
//        customer.setPhone_number("18016067518");
//        customer.setAddress("sz");
//        customer.setEmail("@gatech.edu");
//        customer.setDriver_license("18016067518");
//        customer.setFirst_name("zhu");
//        customer.setLast_name("qifeng");
//        customerService.insertBusiness(customer);
        Customer customer = new Customer();
        customer.setPhone_number("18819292934");
        customer.setAddress("Beijing");
        customer.setEmail("1234@gatech.edu");
        customer.setBusiness_name("Baidu");
        customer.setTax_id("182325");
        customer.setContact("contact1");
        customer.setTitle("Boss");
        customerService.insertBusiness(customer);
    }

}

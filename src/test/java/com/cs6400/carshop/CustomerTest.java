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
        String driver = "license_1111";
        Customer customer = customerService.searchIndividual(driver);
        System.out.println(customer);
        String driver1 = "182323";
        Customer customer1 = customerService.searchBusiness(driver1);
        System.out.println(customer1);
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
        customer.setPhone_number("13301638071");
        customer.setAddress("sh");
        customer.setEmail("qzhu@gatech.edu");
        customer.setBusiness_name("tc");
        customer.setTax_id("192323");
        customer.setContact("contact1");
        customer.setTitle("Boss");
        customerService.insertBusiness(customer);





    }
    @Test
    void findCustomer1(){
        System.out.println(customerService.searchCustomerById(9L));
    }
}

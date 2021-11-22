package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.Customer;
import com.cs6400.carshop.bean.Individual;
import com.cs6400.carshop.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public Customer SearchIndividual(String driver_license){
        return customerMapper.selectIndividual(driver_license);
    }

    public Customer SearchBusiness(String tax_id){
        return customerMapper.selectBusiness(tax_id);
    }

//    public Customer SearchCustomer(String)
    public void InsertIndividual(Customer customer){
        customerMapper.insertCustomer(customer);
        customerMapper.insertIndividual(customer);
    }
    public void InsertBusiness(Customer customer){
        customerMapper.insertCustomer(customer);

        customerMapper.insertBusiness(customer);
    }
}

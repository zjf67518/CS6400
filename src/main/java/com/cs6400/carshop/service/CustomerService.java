package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.Customer;
import com.cs6400.carshop.bean.Individual;
import com.cs6400.carshop.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public Customer searchIndividual(String driver_license){
        return customerMapper.selectIndividual(driver_license);
    }

    public Customer searchBusiness(String tax_id){
        return customerMapper.selectBusiness(tax_id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void insertIndividual(Customer customer){
        customerMapper.insertCustomer(customer);
        customerMapper.insertIndividual(customer);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void insertBusiness(Customer customer){
        customerMapper.insertCustomer(customer);
        customerMapper.insertBusiness(customer);
    }
}

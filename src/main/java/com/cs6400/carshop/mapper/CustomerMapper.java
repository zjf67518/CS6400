package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    Customer selectCustomerById(int id);
    int insertIndividualCustomer(Customer customer);
    int insertBusinessCustomer(Customer customer);
}

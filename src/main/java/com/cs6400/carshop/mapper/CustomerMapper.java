package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.Customer;
import com.cs6400.carshop.bean.RegularUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    Customer selectIndividual(String driver_license);
    Customer selectBusiness(String tax_id);
    void insertCustomer(Customer customer);
    void insertIndividual(Customer customer);
    void insertBusiness(Customer customer);
    Customer selectIndividualById(Long customer_id);
    Customer selectBusinessById(Long customer_id);


    void insertUser(RegularUser user);
    void insertInventoryClerk(String username);
    void insertManager(String username);
    void insertSalePerson(String username);
    void insertWriter(String username);



    Long searchCustomerIdByIndividual(String driving);
    Long searchCustomerIdByBusiness(String tax_id);
}

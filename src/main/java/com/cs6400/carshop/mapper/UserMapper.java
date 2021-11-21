package com.cs6400.carshop.mapper;


import com.cs6400.carshop.bean.RegularUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    RegularUser selectByUserName(String username);
    String selectOwner(String username);
    String selectInventoryClerk(String username);
    String selectSalePerson(String username);
    String selectServiceWriter(String username);
    String selectManager(String username);

}

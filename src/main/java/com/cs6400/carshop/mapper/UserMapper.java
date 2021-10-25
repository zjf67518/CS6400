package com.cs6400.carshop.mapper;


import com.cs6400.carshop.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectByUserName(String username);
}

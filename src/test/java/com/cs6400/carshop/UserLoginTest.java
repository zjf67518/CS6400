package com.cs6400.carshop;

import com.cs6400.carshop.bean.RegularUser;
import com.cs6400.carshop.mapper.UserMapper;
import com.cs6400.carshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserLoginTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void findByUserName() {
        RegularUser u = userMapper.selectByUserName("salesman_1");
        System.out.println(u.toString());
        System.out.println(userService.searchUserType("salesman_1"));
    }

}

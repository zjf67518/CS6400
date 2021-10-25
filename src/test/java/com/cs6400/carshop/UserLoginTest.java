package com.cs6400.carshop;

import com.cs6400.carshop.bean.User;
import com.cs6400.carshop.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserLoginTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void findByUserName() {
        User u = userMapper.selectByUserName("qzhu302");
        System.out.println(u.toString());
    }

}

package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.User;
import com.cs6400.carshop.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> login(String username, String password) {


        Map<String, Object> map = new HashMap<>();

        //空值处理
        if(StringUtils.isBlank(username)) {
            map.put("msg", "账号不能为空！");
            return map;
        }
        if(StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空！");
            return map;
        }

        //验证账号
        User user = userMapper.selectByUserName(username);
        if(user == null) {
            map.put("msg", "该账号不存在！");
            return map;
        }

        if (!user.getPassword().equals(password)) {
            map.put("msg", "密码不正确！");
            return map;
        }

        map.put("user", user);
        return map;
    }
}

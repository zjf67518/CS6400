package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.RegularUser;
import com.cs6400.carshop.mapper.UserMapper;
import com.cs6400.carshop.utils.Enum.PrivilegedUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserService implements UserDetailsService {
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
        RegularUser user = userMapper.selectByUserName(username);
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

    public PrivilegedUser searchUserType(String username){
        if(userMapper.selectOwner(username) != null){
            return PrivilegedUser.owner;
        }
        if(userMapper.selectInventoryClerk(username) != null){
            return PrivilegedUser.inventory_clerk;
        }
        if(userMapper.selectSalePerson(username) != null){
            return PrivilegedUser.sales_person;
        }
        if(userMapper.selectServiceWriter(username) != null){
            return PrivilegedUser.service_writer;
        }
        if(userMapper.selectManager(username) != null){
            return PrivilegedUser.manager;
        }
        return null;
    }

    public RegularUser findUserByName(String username) {
        System.out.println(username);
        RegularUser user = userMapper.selectByUserName(username);
        log.info("{}", user);
        user.setAuthority(searchUserType(user.getUsername()).getCode());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findUserByName(username);
    }
}

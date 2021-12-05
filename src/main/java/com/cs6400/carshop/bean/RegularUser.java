package com.cs6400.carshop.bean;


import com.cs6400.carshop.utils.Enum.PrivilegedUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegularUser implements UserDetails {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private Integer Authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                if (Authority.equals(PrivilegedUser.inventory_clerk.getCode())) {
                    return "InventoryClerk";
                } else if (Authority.equals(PrivilegedUser.sales_person.getCode())) {
                    return "SalePerson";
                } else if (Authority.equals(PrivilegedUser.service_writer.getCode())) {
                    return "ServiceWriter";
                } else if (Authority.equals(PrivilegedUser.manager.getCode())) {
                    return "Manager";
                } else if (Authority.equals(PrivilegedUser.owner.getCode())) {
                    return "Owner";
                } else {
                    return null;
                }
            }
        });
        return list;
    }

    // true: 账号未过期.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // true: 账号未锁定.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // true: 凭证未过期.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // true: 账号可用.
    @Override
    public boolean isEnabled() {
        return true;
    }

}

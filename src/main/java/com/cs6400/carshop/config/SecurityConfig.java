package com.cs6400.carshop.config;

import com.cs6400.carshop.bean.RegularUser;
import com.cs6400.carshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略静态资源的访问
        web.ignoring().antMatchers("/resources/**");
    }

    // AuthenticationManager: 认证的核心接口.
    // AuthenticationManagerBuilder: 用于构建AuthenticationManager对象的工具.
    // ProviderManager: AuthenticationManager接口的默认实现类.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 内置的认证规则
        // auth.userDetailsService(userService).passwordEncoder(new Pbkdf2PasswordEncoder("12345"));

        // 自定义认证规则
        // AuthenticationProvider: ProviderManager持有一组AuthenticationProvider,每个AuthenticationProvider负责一种认证.
        // 委托模式: ProviderManager将认证委托给AuthenticationProvider.
        auth.authenticationProvider(new AuthenticationProvider() {
            // Authentication: 用于封装认证信息的接口,不同的实现类代表不同类型的认证信息.
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String username = authentication.getName();
                String password = (String) authentication.getCredentials();
                RegularUser user = userService.findUserByName(username);
                if (user == null) {
                    throw new UsernameNotFoundException("UserNotFound!");
                }

                if (!user.getPassword().equals(password)) {
                    throw new BadCredentialsException("PasswordWrong!");
                }

                // principal: 主要信息; credentials: 证书; authorities: 权限;
                return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            }

            // 当前的AuthenticationProvider支持哪种类型的认证.
            @Override
            public boolean supports(Class<?> aClass) {
                // UsernamePasswordAuthenticationToken: Authentication接口的常用的实现类.
                return UsernamePasswordAuthenticationToken.class.equals(aClass);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 登录相关配置
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        System.out.println("searchconfig");
                        response.sendRedirect(request.getContextPath() + "/index");
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        request.setAttribute("error", e.getMessage());
                        request.getRequestDispatcher("/login").forward(request, response);
                    }
                });

        // 退出相关配置
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect(request.getContextPath() + "/logout2");
                    }
                });

        // 授权配置
        http.authorizeRequests()
                .antMatchers("/findCustomer").hasAnyAuthority("Owner", "SalePerson", "InventoryClerk", "ServiceWriter", "Manager")
                .antMatchers("/addCustomer").hasAnyAuthority("Owner", "SalePerson", "ServiceWriter")
                .antMatchers("/addVehicle").hasAnyAuthority("Owner", "InventoryClerk")
                .antMatchers("/sellVehicle").hasAnyAuthority("Owner", "SalePerson")
                .antMatchers("/repairVehicle").hasAnyAuthority("Owner", "ServiceWriter")
                .antMatchers("/getReport").hasAnyAuthority("Owner", "Manager")
                .and().exceptionHandling().accessDeniedPage("/denied");

        http.csrf().disable();
    }

}
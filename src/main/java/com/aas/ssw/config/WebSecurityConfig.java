package com.aas.ssw.config;

import com.aas.ssw.common.security.CustomUserService;
import com.aas.ssw.common.security.FilterSecurityInterceptor;
import com.aas.ssw.common.util.MD5Util;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(name = "security.enabled")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private FilterSecurityInterceptor filterSecurityInterceptor;

    @Bean
    UserDetailsService customUserService() {
        //注册UserDetailsService 的bean
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //user Details Service验证,密码md5加密
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence password) {
                return MD5Util.encode((String) password);
            }

            @Override
            public boolean matches(CharSequence password, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String) password));
            }
        });
        System.out.println("haha");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll(); //注销行为任意访问
        http.addFilterBefore(filterSecurityInterceptor, org.springframework.security.web.access.intercept.FilterSecurityInterceptor.class);
    }
}

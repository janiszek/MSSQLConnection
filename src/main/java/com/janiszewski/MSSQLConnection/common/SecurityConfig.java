package com.janiszewski.MSSQLConnection.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN","USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Override
    protected void configure(HttpSecurity httpSec) throws Exception {
        //TODO remove bills
        //httpSec.authorizeRequests().antMatchers("/","/welcome","/css","/js","/images").permitAll()
        httpSec.authorizeRequests().antMatchers("/","/welcome","/css/**","/js/**","/images/**","/bills/**").permitAll()
                .and().authorizeRequests().antMatchers("/locations/**", "/tenants/**").hasRole("ADMIN")
                //.antMatchers("/bills/**", "/payments/**").hasRole("USER")
                .antMatchers("/payments/**").hasRole("USER")
                //.anyRequest().authenticated()
                .anyRequest().hasRole("USER")
                .and().formLogin().permitAll().defaultSuccessUrl("/welcome")
                .and().logout().logoutSuccessUrl("/");

        httpSec.csrf().disable();
        httpSec.headers().frameOptions().disable();
    }

}

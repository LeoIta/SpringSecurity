package com.leoita.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    /myAccount - secured
    /myBalance - secured
    /myLoans - secured
    /myCards - secured
    /notices - not secured
    /contact - not secured
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/myAccount").authenticated()
                .antMatchers("/myBalance").authenticated()
                .antMatchers("/myLoans").authenticated()
                .antMatchers("/myCards").authenticated()
                .antMatchers("/notices").permitAll()
                .antMatchers("/contact").permitAll()
                .and().formLogin()
                .and().httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*we have now 2 users in the db:
    * 'user@example.com', and 'user@BCrypt.com'.
    * the 1st one will not work as password is not hashed at all -> WARN 6336 --- [nio-8080-exec-6] o.s.s.c.bcrypt.BCryptPasswordEncoder     : Encoded password does not look like BCrypt
    * 2nd will work as password is hashed and can be compared   */

}

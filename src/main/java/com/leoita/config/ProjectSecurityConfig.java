package com.leoita.config;

import com.leoita.filter.AuthoritiesLoggingAfterFilter;
import com.leoita.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

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

        http.cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200/"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L);
                    return config;
                }).and()
                .csrf()
                .ignoringAntMatchers("/contact")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/myAccount").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/myBalance").hasAnyRole("USER","ADMIN")
                .antMatchers("/myLoans").hasRole("ROOT")
                .mvcMatchers(HttpMethod.POST,"/myCards").authenticated()
                .regexMatchers(HttpMethod.GET,"/notices").permitAll()
                .antMatchers("/contact").permitAll()
                .and().formLogin()
                .and().httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

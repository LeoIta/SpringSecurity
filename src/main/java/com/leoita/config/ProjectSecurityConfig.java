package com.leoita.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
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
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http.cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L);
                    return config;
                }).and()
                .authorizeRequests()
                .antMatchers("/myAccount").hasAnyRole("USER")
                .antMatchers("/myBalance").hasAnyRole("ADMIN")
                .antMatchers("/myLoans").authenticated()
                .antMatchers("/myCards").hasAnyRole("USER", "ADMIN")
                .antMatchers("/notices").permitAll()
                .antMatchers("/contact").permitAll()
                .and()
                .csrf().disable()
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);

        /**
         * This configuration is needed to view the /h2-console without any issues.
         * Since H2 Console uses frames to display the UI, we need to allow the frames.
         * Otherwise since by default Spring Security consider X-Frame-Options: DENY
         * to avoid Clickjacking attacks, the /h2-console will not work properly.
         * More details can be found at
         * https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/headers.html#headers-frame-options
         */
        http.headers().frameOptions().sameOrigin();

    }

}

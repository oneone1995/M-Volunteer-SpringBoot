package com.github.oneone1995.mvolunteer.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by wangl on 2017/2/14.
 * 配置类
 * 主要负责资源服务器的配置，包括：对于请求资源的 URL 的安全约束的配置等等
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/signin/**").authenticated()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui",
                        "/swagger-resources", "/configuration/security",
                        "/swagger-ui.html", "/webjars/**",
                        "/swagger-resources/configuration/ui", "/swagge‌​r-ui.html").permitAll()
                .anyRequest().authenticated();
    }
}

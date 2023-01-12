//package com.frame.gateway.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///*
// *@ClassName SecurityConfig
// *@Description TODO
// *@Author LiuQi
// *@Date 2023/1/11 21:13
// *@Version 1.0
// */
////@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig  implements WebMvcConfigurer {
//
//    //Swagger Resources
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    //If you are using Spring Security Add Below Configuration
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**", "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
//                .permitAll()
//                .anyRequest().authenticated();
//        return http.build();
//    }
//
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
//                .antMatchers(HttpMethod.OPTIONS,"/**");
//    }
//}

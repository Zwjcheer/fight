package com.baozun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.baozun.dao.entity")
public class UnexFightAdminWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(UnexFightAdminWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(UnexFightAdminWebApplication.class);
    }
}

package com.ck.multimoduleweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.ck.*.mapper"})
@ComponentScan(basePackages={"com.ck"})
public class MultiModuleWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MultiModuleWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MultiModuleWebApplication.class);
    }
}

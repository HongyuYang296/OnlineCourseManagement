package com.example.dataclean;

import com.example.dataclean.controller.PageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
public class DataCleanApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DataCleanApplication.class, args);
        new SpringApplicationBuilder(DataCleanApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}

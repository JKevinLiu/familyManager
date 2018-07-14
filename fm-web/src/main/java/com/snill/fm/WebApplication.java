package com.snill.fm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        //application能扫描它同级和下级的包
        SpringApplication.run(WebApplication.class, args);
    }

}
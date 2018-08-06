package com.snill.fm;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {
    private static Logger log = Logger.getLogger(WebApplication.class);

    public static void main(String[] args) {
        //application能扫描它同级和下级的包
        SpringApplication.run(WebApplication.class, args);
    }
}
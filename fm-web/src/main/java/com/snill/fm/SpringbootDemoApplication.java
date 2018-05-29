package com.snill.fm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemoApplication {
    public static void main(String[] args) {
        //application能扫描它同级和下级的包
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}


/*@SpringBootApplication
public class SpringbootDemoApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootDemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }
}*/

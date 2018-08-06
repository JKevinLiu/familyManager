package com.snill.fm;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseServiceApplication {
    private static volatile boolean running = true;

    private static Logger log = Logger.getLogger(BaseServiceApplication.class);

    /**
     * Main 方法启动项
     */
    public static void main(String[] args) {
        SpringApplication.run(BaseServiceApplication.class, args);
        log.info("============= APP Start ON SpringBoot Success =============");
        synchronized (BaseServiceApplication.class) {
            while (running) {
                try {
                    BaseServiceApplication.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }


}

package com.snill.fm;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmsServiceApplication {
    private static volatile boolean running = true;

    private static Logger log = Logger.getLogger(AmsServiceApplication.class);

    /**
     * Main 方法启动项
     */
    public static void main(String[] args) {
        SpringApplication.run(AmsServiceApplication.class, args);
        log.info("============= APP Start ON SpringBoot Success =============");
        synchronized (AmsServiceApplication.class) {
            while (running) {
                try {
                    AmsServiceApplication.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }


}

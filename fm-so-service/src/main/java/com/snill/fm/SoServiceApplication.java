package com.snill.fm;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoServiceApplication {
    private static volatile boolean running = true;

    private static Logger log = Logger.getLogger(SoServiceApplication.class);

    /**
     * Main 方法启动项
     */
    public static void main(String[] args) {
        SpringApplication.run(SoServiceApplication.class, args);
        log.info("============= APP Start ON SpringBoot Success =============");
        synchronized (SoServiceApplication.class) {
            while (running) {
                try {
                    SoServiceApplication.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }


}

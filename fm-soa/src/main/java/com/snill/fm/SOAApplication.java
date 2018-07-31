package com.snill.fm;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SOAApplication {
    private static volatile boolean running = true;

    private static Logger log = Logger.getLogger(SOAApplication.class);

    /**
     * Main 方法启动项
     */
    public static void main(String[] args) {
        SpringApplication.run(SOAApplication.class, args);
        log.info("============= APP Start ON SpringBoot Success =============");
        synchronized (SOAApplication.class) {
            while (running) {
                try {
                    SOAApplication.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }


}
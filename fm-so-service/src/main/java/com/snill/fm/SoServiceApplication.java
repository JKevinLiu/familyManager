package com.snill.fm;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

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

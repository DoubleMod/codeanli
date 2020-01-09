package com.code.anli;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.management.ManagementFactory;

@SpringBootApplication
@SpringBootConfiguration
@EnableScheduling
@MapperScan(value = "com.code.anli.mapper.*.xml")
public class CodeanliApplication extends SpringBootServletInitializer implements CommandLineRunner {
    private Logger logger = Logger.getLogger(CodeanliApplication.class.getName());

    @Value("${spring.datasource.url}")
    private String sqlAdress;

    @Value("${spring.datasource.username}")
    private String sqlName;

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(CodeanliApplication.class, args);
    }

    @Override
    public void run(String... args) {

        try {

            logger.info(
                    ("JVM中试图使用的最大的内存是（最大分配）："
                            + Runtime.getRuntime().maxMemory() / (double) 1024 / 1024
                            + "MB"));
            logger.info(
                    "JVM的总内存(初始分配)：" + Runtime.getRuntime().totalMemory() / (double) 1024 / 1024 + "MB");
            logger.info("JVM的剩余(可分配)：" + Runtime.getRuntime().freeMemory() / (double) 1024 / 1024 + "MB");
            logger.info("The  Pid is " + ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
            logger.info("SQL User is: " + sqlName);
            logger.info("SQL Address is: " + sqlAdress);
            logger.info("Server port is: " + port);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}

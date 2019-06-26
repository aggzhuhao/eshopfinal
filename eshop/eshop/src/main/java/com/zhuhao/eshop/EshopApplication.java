package com.zhuhao.eshop;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("com.zhuhao.eshop.mapper")
@EnableTransactionManagement(proxyTargetClass = true)  //开启事务
public class EshopApplication {
    private static final Logger log = LoggerFactory.getLogger(EshopApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EshopApplication.class, args);
        log.info("eshop has run already BY Q1038334827 Zzh");
    }
}

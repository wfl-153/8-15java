package com.oracle.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.oracle.*")
@ServletComponentScan(basePackages = "com.oracle.*")
@EnableScheduling
public class PaiMingBooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaiMingBooksApplication.class, args);
    }

}

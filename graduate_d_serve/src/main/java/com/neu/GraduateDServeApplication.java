package com.neu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//加载过滤器
@ServletComponentScan
public class GraduateDServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduateDServeApplication.class, args);
    }

}

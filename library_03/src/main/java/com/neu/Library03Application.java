package com.neu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author 陈锦房
 */
@SpringBootApplication
//加载过滤器
@ServletComponentScan
public class Library03Application {

    public static void main(String[] args) {
        SpringApplication.run(Library03Application.class, args);
    }

}

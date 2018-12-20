package com.test.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication 来标注一个主程序类，说明这是一个Spring
 */
@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        //Spring应用启动
        SpringApplication.run(HelloApplication.class, args);
    }

}


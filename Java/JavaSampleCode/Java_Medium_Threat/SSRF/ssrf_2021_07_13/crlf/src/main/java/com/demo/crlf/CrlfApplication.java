package com.demo.crlf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CrlfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrlfApplication.class, args);
    }

}

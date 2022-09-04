package com.vmware.talentboost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringBootImageApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootImageApp.class, args);
    }
}

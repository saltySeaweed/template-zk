package com.example.zktraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EncryptablePropertySource(name = "EncryptedProperties", // decode password database
        value = {"classpath:application.properties"})
@EnableScheduling
public class ZkTrainingApplication {
    public static ApplicationContext ctx;
    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }
    public static void main(String[] args) {
        SpringApplication.run(ZkTrainingApplication.class, args);
    }

}

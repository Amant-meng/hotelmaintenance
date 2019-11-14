package com.chinahotelhelp.shm.operational;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.chinahotelhelp.shm.operational.server")
@EnableCaching
public class OperationalApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationalApplication.class, args);
    }
}

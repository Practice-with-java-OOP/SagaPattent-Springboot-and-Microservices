package com.syphan.practice.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.syphan.pratice.common.swagger", "com.syphan.practice.house"})
@EnableDiscoveryClient
@EnableFeignClients
public class HouseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseServiceApplication.class, args);
    }
}

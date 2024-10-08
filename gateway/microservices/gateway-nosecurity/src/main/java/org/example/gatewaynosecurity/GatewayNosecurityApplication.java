package org.example.gatewaynosecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@RequiredArgsConstructor
public class GatewayNosecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayNosecurityApplication.class, args);
    }

}

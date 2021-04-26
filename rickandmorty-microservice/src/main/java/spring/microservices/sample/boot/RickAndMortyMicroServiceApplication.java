package spring.microservices.sample.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"spring.microservices.sample"})
@EnableDiscoveryClient
public class RickAndMortyMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RickAndMortyMicroServiceApplication.class, args);
    }

    @Bean(value = "restTemplate")
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

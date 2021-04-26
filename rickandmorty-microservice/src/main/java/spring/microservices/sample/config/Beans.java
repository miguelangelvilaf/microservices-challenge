package spring.microservices.sample.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import spring.microservices.sample.config.web.LoggerInterceptor;
import spring.microservices.sample.config.web.ServerPortService;
import spring.microservices.sample.module.rickandmorty.application.GetRandomCharactersUseCase;
import spring.microservices.sample.module.rickandmorty.domain.CharacterRepository;

/**
 * Beans configuration.
 *
 * @since 4.0.0
 */
@Configuration
public class Beans {

    @Bean
    public LoggerInterceptor loggerInterceptor(ServerPortService serverPortService) {
        return new LoggerInterceptor(serverPortService);
    }

    @Bean(value = "apiRestTemplate")
    public RestTemplate apiRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GetRandomCharactersUseCase getRandomCharactersUseCase(CharacterRepository repository) {
        return new GetRandomCharactersUseCase(repository);
    }

}

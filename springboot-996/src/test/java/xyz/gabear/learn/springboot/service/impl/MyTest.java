package xyz.gabear.learn.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@Slf4j
public class MyTest {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate buildRest() {
        return new RestTemplate();
    }

    @Test
    public void testHttp() {
        String response = restTemplate.postForEntity("http://localhost:8080/rest",
                new HttpEntity<>("hello"), String.class).getBody();
        log.info(response);
    }
}

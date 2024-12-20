package com.example.productservicenov24.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean // create an object and put in the application context, so that Bean create this object(new RestTemplate() ).
    public RestTemplate restTemplate() {    // here creating a method that is returning a object of that class
        return new RestTemplate();
    }


    // create object of redis template
//    public RedisTemplate<String, String> redisTemplate() {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//
//    }

}

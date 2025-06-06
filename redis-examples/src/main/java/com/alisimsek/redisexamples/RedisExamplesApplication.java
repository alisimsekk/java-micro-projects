package com.alisimsek.redisexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisExamplesApplication.class, args);
    }

}

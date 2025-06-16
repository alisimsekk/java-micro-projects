package com.alisimsek.redisexamples.redismessaging.publisher;

import com.alisimsek.redisexamples.redismessaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisMessagePublisher {

    private final RedisTemplate<String, String> redisTemplate;

    public void publish(String channel, Notification notification) {
        try {
            redisTemplate.convertAndSend(channel, notification.getMessage());
            log.info("Message published: {}", notification.getMessage());
        } catch (Exception e) {
            log.error("Error publishing message", e);
        }
    }
}

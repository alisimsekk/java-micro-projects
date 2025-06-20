package com.alisimsek.redisexamples.controller;


import com.alisimsek.redisexamples.redismessaging.Notification;
import com.alisimsek.redisexamples.redismessaging.publisher.RedisMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private static final String CHANNEL = "notification-channel";
    private final RedisMessagePublisher redisMessagePublisher;

    @PostMapping
    public void publish(@RequestBody Notification notification) {
        redisMessagePublisher.publish(CHANNEL, notification);
    }
}

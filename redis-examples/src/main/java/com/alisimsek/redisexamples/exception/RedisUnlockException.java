package com.alisimsek.redisexamples.exception;

public class RedisUnlockException extends RuntimeException {
    public RedisUnlockException(String message, Throwable cause) {
        super(message, cause);
    }
}

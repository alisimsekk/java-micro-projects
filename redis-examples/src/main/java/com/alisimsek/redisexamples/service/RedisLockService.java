package com.alisimsek.redisexamples.service;

import com.alisimsek.redisexamples.exception.RedisLockException;
import com.alisimsek.redisexamples.exception.RedisUnlockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisLockService {
    private final RedisLockRegistry redisLockRegistry;

    /**
     * Attempts to acquire a distributed lock with the given key.
     * Throws RedisLockException if unable to obtain the lock.
     *
     * @param key the lock key
     * @throws RedisLockException if lock acquisition fails
     */
    public void lock(String key) throws RedisLockException {
        log.info("Trying to acquire lock for key: {}", key);
        Lock lock = redisLockRegistry.obtain(key);
        boolean acquired = lock.tryLock();
        if (!acquired) {
            log.warn("Failed to acquire lock for key: {}", key);
            throw new RedisLockException("Unable to acquire lock for key: " + key);
        }
        log.info("Lock acquired for key: {}", key);
    }

    /**
     * Releases the distributed lock for the given key.
     * Throws RedisUnlockException if unlocking fails.
     *
     * @param key the lock key
     * @throws RedisUnlockException if unlock fails
     */
    public void unlock(String key) throws RedisUnlockException {
        log.info("Releasing lock for key: {}", key);
        Lock lock = redisLockRegistry.obtain(key);
        try {
            lock.unlock();
            log.info("Lock released for key: {}", key);
        } catch (IllegalMonitorStateException e) {
            log.error("Failed to release lock for key: {}", key, e);
            throw new RedisUnlockException("Unable to release lock for key: " + key, e);
        }
    }
}

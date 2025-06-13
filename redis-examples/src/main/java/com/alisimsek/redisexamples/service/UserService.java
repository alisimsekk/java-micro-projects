package com.alisimsek.redisexamples.service;

import com.alisimsek.redisexamples.entity.User;
import com.alisimsek.redisexamples.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RedisLockService redisLockService;

    private static final String USER_LIST_CACHE = "userList";
    private static final String USER_BY_ID_CACHE = "userById";

    /**
     * Retrieves all users and caches the result.
     * Does not cache if the result is empty.
     */
    @Cacheable(value = USER_LIST_CACHE, unless = "#result.isEmpty()")
    public List<User> getAllUsers() {
        log.info("Fetching all users from DB");
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by ID and caches the result.
     * Does not cache if the result is null.
     */
    @Cacheable(value = USER_BY_ID_CACHE, key = "#id", unless = "#result == null")
    public User getUser(Long id) {
        log.info("Fetching user from DB with id: {}", id);
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Creates a new user and evicts relevant caches.
     */
    //@CacheEvict(value = {USER_BY_ID_CACHE, USER_LIST_CACHE}, allEntries = true)
    @CacheEvict(value = {USER_LIST_CACHE}, allEntries = true)
    public User createUser(User user) {
        log.info("Creating new user: {}", user);
        return userRepository.save(user);
    }

    /**
     * Updates an existing user.
     * Uses distributed locking to prevent concurrent updates.
     * Evicts relevant caches after update.
     */
    @CacheEvict(value = {USER_LIST_CACHE, USER_BY_ID_CACHE}, allEntries = true)
    public User updateUser(Long id, User user) {
        User existing = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        String lockKey = "user_update_" + id;
        boolean lockAcquired = false;
        try {
            redisLockService.lock(lockKey);
            lockAcquired = true;

            log.info("Updating user: {}", user);
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            return userRepository.save(existing);
        } catch (Exception e) {
            log.error("updateUser || Failed to update user with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException(String.format("updateUser || Failed to update user with ID %d", id));
        } finally {
            if (lockAcquired) {
                redisLockService.unlock(lockKey);
            }
        }
    }

    /**
     * Updates an existing user without releasing the Redis lock.
     * This simulates a scenario where the lock is not released,
     * preventing further access with the same lock key until it expires.
     */
    @CacheEvict(value = {USER_LIST_CACHE, USER_BY_ID_CACHE}, allEntries = true)
    public User updateUserWithoutRedisUnlock(Long id, User user) {
        User existing = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        String lockKey = "user_update_" + id;
        try {
            redisLockService.lock(lockKey);
            log.info("Updating user (without unlocking): {}", user);
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            return userRepository.save(existing);
        } catch (Exception e) {
            log.error("updateUserWithoutRedisUnlock || Failed to update user with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException(String.format("updateUserWithoutRedisUnlock || Failed to update user with ID %d", id));
        }
        // lock is NOT released here intentionally
    }


    /**
     * Deletes a user and evicts relevant caches.
     */
    //@CacheEvict(value = {USER_LIST_CACHE, USER_BY_ID_CACHE}, allEntries = true)
    @Caching(evict = {
            @CacheEvict(value = USER_BY_ID_CACHE, key = "#id"),
            @CacheEvict(value = USER_LIST_CACHE, allEntries = true)
    })
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
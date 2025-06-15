package com.alisimsek.redisexamples.controller;
import com.alisimsek.redisexamples.entity.User;
import com.alisimsek.redisexamples.exception.RateLimitException;
import com.alisimsek.redisexamples.service.RedisLockService;
import com.alisimsek.redisexamples.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.RedisTemplate;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final RedisLockService redisLockService;

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String key = "rate_limit_" + ip;
        Long count = redisTemplate.opsForValue().increment(key);
        if (count == 1) {
            redisTemplate.expire(key, java.time.Duration.ofMinutes(1));
        }
        if (count > 3) {
            log.warn("Rate limit reached for {}", ip);
            throw new RateLimitException("Rate limit exceeded");
        }
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PutMapping("/update-without-redis-unlock/{id}")
    public User updateUserWithoutRedisUnlock(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUserWithoutRedisUnlock(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Tries to forcefully unlock the Redis lock by key.
     */
    @DeleteMapping("/unlock")
    public ResponseEntity<String> unlock(@RequestParam String lockKey) {
        try {
            redisLockService.unlock(lockKey);
            return ResponseEntity.ok("Lock released for key: " + lockKey);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Could not unlock. " + ex.getMessage());
        }
    }
}
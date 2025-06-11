package com.alisimsek.redisexamples.repository;

import com.alisimsek.redisexamples.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
}

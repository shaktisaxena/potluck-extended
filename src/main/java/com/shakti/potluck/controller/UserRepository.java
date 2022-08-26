package com.shakti.potluck.controller;

import com.shakti.potluck.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 *
 * @author didin
 */
public interface UserRepository extends  MongoRepository<User, String> {
    
    User findByEmail(String email);
    Optional<User> findByUsername(String name);
}

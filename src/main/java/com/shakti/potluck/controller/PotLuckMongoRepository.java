package com.shakti.potluck.controller;

import com.shakti.potluck.entity.PotLuck;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface PotLuckMongoRepository extends MongoRepository<PotLuck,Integer> {
    @Query("{ 'name': ?0 }")
    Optional<PotLuck> findByName(final String name);
}

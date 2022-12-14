package com.shakti.potluck.controller;

import com.shakti.potluck.entity.PotLuck;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface PotLuckMongoRepository extends MongoRepository<PotLuck,String> {
    @Query("{ 'name': ?0 }")
    Optional<PotLuck> findByName(final String name);

    Optional<List<PotLuck>> findByUserEmail(final String email);

    boolean existsByName(final String name);

    Optional<PotLuck> findByNameAndUserEmail(final String name, final String email);

    Optional<List<PotLuck>> findAllByUserEmail(final String email);

}

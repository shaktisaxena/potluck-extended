package com.shakti.potluck.controller;


import com.shakti.potluck.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

/**
 *
 * @author didin
 */
public interface RoleRepository extends MongoRepository<Role, String> {

    Collection<Role> findByRole(String role);

}


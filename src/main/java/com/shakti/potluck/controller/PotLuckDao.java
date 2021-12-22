package com.shakti.potluck.controller;

import com.shakti.potluck.entity.PotLuck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PotLuckDao {

    private final PotLuckMongoRepository _repository;

    public void save(PotLuck potLuck) {
        _repository.save(potLuck);
    }

    public void update(PotLuck potLuck){
        _repository.save(potLuck);
    }


    public void delete(PotLuck potLuck){
        _repository.delete(potLuck);
    }

    public Optional<PotLuck> findByName(String potLuckName) {
        return _repository.findByName(potLuckName);
        }
}
package com.shakti.potluck.controller;

import com.shakti.potluck.entity.PotLuck;
import com.shakti.potluck.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDao {

    private final UserMongoRepository _repository;

    public User save(User user) {
        return  _repository.save(user);
    }

    public User update(User user){
       return _repository.save(user);
    }

    public void delete(User user){
        _repository.delete(user);
    }

    public Optional<User> findByName(String userName) {
        return _repository.findByName(userName);
        }

    public Optional<User> findById(String id) {
      return  _repository.findById(id);
    }
}

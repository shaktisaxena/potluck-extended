package com.shakti.potluck.controller;

import com.shakti.potluck.entity.User;

public interface UserService {
    ServiceResponse login(User requestUser);
    ServiceResponse logout();
    ServiceResponse register(User user);

}

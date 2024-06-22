package com.codinghints.eventsmicroservice.service;

import com.codinghints.eventsmicroservice.entity.RoleEntity;

public interface UserService {
    void createUser(String firstName, String lastName, String email, String password);

    RoleEntity getRoleName(String name);
}

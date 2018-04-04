package com.ocularminds.ocare.service;

import com.ocularminds.ocare.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    void delete(String id);
}

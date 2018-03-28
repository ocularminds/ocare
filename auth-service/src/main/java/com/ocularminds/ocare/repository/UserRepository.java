package com.ocularminds.ocare.repository;

import com.ocularminds.ocare.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByLogin(String username);
}

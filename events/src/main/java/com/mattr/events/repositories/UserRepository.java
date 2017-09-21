package com.mattr.events.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.events.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
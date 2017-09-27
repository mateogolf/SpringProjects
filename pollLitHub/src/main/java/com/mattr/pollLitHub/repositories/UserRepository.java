package com.mattr.pollLitHub.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	User findByUsername(String username);
}

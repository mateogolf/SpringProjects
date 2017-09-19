package com.mattr.loginreg.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mattr.loginreg.models.User;

public interface UserRepo extends CrudRepository<User,Long>{
	User findByEmail(String email);
//	@Query()
//	ArrayList<User> findByRoleId(Role role);
}

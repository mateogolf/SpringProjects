package com.mattr.loginreg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mattr.loginreg.models.Role;

public interface RoleRepo extends CrudRepository<Role,Long>{
	List<Role> findByName(String name);
}

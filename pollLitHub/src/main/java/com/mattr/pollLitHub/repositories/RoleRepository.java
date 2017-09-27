package com.mattr.pollLitHub.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long>{
	Role findByName(String name);
}

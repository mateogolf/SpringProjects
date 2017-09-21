package com.mattr.events.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.events.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
	Role findByName(String name);
}

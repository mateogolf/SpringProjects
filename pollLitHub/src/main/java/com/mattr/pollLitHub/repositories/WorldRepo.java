package com.mattr.pollLitHub.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.World;

@Repository
public interface WorldRepo extends CrudRepository<World,Long>{

}

package com.mattr.dojoninjas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.dojoninjas.models.Ninja;

@Repository
public interface NinjaRepo extends CrudRepository<Ninja,Long>{

}

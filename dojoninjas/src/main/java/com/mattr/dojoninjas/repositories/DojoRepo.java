package com.mattr.dojoninjas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.dojoninjas.models.Dojo;
@Repository
public interface DojoRepo extends CrudRepository<Dojo,Long>{

}

package com.mattr.pollLitHub.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.Chara;

@Repository
public interface CharRepo extends CrudRepository<Chara,Long>{

}

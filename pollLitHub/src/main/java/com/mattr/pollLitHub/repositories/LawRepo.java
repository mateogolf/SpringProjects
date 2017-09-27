package com.mattr.pollLitHub.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.Law;

@Repository
public interface LawRepo extends CrudRepository<Law,Long>{

}

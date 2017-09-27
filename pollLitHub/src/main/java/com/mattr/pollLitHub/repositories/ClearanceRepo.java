package com.mattr.pollLitHub.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.Clearance;

@Repository
public interface ClearanceRepo extends CrudRepository<Clearance,Long>{

}

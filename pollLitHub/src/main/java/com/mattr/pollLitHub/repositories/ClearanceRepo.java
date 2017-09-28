package com.mattr.pollLitHub.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.Clearance;

@Repository
public interface ClearanceRepo extends CrudRepository<Clearance,Long>{
	List<Clearance> findByIdGreaterThan(Long id);
}

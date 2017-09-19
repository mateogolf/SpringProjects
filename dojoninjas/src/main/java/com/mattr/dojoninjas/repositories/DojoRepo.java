package com.mattr.dojoninjas.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.dojoninjas.models.Dojo;
@Repository
public interface DojoRepo extends CrudRepository<Dojo,Long>{
	@Query(value="SELECT *,COUNT(ninja.id) As ninjaCount FROM dojo INNER JOIN dojo.id=ninja.dojo ORDER BY ninjaCount DESC",nativeQuery=true)
	ArrayList<Dojo> findAllDojosByNinjaCountDesc();
}

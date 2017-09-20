package com.mattr.dojoninjas.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.dojoninjas.models.Dojo;
@Repository
public interface DojoRepo extends CrudRepository<Dojo,Long>{
//	@Query(value="SELECT *,COUNT(ninja.id) As ninjaCount FROM dojo INNER JOIN dojo.id=ninja.dojo ORDER BY ninjaCount DESC",nativeQuery=true)
//	@Query("SELECT d FROM Dojo d JOIN d.ninjas n ORDER BY COUNT(n.id) DESC")
//	ArrayList<Dojo> findAllDojosByNinjaCountDesc();
//	ArrayList<Dojo> findAllOrderByNinjasDesc();
	@Query("SELECT d, n FROM Dojo d JOIN d.ninjas n")
	ArrayList<Object[]> joinDojosAndNinjas2();
}

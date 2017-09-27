package com.mattr.pollLitHub.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.Lore;

@Repository
public interface LoreRepo extends CrudRepository<Lore,Long>{

}

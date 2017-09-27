package com.mattr.pollLitHub.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.NodeType;

@Repository
public interface TypeRepo extends CrudRepository<NodeType,Long>{

}

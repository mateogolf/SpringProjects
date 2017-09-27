package com.mattr.pollLitHub.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.Follower;

@Repository
public interface FollowerRepo extends CrudRepository<Follower,Long>{

}

package com.mattr.pollLitHub.repositories;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.Follower;
import com.mattr.pollLitHub.models.User;
import com.mattr.pollLitHub.models.World;

@Repository
public interface FollowerRepo extends CrudRepository<Follower,Long>{
	Long deleteByUserIdAndWorldId(Long userId,Long worldId);
	ArrayList<Follower> findByUserAndWorld(User user,World world);
}

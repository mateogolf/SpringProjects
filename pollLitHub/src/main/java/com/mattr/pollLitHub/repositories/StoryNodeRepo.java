package com.mattr.pollLitHub.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.StoryNode;

@Repository
public interface StoryNodeRepo extends CrudRepository<StoryNode,Long>{

}

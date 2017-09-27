package com.mattr.pollLitHub.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.NodeType;
import com.mattr.pollLitHub.models.StoryNode;

@Repository
public interface StoryNodeRepo extends CrudRepository<StoryNode,Long>{
	List<StoryNode> findBySuperNodeOrderByNodeNumAsc(StoryNode superNode);
	List<StoryNode> findBySuperNodeAndNTypeOrderByNodeNumAsc(StoryNode superNode,NodeType nType);
}

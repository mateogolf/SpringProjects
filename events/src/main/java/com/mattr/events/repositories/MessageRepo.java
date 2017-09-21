package com.mattr.events.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.events.models.Message;
@Repository
public interface MessageRepo extends CrudRepository<Message,Long>{

}

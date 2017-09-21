package com.mattr.events.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.events.models.Event;
@Repository
public interface EventRepo extends CrudRepository<Event,Long>{
	ArrayList<Event> findByDateAfterAndState(Date date,String state);
	ArrayList<Event> findByDateAfterAndStateNotLike(Date date,String state);
}

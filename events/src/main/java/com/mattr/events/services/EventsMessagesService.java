package com.mattr.events.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mattr.events.models.Event;
import com.mattr.events.models.Message;
import com.mattr.events.repositories.EventRepo;
import com.mattr.events.repositories.MessageRepo;
import com.mattr.events.repositories.UserRepository;

@Service
public class EventsMessagesService {
	private EventRepo eventRepo;
	private MessageRepo messageRepo;
	private UserRepository userRepo;
	public EventsMessagesService(EventRepo eventRepo, MessageRepo messageRepo, UserRepository userRepo) {
		this.eventRepo = eventRepo;
		this.messageRepo = messageRepo;
		this.userRepo = userRepo;
	}
	
	//Events
	//View all
	public ArrayList<Event> allEvents(){
		return (ArrayList<Event>) eventRepo.findAll();
	}
	public ArrayList<Event> localEvents(String state){
		return eventRepo.findByDateAfterAndState(new Date(),state);
	}
	public ArrayList<Event> otherEvents(String state){
		return eventRepo.findByDateAfterAndStateNotLike(new Date(), state);
	}
	
	//View One and edit one
	public Event eventAt(Long id) {
		return eventRepo.findOne(id);
	}
	//Add
	public Event addEvent(Event event) {
		return eventRepo.save(event);
	}
	//Update
	public Event updateEvent(Event event) {
		event.setUpdatedAt(new Date());
		return eventRepo.save(event);
	}
	//Delete
	public void destroyEvent(Long id) {
		eventRepo.delete(id);
	}
	//CRUD Messages
	//View all
	public ArrayList<Message> allMessages(){
		return (ArrayList<Message>) messageRepo.findAll();
	}
	//2)Show One and 5)GET: Edit
	public Message messageAt(Long id) {
		return messageRepo.findOne(id);
	}
	//3)GET: New: NONE

	//4)POST: Create
	public void addMessage(Message message) {
		messageRepo.save(message);
	}
	//6)POST: Update
	public Message updateMessage(Message message) {
		message.setUpdatedAt(new Date());
		return messageRepo.save(message);
	}
}

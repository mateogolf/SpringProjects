package com.mattr.events.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="messages")
public class Message {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=1)
	private String content;
	
	//Relationships
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
	private Event event;
	
	
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss") 
	private Date createdAt;
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss") 
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	public Message() {
	}

	public Message(String content, User user, Event event) {
		this.content = content;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
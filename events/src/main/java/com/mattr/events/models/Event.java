package com.mattr.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=5)
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
	@Size(min=2,max=255)
	private String location;
	@Size(min=2,max=2)
	private String state;
	//Relationships
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User host;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name= "events_users",
			joinColumns = @JoinColumn(name = "event_id"), 
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> attendees;
	
	@OneToMany(mappedBy="event", fetch = FetchType.LAZY)
	private List<Message> messages;
	
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
	public Event() {
		
	}

	public Event(String name, Date date, String location, String state, User host) {
		this.name = name;
		this.date = date;
		this.location = location;
		this.state = state;
		this.host = host;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
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
	public boolean hasUser(User currentUser) {
		for(User user:attendees) {
			if(user.getId() == currentUser.getId()) {
				return true;
			}
		}
		return false;
	}
	public boolean isHost(User currentUser) {
		if(host.getId() == currentUser.getId()) {
			return true;
		}
		return false;
	}
}

package com.mattr.pollLitHub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class World {
	@Id
	@GeneratedValue
	private Long id;
	//Columns
	@Size(min=1,max=100)
	private String name;
	@Size(min=1,max=255)
	private String description;
	
	//Relationships
	//Writers
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "users_worlds", 
		joinColumns = @JoinColumn(name = "world_id"), 
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> writers;
	//Link to followed users
	@OneToMany(mappedBy="world", fetch=FetchType.LAZY)
	private List<Follower> followers;
	//Law
	@OneToMany(mappedBy="world", fetch=FetchType.LAZY)
	private List<Law> laws;
	//Lore
	@OneToMany(mappedBy="world", fetch=FetchType.LAZY)
	private List<Lore> lore;
	//Characters
	@OneToMany(mappedBy="world", fetch=FetchType.LAZY)
	private List<Chara> charas;
//	//First StoryNode
//	@OneToOne(mappedBy="headofWorld", fetch=FetchType.LAZY)
//	private StoryNode head;
	
	//Created/Updated 
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")//extra
	private Date createdAt;
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")//extra
	private Date updatedAt;
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	public World() {
		
	}

	public World(String name, String description) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getWriters() {
		return writers;
	}

	public void setWriters(List<User> writers) {
		this.writers = writers;
	}

	public List<Follower> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Follower> followers) {
		this.followers = followers;
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

	public List<Law> getLaws() {
		return laws;
	}

	public void setLaws(List<Law> laws) {
		this.laws = laws;
	}

	public List<Lore> getLore() {
		return lore;
	}

	public void setLore(List<Lore> lore) {
		this.lore = lore;
	}

	public List<Chara> getCharacters() {
		return charas;
	}

	public void setCharacters(List<Chara> charas) {
		this.charas = charas;
	}

//	public StoryNode getHead() {
//		return head;
//	}
//
//	public void setHead(StoryNode head) {
//		this.head = head;
//	}
	
	
}

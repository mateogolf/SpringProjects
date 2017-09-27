package com.mattr.pollLitHub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Clearance {
	@Id
	@GeneratedValue
	private Long id;
	//Columns
	@Size(min=1)
	private String name;
	private String description;
	
	//Relationships
	//Users
	@OneToMany(mappedBy="clearance", fetch=FetchType.LAZY)
	private List<Follower> followers;
	//Law
	@OneToMany(mappedBy="clearance", fetch=FetchType.LAZY)
	private List<Law> laws;
	//Lore
	@OneToMany(mappedBy="clearance", fetch=FetchType.LAZY)
	private List<Lore> lore;
	//Characters
	@OneToMany(mappedBy="clearance", fetch=FetchType.LAZY)
	private List<Character> characters;
	//StoryNode
	@OneToMany(mappedBy="clearance", fetch=FetchType.LAZY)
	private List<StoryNode> StoryNode;
	
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
	
	public Clearance() {
		
	}

	public Clearance(String name, String description) {
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
	
	
}

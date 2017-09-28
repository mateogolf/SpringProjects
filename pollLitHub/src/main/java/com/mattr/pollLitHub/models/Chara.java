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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Chara {
	@Id
	@GeneratedValue
	private Long id;
	//Columns
	@Size(min=2,max=100)
	private String name;
	@Size(min=2,max=50)
	private String race;
	@Size(min=2,max=50)
	private String personality;
	@Size(min=10)
	private String background;
	
	
	//Relationships
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="world_id")
	private World world;
	//Clearance
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="clearance_id")
	private Clearance clearance;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "nodes_characters", 
		joinColumns = @JoinColumn(name = "character_id"), 
		inverseJoinColumns = @JoinColumn(name = "snode_id"))
	private List<StoryNode> relevance;
	
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
	public Chara() {
		
	}

	public Chara(String name, String race, String personality, String background, World world, Clearance clearance) {
		this.name = name;
		this.race = race;
		this.personality = personality;
		this.background = background;
		this.world = world;
		this.clearance = clearance;
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

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Clearance getClearance() {
		return clearance;
	}

	public void setClearance(Clearance clearance) {
		this.clearance = clearance;
	}

	public List<StoryNode> getRelevance() {
		return relevance;
	}

	public void setRelevance(List<StoryNode> relevance) {
		this.relevance = relevance;
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

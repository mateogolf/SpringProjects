package com.mattr.pollLitHub.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Character {
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
	public Character() {
		
	}
}

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

//imports removed for brevity

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=3, message="Username must be greater than 3 characters")
	private String username;
	@Size(min=5, message="Password must be greater than 5 characters")
	private String password;
	@Transient
	private String passwordConfirmation;
	
	//Fields
	@Email
	private String email;
	private double rating;
	//Stats
//	@Deprecated
//	private double VIT;
//	@Deprecated
//	private double END;
//	@Deprecated
//	private double STR;
//	@Deprecated
//	private double DEF;
//	@Deprecated
//	private double INT;
//	@Deprecated
//	private double RES;
//	@Deprecated
//	private double DEX;
//	@Deprecated
//	private double CRIT;
	
	//Relationships
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "users_roles", 
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;	
	
	//Title
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_titles", 
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "title_id"))
	private List<Title> titles;
	//World
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_worlds", 
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "world_id"))
	private List<World> createdWorlds;
	//Followed
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Follower> followerRoles;
	
	
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
	public boolean isAdmin() {
		for(Role role:roles) {
			if(role.getName().equals("ROLE_ADMIN")) {
				return true;
			}
		}
		return false;
	}
	
	
	public User() {
	}
	 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
	    this.id = id;
	}
	
	public String getUsername() {
	    return username;
	}
	
	public void setUsername(String username) {
	    this.username = username;
	}
	
	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}
	
	public String getPasswordConfirmation() {
	    return passwordConfirmation;
	}
	
	public void setPasswordConfirmation(String passwordConfirmation) {
	    this.passwordConfirmation = passwordConfirmation;
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
	
	public List<Role> getRoles() {
	    return roles;
	}
	
	public void setRoles(List<Role> roles) {
	    this.roles = roles;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

//	public double getVIT() {
//		return VIT;
//	}
//
//	public void setVIT(double vIT) {
//		VIT = vIT;
//	}
//
//	public double getEND() {
//		return END;
//	}
//
//	public void setEND(double eND) {
//		END = eND;
//	}
//
//	public double getSTR() {
//		return STR;
//	}
//
//	public void setSTR(double sTR) {
//		STR = sTR;
//	}
//
//	public double getDEF() {
//		return DEF;
//	}
//
//	public void setDEF(double dEF) {
//		DEF = dEF;
//	}
//
//	public double getINT() {
//		return INT;
//	}
//
//	public void setINT(double iNT) {
//		INT = iNT;
//	}
//
//	public double getRES() {
//		return RES;
//	}
//
//	public void setRES(double rES) {
//		RES = rES;
//	}
//
//	public double getDEX() {
//		return DEX;
//	}
//
//	public void setDEX(double dEX) {
//		DEX = dEX;
//	}
//
//	public double getCRIT() {
//		return CRIT;
//	}
//
//	public void setCRIT(double cRIT) {
//		CRIT = cRIT;
//	}

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public List<World> getCreatedWorlds() {
		return createdWorlds;
	}

	public void setCreatedWorlds(List<World> createdWorlds) {
		this.createdWorlds = createdWorlds;
	}

	public List<Follower> getFollowerRoles() {
		return followerRoles;
	}

	public void setFollowerRoles(List<Follower> followerRoles) {
		this.followerRoles = followerRoles;
	}
}

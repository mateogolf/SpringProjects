package com.mattr.lookify.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Song {
	//id
	 @Id
	 @GeneratedValue
	 private Long id;
	
	 @Column
	 @Size(min = 5)
	 private String title;
	 
	
	 @Column
	 @Size(min = 5)
	 private String artist;
	 
	 @Column
	 @Range(min = 1, max = 10)
	 private int rating;
	
	 @Column
	 @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	 private Date created_at;
	
	 @Column
	 @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	 private Date updated_at;
	
	public Song() {
	 
	}
	public Song(String title, String artist, int rating) {
		this.title = title;
		this.artist = artist;
		this.rating = rating;
		this.created_at = new Date();
		this.updated_at = new Date();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	@PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }
}

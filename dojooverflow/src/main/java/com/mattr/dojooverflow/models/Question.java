package com.mattr.dojooverflow.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
public class Question {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min=5)
	private String question;
	
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date created_at;
	
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date updated_at;
	
	@Column
	@OneToMany(mappedBy="question",fetch= FetchType.LAZY)
	private List<Answer> answers;
	
	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name="questions_tags",
		joinColumns = @JoinColumn(name = "question_id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Tag> tags;

	public Question() {
		
	}

	public Question(String question) {
		this.question = question;
		this.created_at = new Date();
		this.updated_at = new Date();
		
	}

	@PrePersist
	protected void onCreate(){
	this.created_at = new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
		this.updated_at = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String tagsToString() {
        String result = "";
        for (int i = 0; i < this.tags.size() - 1; i++) {
            result += this.tags.get(i).getSubject() + ", ";
        }
        result += this.tags.get(this.tags.size() - 1).getSubject();
        return result;
    }
}

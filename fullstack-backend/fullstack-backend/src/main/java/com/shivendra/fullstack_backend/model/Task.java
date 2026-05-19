package com.shivendra.fullstack_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String start_date;
	private String target_date;
	private String task_desc;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // Foreign key column in 'task' table
	@JsonIgnore
    private User user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getTarget_date() {
		return target_date;
	}
	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}
	public String getTask_desc() {
		return task_desc;
	}
	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}
	
}

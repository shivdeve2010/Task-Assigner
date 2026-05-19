package com.shivendra.fullstack_backend.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String name;
	private String email;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Task> tasks;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Task> getTasks() {
	    return tasks;
	}

	public void setTasks(List<Task> tasks) {
	    this.tasks = tasks;
	}
	
   
}

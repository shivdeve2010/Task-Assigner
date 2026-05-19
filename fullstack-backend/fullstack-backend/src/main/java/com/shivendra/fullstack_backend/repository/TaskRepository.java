package com.shivendra.fullstack_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivendra.fullstack_backend.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{
	
   
}

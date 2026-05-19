package com.shivendra.fullstack_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shivendra.fullstack_backend.exception.UserNotFoundException;
import com.shivendra.fullstack_backend.model.Task;
import com.shivendra.fullstack_backend.model.User;
import com.shivendra.fullstack_backend.repository.TaskRepository;
import com.shivendra.fullstack_backend.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // ➕ Assign new task to a specific user
    @PostMapping("/user/{userId}/task")
    Task assignTaskToUser(@PathVariable Long userId, @RequestBody Task newTask) {
        return userRepository.findById(userId)
                .map(user -> {
                    newTask.setUser(user);
                    return taskRepository.save(newTask);
                }).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
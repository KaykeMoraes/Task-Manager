package com.kayke.task_manager.service;

import com.kayke.task_manager.entity.ToDo;
import com.kayke.task_manager.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoService{

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getCompletedTodos() {
        return toDoRepository.findByDoneTrue();  // Returns all completed todos
    }

    public List<ToDo> getPendingTodos() {
        return toDoRepository.findByDoneFalse(); // Returns all pending todos
    }

    public List<ToDo> getTodosByStatus(Boolean done) {
        return toDoRepository.findByDone(done);  // Can pass true or false
    }

    public List<ToDo> getRecentTodos() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return toDoRepository.findByCreatedAtAfter(yesterday);
    }
}

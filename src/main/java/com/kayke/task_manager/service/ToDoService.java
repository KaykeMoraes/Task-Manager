package com.kayke.task_manager.service;

import com.kayke.task_manager.entity.ToDo;
import com.kayke.task_manager.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    public Optional<ToDo> getTodoById(String id) {
        return toDoRepository.findById(id);
    }

    public List<ToDo> getCompletedTodos() {
        return toDoRepository.findByDoneTrue();
    }

    public List<ToDo> getPendingTodos() {
        return toDoRepository.findByDoneFalse();
    }

    public List<ToDo> getTodosByTitle(String title) {
        return toDoRepository.findByTitle(title);
    }

    public List<ToDo> getTodosByTitleContainingIgnoreCase(String title) {
        return toDoRepository.findByTitleContainingIgnoreCase(title);
    }

    public ToDo createTodo(ToDo toDo) {
        toDo.setDone(false);
        toDo.setCreatedAt(LocalDateTime.now());
        return toDoRepository.save(toDo);
    }

    public ToDo createTodo(String title, String description) {
        ToDo toDo = new ToDo();
        toDo.setTitle(title);
        toDo.setDescription(description);
        toDo.setDone(false);
        toDo.setCreatedAt(LocalDateTime.now());
        return toDoRepository.save(toDo);
    }

    public Optional<ToDo> updateTodo(String id, ToDo toDoDetails) {
        return toDoRepository.findById(id)
                .map(existingTodo -> {
                    existingTodo.setTitle(toDoDetails.getTitle());
                    existingTodo.setDescription(toDoDetails.getDescription());
                    return toDoRepository.save(existingTodo);
                });
    }

    public Optional<ToDo> setTodoStatus(String id) {
        return toDoRepository.findById(id)
                .map(todo -> {
                    todo.setDone(!todo.getDone());
                    if (todo.getDone()) {
                        todo.setCompletedAt(LocalDateTime.now());
                    } else {
                        todo.setCompletedAt(null);
                    }
                    return toDoRepository.save(todo);
                });
    }
    
    public boolean deleteTodo(String id) {
        if (toDoRepository.existsById(id)) {
            return false;
        }
        toDoRepository.deleteById(id);
        return true;
    }

    public boolean deleteCompletedTodos() {
        List<ToDo> completedTodos = toDoRepository.findByDoneTrue();
        if (toDoRepository.findByDoneTrue().isEmpty()) {
            return false;
        }
        toDoRepository.deleteAll(completedTodos);
        return true;
    }

    public boolean deleteTodoByTitle(String title) {
        List<ToDo> toDos = toDoRepository.findByTitle(title);
        if (toDoRepository.findByTitle(title).isEmpty()) {
            return false;
        }
        toDoRepository.deleteAll(toDos);
        return true;
    }
}
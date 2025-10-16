package com.kayke.task_manager.controller;

import com.kayke.task_manager.entity.ToDo;
import com.kayke.task_manager.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public List<ToDo> getAllTodos() {
        return toDoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getTodoById(@PathVariable String id) {
        return toDoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // search title by exact matches
    @GetMapping("/search/exact")
    public List<ToDo> getTodosByTitle(@RequestParam String title) {
        return toDoService.getTodosByTitle(title);
    }

    // search by containing words in the title
    @GetMapping("/search/contain")
    public List<ToDo> searchTodosByTitle(@RequestParam String title) {
        return toDoService.getTodosByTitleContainingIgnoreCase(title);
    }

    @GetMapping("/completed")
    public List<ToDo> getCompletedTodos() {
        return toDoService.getCompletedTodos();
    }

    @GetMapping("/pending")
    public List<ToDo> getPendingTodos() {
        return toDoService.getPendingTodos();
    }

    @PostMapping
    public ToDo createTodo(@RequestBody ToDo toDo) {
        return toDoService.createTodo(toDo);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable String id, @RequestBody ToDo toDoDetails) {
        return toDoService.updateTodo(id, toDoDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("status/{id}")
    public ResponseEntity<ToDo> setTodoStatus(@PathVariable String id) {
        return toDoService.setTodoStatus(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
        if (toDoService.deleteTodo(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/completed")
    public ResponseEntity<Void> deleteCompletedTodos() {
        if (toDoService.deleteCompletedTodos()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/title")
    public ResponseEntity<Void> deleteByTitle(@RequestParam String title) {
        if (toDoService.deleteTodoByTitle(title)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
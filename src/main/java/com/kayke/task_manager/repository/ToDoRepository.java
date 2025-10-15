package com.kayke.task_manager.repository;

import com.kayke.task_manager.entity.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ToDoRepository extends MongoRepository<ToDo, String> {

    List<ToDo> findByDone(Boolean done);

    List<ToDo> findByDoneFalse();

    List<ToDo> findByDoneTrue();

    List<ToDo> findByTitle(String title);

    List<ToDo> findByCreatedAtAfter(LocalDateTime date);

}

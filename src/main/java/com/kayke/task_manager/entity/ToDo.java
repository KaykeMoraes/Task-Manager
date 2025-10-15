package com.kayke.task_manager.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "todo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToDo {

    @Id
    private String id;
    private String title;
    private String description;
    private Boolean done = false;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

}

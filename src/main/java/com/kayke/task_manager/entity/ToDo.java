package com.kayke.task_manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime completedAt;

}

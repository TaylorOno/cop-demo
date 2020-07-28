package com.taylor.ono.copdemo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taylor.ono.copdemo.entities.Task;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TaskResponse {
    private String id;
    private String title;
    private Date dueDate;
    private int priority;

    private String description;

    public TaskResponse(Task task) {
        id = task.getId();
        title = task.getTitle();
        dueDate = task.getDueDate();
        priority = task.getPriority();
        description = task.getDescription();
    }
}

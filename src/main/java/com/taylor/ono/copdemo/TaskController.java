package com.taylor.ono.copdemo;

import com.taylor.ono.copdemo.dtos.CreateTaskRequest;
import com.taylor.ono.copdemo.dtos.TaskResponse;
import com.taylor.ono.copdemo.entities.Task;
import com.taylor.ono.copdemo.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@Validated
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody CreateTaskRequest createTaskRequest) {
        TaskResponse response = new TaskResponse(taskService.addTask(new Task(createTaskRequest)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> getTasks() {
        List<TaskResponse> response = taskService.getAllTasks().stream().map(TaskResponse::new).collect(toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable String id) throws TaskNotFoundException, InterruptedException {
        TaskResponse task = taskService.getTask(id).map(TaskResponse::new).orElseThrow(TaskNotFoundException::new);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}

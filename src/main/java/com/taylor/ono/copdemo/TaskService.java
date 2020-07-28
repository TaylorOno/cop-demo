package com.taylor.ono.copdemo;

import com.taylor.ono.copdemo.entities.Task;
import com.taylor.ono.copdemo.repositories.TasksRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TasksRepository tasksRepository;

    public TaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Task addTask(Task t) {
        return tasksRepository.save(t);
    }

    public List<Task> getAllTasks() {
        return tasksRepository.findAll();
    }

    @Cacheable("getTask")
    public Optional<Task> getTask(String id) throws InterruptedException {
        simulateSlowService();
        return tasksRepository.findById(id);
    }

    // Don't do this at home
    private void simulateSlowService() throws InterruptedException {
        Thread.sleep(3000L);
    }
}

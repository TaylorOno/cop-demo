package com.taylor.ono.copdemo.repositories;


import com.taylor.ono.copdemo.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends CrudRepository<Task, String> {
    List<Task> findAll();
}

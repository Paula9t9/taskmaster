package com.paula9t9.taskmaster;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface TaskRepository extends CrudRepository<Task, String> {
    List<Task> findByAssignee(String assignee);
}

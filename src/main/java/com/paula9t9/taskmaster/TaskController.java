package com.paula9t9.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/tasks")
    public ResponseEntity getTasks(){
        List<Task> results = (List<Task>) taskRepository.findAll();

        return new ResponseEntity(results, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public RedirectView postTasks(String title, String description){
        Task newTask = new Task(title, description);

        taskRepository.save(newTask);

        return new RedirectView("/tasks");
    }

    @PutMapping("tasks/{id}/state")
    public ResponseEntity putState(@PathVariable String id){
        //get task with id
        Task task = taskRepository.findById(id).get();
        String currentStatus = task.getStatus();

        //Advance the status depending on where it currently is
        if(currentStatus.equals("Available")){
            task.setStatus("Assigned");
        }else if(currentStatus.equals("Assigned")){
            task.setStatus("Accepted");
        }else if(currentStatus.equals("Accepted")){
            task.setStatus("Finished");
        } // Don't need one for finished. If finished, we do nothing.

        taskRepository.save(task);

        return new ResponseEntity(task, HttpStatus.OK);
    }
}

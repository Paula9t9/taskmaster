package com.paula9t9.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    //Save new tasks to the database
    @PostMapping("/tasks")
    public ResponseEntity postTasks(String title, String description, String assignee){

        //If there is no assignee, use the appropriate constructor
        Task newTask;
        if(assignee == null){
            newTask = new Task(title, description);
        }else {
            newTask = new Task(title, description, assignee);
        }


        taskRepository.save(newTask);

        return new ResponseEntity(newTask, HttpStatus.OK);
    }

    //Update the state of a task
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

    //Get all tasks for a particular user
    @GetMapping("/users/{name}/tasks")
    public ResponseEntity getUserTasks(@PathVariable String name){
        List<Task> results = taskRepository.findByAssignee(name);

        return new ResponseEntity(results, HttpStatus.OK);
    }

    //Change the assigned user on a task
    @PatchMapping("tasks/{id}/assign/{assignee}")
    public ResponseEntity updateAssignee(@PathVariable String id, @PathVariable String assignee){
        //TODO: catch 405 error if no task by that id, also update in other routes
        Task task = taskRepository.findById(id).get();

        task.setAssignee(assignee);

        taskRepository.save(task);

        return new ResponseEntity(task, HttpStatus.OK);

    }

}

package com.paula9t9.taskmaster;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.sun.javafx.binding.StringFormatter;

import java.util.UUID;

@DynamoDBTable(tableName = "Tasks")
public class Task {

    private String id;

    private String title;
    private String description;
    private String status;
    private String assignee;

    public Task() { }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "Available";
    }

    public Task(String title, String description, String assignee) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.status = "Assigned";
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @DynamoDBAttribute
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {

        StringBuilder taskString = new StringBuilder();
        taskString.append("Task title: ");
        taskString.append(this.title);
        taskString.append("\nDescription: ");
        taskString.append(this.description);

        return taskString.toString();
    }
}

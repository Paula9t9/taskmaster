# Taskmaster

Manage your tasks! 

This project is a part of a series of Codefellows assignments designed to build familiarity with AWS services.

## Features

GET /tasks - get a list of all tasks currently in the database. 

POST /tasks - add a new task to the database - add title and description

## Technologies Used:

DynamoDB

SpringBoot

## Changelog

07-03-2019:

Added the following functionality:

GET /users/{name}/tasks - get all tasks for a particular assignee

PUT /tasks/{id}/state - update the state of the tasks
            
 * State advances from Assigned -> Accepted -> Finished
 
POST /tasks - add a new task - can now post with assignee in addition to title and description 

PATCH /tasks/{id}/assign/{assignee} - update who is assigned to a task



 
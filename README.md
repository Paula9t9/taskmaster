# Taskmaster

Manage your tasks! 

This project is a part of a series of Codefellows assignments designed to build familiarity with AWS services.

You can find the live deployment [here.](http://paula9t9-taskmaster.us-east-2.elasticbeanstalk.com/tasks)

## Features

GET /tasks - get a list of all tasks currently in the database. 

POST /tasks - add a new task to the database - add title and description

GET /users/{name}/tasks - get all tasks for a particular assignee

PUT /tasks/{id}/state - update the state of the tasks

 * State advances from Assigned -> Accepted -> Finished
 
POST /tasks - add a new task - can now post with assignee in addition to title and description 

PATCH /tasks/{id}/assign/{assignee} - update who is assigned to a task

POST /tasks/{id}/images - Upload an image to the given task

## Technologies Used:

DynamoDB

S3

SpringBoot

## Changelog

07-03-2019:

Added the following functionality:

GET /users/{name}/tasks - get all tasks for a particular assignee

PUT /tasks/{id}/state - update the state of the tasks
            
 * State advances from Assigned -> Accepted -> Finished
 
POST /tasks - add a new task - can now post with assignee in addition to title and description 

PATCH /tasks/{id}/assign/{assignee} - update who is assigned to a task

07-09-2019

Added the ability to upload images. 



 
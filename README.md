# Todos UI

## Overview

Your task is to create an application that allows you to track, add, and delete tasks as well as manage categories of tasks.

![Todos UI Example](./todos_app.PNG)

## MVP

- Must be able to add categories
- Must be able to add new tasks tagged with a task category
- Must be able to update tasks automatically by changing the task name and the category
- Must be able to duplicate tasks
- Must be able to delete tasks
- You must add your own styling

## Bonus

- Come up with a feature that allows us to delete and update task categories
- Create a summary section that lists how many of each type of task there are

# Todos API

## Overview

Create an API to be integrated with your [todos-ui](../todos-ui/) project, that allows you to store and retrieve tasks from a database.

## MVP

- Deleting a task should set an `isArchived` flag in the database instead of deleting the task from the database
- Add a filter to the frontend application that allows you to filter tasks by category
- Categories and Todos should be stored in separate tables

## Endpoints

- `GET /categories`
- `POST /categories`
- `PUT /categories/:id`
- `DELETE /categories/:id`

- `GET /todos`
- `GET /todos?category={}`
- `POST /todos`
- `PUT/PATCH /todos/:id`
- `DELETE /todos/:id`

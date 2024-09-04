# TODO List App - Spring API

## Demo & Snippets

[Link to live]() (NOT YET DEPLOYED)

---

## Requirements / Purpose

This project is the corresponding backend for the [todo-ui](https://github.com/vadien/todo-app-frontend) project. The aim is to create a functional API allowing data storage and retrieval, using a full controller-service-repository model, linking to a MySQL database with relational mapping.

##### Stack:

- Java 21
- Spring Boot
- Maven
- MySQL (database)
- JUnit/REST assured (testing)
- H2 Database (testing only)

---

## Build Steps

- Clone the repo to a local folder
- Create a new MySQL database called `todo_api`
- Configure `src/main/application.properties` with your relevant information, particularly the port connected to your MySQL database and, if required, your PC's login information
- Ensure your build is set to use Java 21 in the pom.xml
- Build the project using `mvn package`
- Start the project using `mvn spring-boot:run`
- Jump over to [todo-ui](https://github.com/vadien/todo-app-frontend) to run the frontend!

---

## Design Goals / Approach

#### Goals:

- To implement a complete backend API/database that utilises relational mapping with a corresponding frontend.
- To follow separation of concerns and DRY coding principles.
- To test the application using integration and unit testing.
- To successfully integrate the app with the frontend UI.

---

## Features

- GET/POST/PATCH/DELETE API endpoints for Category and Todo
- Associated DTOs for above endpoints
- Exception/error handling
- E2E and unit testing (in progress)

---

## Known issues

- Incomplete testing suite

---

## Future Goals

- Implement backend sort for find Todos method (once I figure out how)
- Due date/timestamp per todo
- Sorting by category/update timestamp/due timestamp
- Search/filter by category/todo
- Possible data sanitising on submit (e.g. capitalisation)
- Complete testing suite
- Implement CI/CD using github workflow
- Docker deployment to cloud
- Full refactoring pass

---

## Change logs

##### 2024-09-04

- Standardise error handling across domains
- Initialise Github workflow for backend tests
- Fix data integrity issue in testing suite
- Begin unit test coverage

##### 2024-09-03

- Separate archive functionality from task completion functionality
- Add build steps to README

##### 2024-09-02

- Complete implementing archive
- Update README

##### 2024-09-01

- Add E2E category testing
- Partial implement archiving (todo completion)

##### 2024-08-31

- Refactoring, add PrePersist and PreUpdate to BaseEntity
- Implement map between todo/category tables, update services/DTOs accordingly
- Add common exception and error handling across app

---

## What did you struggle with?

- First project using JUnit/REST Assured testing, ran into difficulties isolating and sanitising tests.

---

## Licensing Details

MIT License

Copyright (c) 2024 David Neill

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

# TODO List App - Spring API

## Demo & Snippets

[Link to live]() (NOT YET DEPLOYED)

---

## Requirements / Purpose

This project is the corresponding backend for the [todo-ui](https://github.com/vadien/todo-app-frontend) project. The aim is to create a functional API allowing data storage and retrieval, using a full controller-service-repository model, linking to a MySQL database with relational mapping.

Stack:

- Spring Boot
- Maven
- JUnit/REST assured (testing)
- H2 Database (testing only)

---

## Design Goals / Approach

#### Goals:

- To implement a complete backend API/database that utilises relational mapping with a corresponding frontend.
- To follow separation of concerns and DRY coding principles.
- To test the application using integration and unit testing.

---

## Features

- GET/POST/PATCH/DELETE API endpoints for Category and Todo
- Associated DTOs for above endpoints
- Exception/error handling
- E2E and unit testing (in progress)

---

## Known issues

- Incomplete testing suite
- Testing suite passes individually but not concurrently

---

## Future Goals

- Due date/timestamp per todo
- Sorting by category/update timestamp/due timestamp
- Search/filter by category/todo
- Complete testing suite and github workflow
- Docker deployment to cloud
- Full refactoring pass

---

## Change logs

- Write a paragraph labelled with the date every day you work on the project to discuss what you've done for the say. Be specific about the changes that have happened for that day.

##### 2024-08-31

- Refactoring, add PrePersist and PreUpdate to BaseEntity
- Implement map between todo/category tables, update services/DTOs accordingly
- Add common exception and error handling across app

##### 2024-09-01

- Add E2E category testing
- Partial implement archiving (todo completion)

##### 2024-09-02

- Complete implementing archive
- README rewrite

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

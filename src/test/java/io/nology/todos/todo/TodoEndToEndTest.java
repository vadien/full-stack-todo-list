package io.nology.todos.todo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import io.nology.todos.category.Category;
import io.nology.todos.category.CategoryRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
// @TestMethodOrder(MethodOrderer.MethodName.class)
public class TodoEndToEndTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TodoRepository todoRepository;
    // DOUBLE CHECK ALL DATA IS VALID

    @Autowired
    private CategoryRepository categoryRepository;
    // DOUBLE CHECK ALL DATA IS VALID

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        Category category1 = new Category();
        category1.setName("sandwiches");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("burgers");
        categoryRepository.save(category2);

        Todo todo1 = new Todo();
        todo1.setTitle("Make a sandwich");
        todo1.setCategory(category1);
        todo1.setArchived(false);
        todoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("Make a burger");
        todo2.setCategory(category1);
        todo2.setArchived(false);
        todoRepository.save(todo2);
        todoRepository.flush();
    }

    @Test
    public void getAllTodos() {
        given()
                .when().get("/todos")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(2))
                .body("title", hasItems("Make a sandwich", "Make a burger"))
                .body(matchesJsonSchemaInClasspath("io/nology/todos/todo/schemas/todos-schema.json"));
    }

    @Test
    public void getTodoById() {
        given()
                .when().get("/todos/1")
                .then().statusCode(HttpStatus.OK.value())
                .body("title", equalTo("Make a sandwich"))
                .body(matchesJsonSchemaInClasspath("io/nology/todos/todo/schemas/todo-schema.json"));

    }

    @Test
    public void createTodo_success() {
        CreateTodoDTO data = new CreateTodoDTO();
        data.setTitle("created todo");
        data.setCategoryId(2L);
        given()
                .contentType(ContentType.JSON).body(data)
                .when().post("/todos")
                .then().statusCode(HttpStatus.CREATED.value())
                .body("title", equalTo("created todo"))
                .body("id", notNullValue())
                .body(matchesJsonSchemaInClasspath("io/nology/todos/todo/schemas/todo-schema.json"));

        // then check it's in findAll
        given()
                .when().get("/todos")
                .then().statusCode(HttpStatus.OK.value())
                .body("$", hasSize(3))
                .body("title", hasItems("Make a sandwich", "Make a burger", "created todo"));
    }

    // @Test
    // public void createTodo_failure
    // UPDATE TESTS

    @AfterEach
    public void tearDown() {
        todoRepository.deleteAll();
        categoryRepository.deleteAll();
    }

}

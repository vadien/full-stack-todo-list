package io.nology.todos.category;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CategoryEndToEndTest {
    @LocalServerPort
    private int port;

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
    }

    @Test
    public void getAllCategories() {
        given()
                .when().get("/categories")
                .then().statusCode(HttpStatus.OK.value())
                .body("$", hasSize(2))
                .body("name", hasItems("burgers", "sandwiches"))
                .body(matchesJsonSchemaInClasspath("io/nology/todos/category/schemas/categories-schema.json"));
    }

    @Test
    // Create category succeeds
    public void createCategory_success() {
        CreateCategoryDTO data = new CreateCategoryDTO();
        data.setName("created category");
        given()
                .contentType(ContentType.JSON).body(data)
                .when().post("/categories")
                .then().statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo("created category"))
                .body("id", notNullValue())
                .body(matchesJsonSchemaInClasspath("io/nology/todos/category/schemas/category-schema.json"));

        // then check it's in findAll
        given()
                .when().get("/categories")
                .then().statusCode(HttpStatus.OK.value())
                .body("$", hasSize(3))
                .body("name", hasItems("burgers", "sandwiches", "created category"));
    }

    @Test
    // Create category fails due to existing category
    public void createCategory_unique_failure() {
        CreateCategoryDTO data = new CreateCategoryDTO();
        data.setName("sandwiches");
        given()
                .contentType(ContentType.JSON).body(data)
                .when().post("/categories")
                .then().statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath(
                        "io/nology/todos/category/schemas/unique-category-failure-schema.json"))
                .body("errors.name[0]", equalTo("'sandwiches' category already exists"));
    }

    @Test
    // Create category fails due to empty string
    public void createCategory_emptyCategory_failure() {
        CreateCategoryDTO data = new CreateCategoryDTO();
        data.setName("");
        given()
                .contentType(ContentType.JSON).body(data)
                .when().post("/categories")
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    // Create category fails due to null value
    public void createCategory_nullCategory_failure() {
        CreateCategoryDTO data = new CreateCategoryDTO();
        given()
                .contentType(ContentType.JSON).body(data)
                .when().post("/categories")
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }
}

package io.nology.todos.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nology.todos.todo.Todo;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CreateCategoryDTO data) throws Exception {
        Category newCategory = this.categoryService.create(data);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = this.categoryService.findAll();
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@Valid @PathVariable Long id) throws Exception {
        Optional<Category> result = this.categoryService.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Could not find category with id" + id);
        }
        Category foundCategory = result.get();
        return new ResponseEntity<>(foundCategory, HttpStatus.OK);
    }

    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid CreateCategoryDTO data)
            throws Exception {
        Category updatedCategory = this.categoryService.updateCategory(id, data);
        return new ResponseEntity<Category>(updatedCategory, HttpStatus.CREATED);
    }

    // DELETE

}

package io.nology.todos.category;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.todos.common.ValidationErrors;
import io.nology.todos.common.exceptions.ServiceValidationException;
import jakarta.validation.Valid;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public Category create(@Valid CreateCategoryDTO data) throws Exception {
        ValidationErrors errors = new ValidationErrors();
        String formattedName = data.getName().trim().toLowerCase();
        if (repo.existsByName(formattedName)) {
            errors.addError("name", String.format("'%s' category already exists", formattedName));
        }
        if (!errors.isEmpty()) {
            throw new ServiceValidationException(errors);
        }
        Category newCategory = new Category();
        newCategory.setName(formattedName);
        return this.repo.save(newCategory);
    }

    public List<Category> findAll() {
        return this.repo.findAll();
    }

    public Optional<Category> findById(Long id) {
        return this.repo.findById(id);
    }

    public Category updateCategory(Long id, CreateCategoryDTO data) throws Exception {
        ValidationErrors errors = new ValidationErrors();
        Optional<Category> maybeCategory = this.repo.findById(id);
        if (maybeCategory.isEmpty()) {
            errors.addError("category", "Attempted to update category which does not exist");
        }
        Category foundCategory = maybeCategory.get();
        String formattedName = data.getName().trim().toLowerCase();
        if (repo.existsByName(formattedName)) {
            errors.addError("category",
                    String.format("Attempted to rename category to '%s' which already exists", formattedName));
        }
        if (!errors.isEmpty()) {
            throw new ServiceValidationException(errors);
        }
        foundCategory.setName(formattedName);
        return this.repo.save(foundCategory);
    }

    public void deleteCategoryById(Long id) throws Exception {
        ValidationErrors errors = new ValidationErrors();
        Optional<Category> maybeCategory = this.repo.findById(id);
        if (maybeCategory.isEmpty()) {
            errors.addError("category",
                    "Attempted to delete category which does not exist");
        }
        this.repo.deleteById(id);
    }
}

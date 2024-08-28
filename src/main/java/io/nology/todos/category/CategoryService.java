package io.nology.todos.category;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public Category create(@Valid CreateCategoryDTO data) throws Exception {
        String formattedName = data.getName().trim().toLowerCase();
        if (repo.existsByName(formattedName)) {
            throw new Exception(String.format("'%s' category already exists", formattedName));
        }
        Category newCategory = new Category();
        newCategory.setName(formattedName);
        newCategory.setCreatedAt(new Date());
        newCategory.setUpdatedAt(new Date());
        return this.repo.save(newCategory);
    }

    public List<Category> findAll() {
        return this.repo.findAll();
    }

    public Optional<Category> findById(Long id) {
        return this.repo.findById(id);
    }

    public Category updateCategory(Long id, CreateCategoryDTO data) throws Exception {
        Optional<Category> maybeCategory = this.repo.findById(id);
        if (maybeCategory.isEmpty()) {
            throw new Exception("No category found with id" + id);
        }
        Category foundCategory = maybeCategory.get();
        String formattedName = data.getName().trim().toLowerCase();
        if (repo.existsByName(formattedName)) {
            throw new Exception(String.format("'%s' category already exists", formattedName));
        }
        foundCategory.setName(formattedName);
        return this.repo.save(foundCategory);
    }

    public void deleteCategoryById(Long id) throws Exception {
        Optional<Category> maybeCategory = this.repo.findById(id);
        if (maybeCategory.isEmpty()) {
            throw new Exception("No category found with id" + id);
        }
        this.repo.deleteById(id);
    }
}

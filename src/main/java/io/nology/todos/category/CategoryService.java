package io.nology.todos.category;

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
        return this.repo.save(newCategory);
    }
}

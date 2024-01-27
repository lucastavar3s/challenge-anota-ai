package com.lucastavares.desafioanotaai.services;

import com.lucastavares.desafioanotaai.domain.category.Category;
import com.lucastavares.desafioanotaai.domain.category.CategoryDTO;
import com.lucastavares.desafioanotaai.domain.category.exceptions.CategoryNotFoudException;
import com.lucastavares.desafioanotaai.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional
    public Category insert(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        return newCategory;
    }

    public List<Category> findAll() {
        return  repository.findAll();
    }


    public Category update(String id, CategoryDTO categoryData) {
        Category category = repository.findById(id)
                .orElseThrow(CategoryNotFoudException::new);

        if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if (!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        repository.save(category);

        return category;
    }

    public void delete(String id) {
        Category category = repository.findById(id)
                .orElseThrow(CategoryNotFoudException::new);
        repository.delete(category);
    }
}

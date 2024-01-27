package com.lucastavares.desafioanotaai.controllers;

import com.lucastavares.desafioanotaai.domain.category.Category;
import com.lucastavares.desafioanotaai.domain.category.CategoryDTO;
import com.lucastavares.desafioanotaai.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;
    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData){
        Category newCategory = service.insert(categoryData);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categories = service.findAll();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update (@PathParam("id") String id, @RequestBody CategoryDTO categoryData){
        Category newCategory = service.update(id, categoryData);
        return ResponseEntity.ok(newCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathParam("id")String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

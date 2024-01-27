package com.lucastavares.desafioanotaai.controllers;

import com.lucastavares.desafioanotaai.domain.product.Product;
import com.lucastavares.desafioanotaai.domain.product.ProductDTO;
import com.lucastavares.desafioanotaai.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> insert(ProductDTO productData){
        Product newProduct = productService.insert(productData);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathParam("id") String id, ProductDTO productData){
        Product newProduct = productService.update(id, productData);
        return ResponseEntity.ok(newProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathParam("id") String id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

}

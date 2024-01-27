package com.lucastavares.desafioanotaai.services;

import com.lucastavares.desafioanotaai.domain.category.Category;
import com.lucastavares.desafioanotaai.domain.category.exceptions.CategoryNotFoudException;
import com.lucastavares.desafioanotaai.domain.product.Product;
import com.lucastavares.desafioanotaai.domain.product.ProductDTO;
import com.lucastavares.desafioanotaai.domain.product.ProductNotFoundException;
import com.lucastavares.desafioanotaai.repository.CategoryRepository;
import com.lucastavares.desafioanotaai.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    public Product insert(ProductDTO productData) {
        Category category = categoryService.findById(productData.categoryId())
                .orElseThrow(CategoryNotFoudException::new);

        Product newProduct = new Product(productData);

        newProduct.setCategory(category);

        productRepository.save(newProduct);

        return newProduct;
    }

    public Product update(String id, ProductDTO productData) {

        Product product = productRepository.findById(id)
                        .orElseThrow(ProductNotFoundException::new);

        categoryService.findById(productData.categoryId())
                .ifPresent(product::setCategory);

        if (!productData.title().isEmpty()) product.setTitle(product.getTitle());
        if (!productData.description().isEmpty()) product.setDescription(product.getDescription());
        if (!(productData.price() == null)) product.setPrice(product.getPrice());

        productRepository.save(product);

        return product;

    }

    public void delete(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        productRepository.delete(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

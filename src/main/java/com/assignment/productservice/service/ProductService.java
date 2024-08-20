package com.assignment.productservice.service;

import com.assignment.productservice.model.Product;
import com.assignment.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    // Create or Update Product
    public Product saveProduct(Product product) {
        logger.info("Saving product: {}", product.getName());
        return productRepository.save(product);
    }

    // Read - Get All Products
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    // Read - Get Product by ID
    public Optional<Product> getProductById(Long id) {
        logger.debug("Fetching product with id: {}", id);
        return productRepository.findById(id);
    }

    // Delete Product
    public void deleteProduct(Long id) {
        logger.warn("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }
}

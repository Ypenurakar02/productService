package com.assignment.productservice.controller;

import com.assignment.productservice.model.Product;
import com.assignment.productservice.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @ApiOperation( value = "Get all products" )
    @ApiResponses( value = {
            @ApiResponse( code = 500, message = "Error fetching product" ),
            @ApiResponse( code = 200, message = "Success" ),
            @ApiResponse( code = 400, message = "Product not found" )
    } )
    @GetMapping
    public List<Product> getAllProducts() {
        logger.info("Received request to fetch all products");
        return productService.getAllProducts();
    }

    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @ApiOperation( value = "Get a product by id" )
    @ApiResponses( value = {
            @ApiResponse( code = 500, message = "Error fetching product" ),
            @ApiResponse( code = 200, message = "Success" ),
            @ApiResponse( code = 400, message = "Product not found" )
    } )
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.info("Received request to fetch product with id: {}", id);
        return productService.getProductById(id)
                .map( (product) -> {
                    logger.info("Product found: {}", product.getName());
                    return new ResponseEntity<>(product, HttpStatus.OK); } )
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @ApiOperation( value = "Create a new product")
    @ApiResponses( value = {
            @ApiResponse( code = 500, message = "Error creating product" ),
            @ApiResponse( code = 200, message = "Product created" )
    } )
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        logger.info("Received request to create product: {}", product.getName());
        Product savedProduct = productService.saveProduct(product);
        logger.info("Product created with id: {}", savedProduct.getId());
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @ApiOperation( value = "Update a product by id")
    @ApiResponses( value = {
            @ApiResponse( code = 500, message = "Error updating product" ),
            @ApiResponse( code = 200, message = "Product Updated" )
    } )
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        logger.info("Received request to update product with id: {}", id);
        return productService.getProductById(id)
                .map(product -> {
                    logger.info("Updating product with id: {}", id);
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setPrice(productDetails.getPrice());
                    Product updatedProduct = productService.saveProduct(product);
                    logger.info("Product updated with id: {}", updatedProduct.getId());
                    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @ApiOperation( value = "Delete a product by id")
    @ApiResponses( value = {
            @ApiResponse( code = 500, message = "Error deleting product" ),
            @ApiResponse( code = 204, message = "Product deleted" )
    } )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        logger.info("Received request to delete product with id: {}", id);
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            productService.deleteProduct(id);
            logger.warn("Product with id: {} deleted", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Product with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}

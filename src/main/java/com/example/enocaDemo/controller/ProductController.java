package com.example.enocaDemo.controller;

import com.example.enocaDemo.model.Product;
import com.example.enocaDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id ){
        return productService.getProduct(id);
    }
    @GetMapping
    public List<Product> getAllProduct(){
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable Long id){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}

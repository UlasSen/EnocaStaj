package com.example.enocaDemo.controller;

import com.example.enocaDemo.dto.ProductDto;
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
    public ProductDto getProduct(@PathVariable Long id ){
        return productService.getProduct(id);
    }
    @GetMapping
    public List<ProductDto> getAllProduct(){
        List<ProductDto> products = productService.getAllProducts();
        return products;
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@RequestBody ProductDto product,@PathVariable Long id){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}

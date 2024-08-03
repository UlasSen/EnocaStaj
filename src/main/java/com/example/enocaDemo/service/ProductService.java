package com.example.enocaDemo.service;

import com.example.enocaDemo.model.Product;
import com.example.enocaDemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private  ProductRepository productRepository;

    public Product getProduct(Long productId){
        return productRepository.findById(productId).orElse(null);

    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(Long id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        }
        return null;
    }

}

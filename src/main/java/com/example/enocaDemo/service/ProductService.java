package com.example.enocaDemo.service;

import com.example.enocaDemo.dto.ProductDto;
import com.example.enocaDemo.model.Product;
import com.example.enocaDemo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ProductDto getProduct(Long productId){

        Product product= productRepository.findById(productId).orElse(null);
        return modelMapper.map(product,ProductDto.class);

    }
    public List<ProductDto> getAllProducts() {
        List<Product> products= productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
    public ProductDto createProduct(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }
    public ProductDto updateProduct(Long id, ProductDto updatedProductDto) {
        if (productRepository.existsById(id)) {
            Product updatedProduct = modelMapper.map(updatedProductDto, Product.class);
            updatedProduct.setId(id);

            Product savedProduct = productRepository.save(updatedProduct);

            return modelMapper.map(savedProduct, ProductDto.class);
        }
        return null;
    }

}

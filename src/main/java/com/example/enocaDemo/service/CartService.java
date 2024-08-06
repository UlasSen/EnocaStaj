package com.example.enocaDemo.service;

import com.example.enocaDemo.dto.CartDto;
import com.example.enocaDemo.dto.CustomerDto;
import com.example.enocaDemo.model.Cart;
import com.example.enocaDemo.model.CartItem;
import com.example.enocaDemo.model.Customer;
import com.example.enocaDemo.model.Product;
import com.example.enocaDemo.repository.CartItemRepository;
import com.example.enocaDemo.repository.CartRepository;
import com.example.enocaDemo.repository.CustomerRepository;
import com.example.enocaDemo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CartDto getCart(Long cartId) {
        Cart cart= cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        return modelMapper.map(cart,CartDto.class);
    }
    public List<CartDto> getAllCart(){
         List<Cart> carts= cartRepository.findAll();
         return carts.stream()
                 .map(cart -> modelMapper.map(cart, CartDto.class))
                 .collect(Collectors.toList());
    }

    public CartDto addProductToCard(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId
        ).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));


        CartItem cartItem = cart.getItems().stream().
                filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(null);
        if (cartItem==null){
            cartItem= new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);

        }
        cartItem.setQuantity(cartItem.getQuantity()+1);
        cartItemRepository.save(cartItem);

        return modelMapper.map(cart,CartDto.class);

    }

    public CartDto removeProductToCard(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = cart.getItems().stream().
                filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new RuntimeException("Product not found in cart"));


        for (int i = 0; i < cart.getItems().size(); i++) {
            if (cart.getItems().get(i).getId()==cartItem.getId()){
                cart.getItems().remove(i);
                break;
            }
        }
        cartRepository.save(cart);
        return modelMapper.map(cart,CartDto.class);
    }

    public CartDto updateCartItem(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).
                orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = cart.getItems().stream().
                filter(item -> item.getProduct().
                        getId().equals(productId)).findFirst().
                orElseThrow(() -> new RuntimeException("Product not found in cart"));

        Product product = cartItem.getProduct();
        int quantityDifference = quantity - cartItem.getQuantity();

        if (product.getStock() < quantityDifference) {
            throw new RuntimeException("Not enough stock");
        }

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        product.setStock(product.getStock() - quantityDifference);
        productRepository.save(product);

        return modelMapper.map(cart,CartDto.class);

    }

    public CartDto emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        //Sepeti boşalttığımızda rünleri geri stoğa ekiyoruz.
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            product.setStock(product.getStock() + cartItem.getQuantity());
            productRepository.save(product);
        }

        cart.getItems().clear();
        return modelMapper.map(cart,CartDto.class);

    }
    public void createCart(Cart cart){
        cartRepository.save(cart);
    }
}



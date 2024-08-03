package com.example.enocaDemo.service;

import com.example.enocaDemo.model.Cart;
import com.example.enocaDemo.model.CartItem;
import com.example.enocaDemo.model.Customer;
import com.example.enocaDemo.model.Product;
import com.example.enocaDemo.repository.CartItemRepository;
import com.example.enocaDemo.repository.CartRepository;
import com.example.enocaDemo.repository.CustomerRepository;
import com.example.enocaDemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }

    public void addProductToCard(Long cartId, Long productId) {
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

    }

    public void removeProductToCard(Long cartId, Long productId) {
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
    }

    public void updateCartItem(Long cartId, Long productId, int quantity) {
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

    }

    public void emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        //Sepeti boşalttığımızda rünleri geri stoğa ekiyoruz.
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            product.setStock(product.getStock() + cartItem.getQuantity());
            productRepository.save(product);
        }

        cart.getItems().clear();
    }
    public void createCart(Cart cart){
        cartRepository.save(cart);
    }
}



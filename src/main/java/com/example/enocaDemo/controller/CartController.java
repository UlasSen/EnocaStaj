package com.example.enocaDemo.controller;

import com.example.enocaDemo.model.Cart;
import com.example.enocaDemo.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private CartService cartService;

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable Long cartId){
       return cartService.getCart(cartId);

    }
    @DeleteMapping("/{cartId}")
    public void emptyCart(@PathVariable Long cartId){
        cartService.emptyCart(cartId);
    }

    @PostMapping("/{cartId}/addProduct/{productId}")
    public void addProductToCart(@PathVariable Long cartId,
                                 @PathVariable Long productId
                                ){
        cartService.addProductToCard(cartId,productId);
    }

    @DeleteMapping("/{cartId}/removeProduct/{productId}")
    public void removeProductFromCart(@PathVariable Long cartId,
                                      @PathVariable Long productId
                                      ){
        cartService.removeProductToCard(cartId,productId);
    }


    public void updateCart(@PathVariable Long cartId,
                           @PathVariable Long productId,
                           @RequestParam int quantity){
         cartService.updateCartItem(cartId,productId,quantity);
    }

    @GetMapping
    public List<Cart> getAllCart(){
        return cartService.getAllCart();
    }


}

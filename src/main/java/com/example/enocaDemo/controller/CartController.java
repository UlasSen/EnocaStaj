package com.example.enocaDemo.controller;

import com.example.enocaDemo.dto.CartDto;
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
    public CartDto getCart(@PathVariable Long cartId){
       return cartService.getCart(cartId);

    }
    @DeleteMapping("/{cartId}")
    public CartDto emptyCart(@PathVariable Long cartId){
        return cartService.emptyCart(cartId);
    }

    @PostMapping("/{cartId}/addProduct/{productId}")
    public CartDto addProductToCart(@PathVariable Long cartId,
                                 @PathVariable Long productId
                                ){
       return cartService.addProductToCard(cartId,productId);
    }

    @DeleteMapping("/{cartId}/removeProduct/{productId}")
    public CartDto removeProductFromCart(@PathVariable Long cartId,
                                      @PathVariable Long productId
                                      ){
        return cartService.removeProductToCard(cartId,productId);
    }


    public CartDto updateCart(@PathVariable Long cartId,
                           @PathVariable Long productId,
                           @RequestParam int quantity){
         return cartService.updateCartItem(cartId,productId,quantity);
    }

    @GetMapping
    public List<CartDto> getAllCart(){
        return cartService.getAllCart();
    }


}

package com.cloudwave.cloudwavewebservice.controller;

import com.cloudwave.cloudwavewebservice.dto.cart.CartItemDto;
import com.cloudwave.cloudwavewebservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestHeader("Authorization") String accessToken, @RequestParam Long productId) {
        System.out.println(accessToken.substring(7));
        cartService.addToCart(accessToken.substring(7), productId);
        return ResponseEntity.ok("Product added to cart successfully.");
    }

    @GetMapping()
    public ResponseEntity<List<CartItemDto>> getUserCart(@RequestHeader("Authorization") String accessToken) {
        List<CartItemDto> userCart = cartService.getUserCart(accessToken.substring(7));
        return ResponseEntity.ok(userCart);
    }
}

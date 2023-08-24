package com.cloudwave.cloudwavewebservice.service;

import com.cloudwave.cloudwavewebservice.domain.cart.Cart;
import com.cloudwave.cloudwavewebservice.domain.cart.CartRepository;
import com.cloudwave.cloudwavewebservice.domain.product.Product;
import com.cloudwave.cloudwavewebservice.domain.product.ProductRepository;
import com.cloudwave.cloudwavewebservice.domain.user.User;
import com.cloudwave.cloudwavewebservice.domain.user.UserRepository;
import com.cloudwave.cloudwavewebservice.dto.cart.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final AuthService authService;

    public void addToCart(String accessToken, Long productId) {
        String email = authService.getPrincipal(accessToken);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Cart cartItem = new Cart();
        cartItem.setUser(user);
        cartItem.setProduct(product);

        cartRepository.save(cartItem);
    }

    public List<CartItemDto> getUserCart(String accessToken) {
        String email = authService.getPrincipal(accessToken);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Cart> userCartItems = cartRepository.findByUserId(user.getId());

        List<CartItemDto> userCart = userCartItems.stream().map(cart -> {
            CartItemDto cartItemDTO = new CartItemDto(cart.getProduct().getName(), cart.getProduct().getPrice(), cart.getProduct().getImageUrl());
            return cartItemDTO;
        }).collect(Collectors.toList());

        return userCart;
    }
}

package com.cloudwave.cloudwavewebservice.dto.cart;

import com.cloudwave.cloudwavewebservice.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemDto {
    private String name;
    private String price;
    private String img;

    public CartItemDto(String name, String price, String img){
        this.name = name;
        this.price = price;
        this.img = img;
    }
}

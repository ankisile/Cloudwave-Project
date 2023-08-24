package com.cloudwave.cloudwavewebservice.dto.product;


import com.cloudwave.cloudwavewebservice.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class ProductResponseDto implements Serializable {

    private String name;
    private String price;
    private String img;

    public ProductResponseDto(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.img = product.getImageUrl();
    }
}

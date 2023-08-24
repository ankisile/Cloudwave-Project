package com.cloudwave.cloudwavewebservice.controller;

import com.cloudwave.cloudwavewebservice.dto.product.ProductResponseDto;
import com.cloudwave.cloudwavewebservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Cacheable(cacheNames = "products", key = "'all'")
    @GetMapping()
    public List<ProductResponseDto> getProducts(){
        return productService.getProducts();
    }

}

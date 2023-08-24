package com.cloudwave.cloudwavewebservice.service;

import com.cloudwave.cloudwavewebservice.dto.product.ProductResponseDto;
import com.cloudwave.cloudwavewebservice.domain.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDto> getProducts(){
        return productRepository.findAll().stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }

}

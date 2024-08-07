package com.commerce.service;

import com.commerce.DTO.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<List<ProductDto>> getProducts();
}

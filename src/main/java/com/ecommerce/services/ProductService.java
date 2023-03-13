package com.ecommerce.services;

import java.util.List;

import com.ecommerce.payloads.ProductDto;

public interface ProductService {
    
    public ProductDto createProduct(ProductDto product);

    public List<ProductDto> getAllProduct();

    public ProductDto getProductById(Integer productId);

    public ProductDto updateProduct(ProductDto product,Integer productId);

    public void deleteProduct(Integer productId);
}

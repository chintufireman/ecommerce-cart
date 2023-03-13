package com.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.CartDetails;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.payloads.CartDetailsDto;
import com.ecommerce.payloads.ProductDto;
import com.ecommerce.payloads.UserDto;
import com.ecommerce.repositories.CartRepo;
import com.ecommerce.repositories.ProductRepo;
import com.ecommerce.repositories.UserRepo;
import com.ecommerce.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private CartRepo cartRepo;
    

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        Product newProduct = this.productRepo.save(product);
        ProductDto newProductDto = this.modelMapper.map(newProduct,ProductDto.class);
        return newProductDto;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> listOfAllProduct = this.productRepo.findAll();
        List<ProductDto> allProductDto = listOfAllProduct.stream().map(x -> this.modelMapper.map(x, ProductDto.class))
                .collect(Collectors.toList());
        return allProductDto;
    }

    @Override
    public ProductDto getProductById(Integer productId) {
        Product product = this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","Id",productId));
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto,Integer productId) {
        Product product = this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","Id",productId));

        product.setProductDetails(productDto.getProductDetails());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());

        if(productDto.getCartsHavingThisProduct()!=null){
            List<CartDetails> allCarts = productDto.getCartsHavingThisProduct()
                    .stream()
                    .map(x->this.modelMapper.map(x,CartDetails.class))
                    .collect(Collectors.toList());
            product.setCarts(allCarts);
        }

       Product updatedProduct = this.productRepo.save(product);
        ProductDto updatedProductDto = this.modelMapper.map(updatedProduct,ProductDto.class);

        if(updatedProduct.getCarts().size()!=0){
            List<CartDetailsDto> cartDetailsDtos= updatedProduct
                .getCarts()
                .stream()
                .map(x->this.modelMapper.map(x,CartDetailsDto.class))
                .collect(Collectors.toList());
        updatedProductDto.setCartsHavingThisProduct(cartDetailsDtos);
    }
     else
         updatedProductDto.setCartsHavingThisProduct(null);
      return  updatedProductDto;
    }

    @Override
    public void deleteProduct(Integer productId) {
        Product product=this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","Id",productId));
        this.productRepo.delete(product);
    }

}

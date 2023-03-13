package com.ecommerce.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.payloads.ProductDto;
import com.ecommerce.services.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createUser(@RequestBody ProductDto product){
        ProductDto createdProduct = this.productService.createProduct(product);
        return new ResponseEntity<ProductDto>(createdProduct,HttpStatus.CREATED);
    }

    @GetMapping("/{productid}")
    public ResponseEntity<ProductDto> getCartById(@PathVariable Integer productid){
        ProductDto product = this.productService.getProductById(productid);
        return new ResponseEntity<ProductDto>(product,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllCarts(){
        List<ProductDto> products = this.productService.getAllProduct();
        return  ResponseEntity.ok(products);
    }

    @PutMapping("/{productid}")
    public ResponseEntity<ProductDto> updateCart(@PathVariable Integer productid 
    ,@RequestBody ProductDto product){
        ProductDto updatedProduct = this.productService.updateProduct(product,productid);
        return new ResponseEntity<ProductDto>(updatedProduct,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{productid}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer productid){
        this.productService.deleteProduct(productid);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}

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

import com.ecommerce.payloads.CartDetailsDto;
import com.ecommerce.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;

    @PostMapping("/create/{userid}")
    public ResponseEntity<CartDetailsDto> createUser(@PathVariable Integer userid){
        CartDetailsDto createdCart = this.cartService.createCart( userid);
        return new ResponseEntity<CartDetailsDto>(createdCart,HttpStatus.CREATED);
    }

    @GetMapping("/{cartid}")
    public ResponseEntity<CartDetailsDto> getCartById(@PathVariable Integer cartid){
        CartDetailsDto cart = this.cartService.getCartById(cartid);
        return new ResponseEntity<CartDetailsDto>(cart,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartDetailsDto>> getAllCarts(){
        List<CartDetailsDto> carts = this.cartService.getAllCarts();
        return  ResponseEntity.ok(carts);
    }

    @PutMapping("/{cartid}")
    public ResponseEntity<CartDetailsDto> updateCart(@RequestBody CartDetailsDto cart,@PathVariable Integer cartid){
        CartDetailsDto updatedCart = this.cartService.updateCart(cart,cartid);
        return new ResponseEntity<CartDetailsDto>(updatedCart,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{cartid}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer cartid){
        this.cartService.deleteCart(cartid);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/add-product/{cartId}/{productId}")
    public  ResponseEntity<CartDetailsDto> addProductToCart(@PathVariable Integer cartId,@PathVariable Integer productId){
        CartDetailsDto cartDetailsDto = this.cartService.addProductToCart(cartId,productId);
        return  new ResponseEntity<CartDetailsDto>(cartDetailsDto,HttpStatus.ACCEPTED);
    }
}

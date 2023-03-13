package com.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.repositories.ProductRepo;
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
import com.ecommerce.repositories.UserRepo;
import com.ecommerce.services.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public CartDetailsDto createCart(Integer userid) {
        User user = this.userRepo.findById(userid).get();

        CartDetails cart = new CartDetails();
        cart.setUser(user);
        cart.setTotalItems(0);
        CartDetails createdCart=this.cartRepo.save(cart);
        createdCart.getUser().setPassword(null);
        return this.modelMapper.map(createdCart,CartDetailsDto.class);
    }

    @Override
    public List<CartDetailsDto> getAllCarts() {
        List<CartDetails> listOfAllCartDetails = this.cartRepo.findAll();

        return null;
    }

    @Override
    public CartDetailsDto getCartById(Integer id) {
        CartDetails cartDetails = this.cartRepo.findById(id).get();
        CartDetailsDto cartDetailsDto =this.modelMapper.map(cartDetails,CartDetailsDto.class);
        List<ProductDto> productDtoList = cartDetails.getProduct().stream()
                .map(x->{
                    x.setCarts(null);
                    return this.modelMapper.map(x,ProductDto.class);
                }).collect(Collectors.toList());

        cartDetailsDto.setProducts(productDtoList);
        return cartDetailsDto;
    }

    @Override
    public CartDetailsDto updateCart(CartDetailsDto cart, Integer cartId) {

        CartDetails cartDetails = this.cartRepo.findById(cartId).get();


        List<Product> products= cart.getProducts().stream().map(x->
                this.modelMapper.map(x,Product.class)).collect(Collectors.toList());
        cartDetails.setProduct(products);
        CartDetails updatedCartDetails = this.cartRepo.save(cartDetails);
        List<ProductDto> productDtos=updatedCartDetails.getProduct().stream().map(x->
                this.modelMapper.map(x,ProductDto.class)).collect(Collectors.toList());

        CartDetailsDto updatedCartDetailsDto = this.modelMapper.map(updatedCartDetails, CartDetailsDto.class);
        updatedCartDetailsDto.setProducts(productDtos);

        return updatedCartDetailsDto;
    }

    @Override
    public void deleteCart(Integer cartId) {
        this.cartRepo.deleteById(cartId);
    }

    @Override
    public CartDetailsDto addProductToCart(Integer cartId,Integer productId) {
    CartDetails cart = this.cartRepo.findById(cartId).get();
    Product product = this.productRepo.findById(productId).get();
    List<Product> products = cart.getProduct();
    products.add(product);
    cart.setTotalItems(cart.getTotalItems()+1);
    cart.setProduct(products);
    CartDetails updatedCart = this.cartRepo.save(cart);

    CartDetailsDto updateCartDetailsDto = this.modelMapper.map(updatedCart,CartDetailsDto.class);
    updateCartDetailsDto
            .setProducts(updatedCart
                    .getProduct()
                    .stream()
                    .map(x->this.modelMapper.map(x,ProductDto.class))
                    .collect(Collectors.toList()));

    return updateCartDetailsDto;
    }

}

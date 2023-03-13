package com.ecommerce.services;

import com.ecommerce.controllers.CartController;
import com.ecommerce.payloads.CartDetailsDto;
import java.util.List;

public interface CartService {
    public CartDetailsDto createCart(Integer userid);

    public List<CartDetailsDto> getAllCarts();

    public CartDetailsDto getCartById(Integer id);

    public CartDetailsDto updateCart(CartDetailsDto cart,Integer cartId);

    public void deleteCart(Integer cartId);

    public CartDetailsDto addProductToCart(Integer cartId,Integer productId);

    public CartDetailsDto deleteProductFromCart(Integer cartId, Integer productId);
}

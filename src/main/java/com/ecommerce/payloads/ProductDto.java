package com.ecommerce.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private String productName;
    private String price;
    private String productDetails;
    private List<CartDetailsDto> cartsHavingThisProduct;
}

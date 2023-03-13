package com.ecommerce.payloads;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDetailsDto {
    private String totalItems;
    private List<ProductDto> products;
    private UserDto user;
}

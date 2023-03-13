package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.CartDetails;

public interface CartRepo extends JpaRepository<CartDetails,Integer>{
    
}

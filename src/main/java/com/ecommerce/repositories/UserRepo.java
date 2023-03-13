package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.entities.User;
public interface UserRepo extends JpaRepository<User,Integer>{
    
}

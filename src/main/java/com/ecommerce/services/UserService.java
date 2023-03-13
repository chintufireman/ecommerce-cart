package com.ecommerce.services;
import java.util.List;


import com.ecommerce.entities.User;
import com.ecommerce.payloads.UserDto;
public interface UserService {
     UserDto createUser(UserDto user);
     List<UserDto> getAllUsers();
     UserDto getUserById(Integer id);
      void deleteUser(Integer userId);
      UserDto updateUser(UserDto user,Integer userId);

}

package com.ecommerce.services;
import java.util.List;


import com.ecommerce.payloads.UserDto;
public interface UserService {
     UserDto createUser(UserDto user);
     List<UserDto> getAllUsers();
     UserDto getUserById(Integer id);
    //  User deletUser(UserDto user);
    //  User updatUser(UserDto user);

}

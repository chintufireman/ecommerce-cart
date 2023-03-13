package com.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.User;
import com.ecommerce.payloads.UserDto;
import com.ecommerce.repositories.UserRepo;
import com.ecommerce.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user) {
        User user2 = this.modelMapper.map(user, User.class);
        User savedUser = this.userRepo.save(user2);
        UserDto savedUserDto = this.modelMapper.map(savedUser, UserDto.class);
        savedUserDto.setPassword(null);
        return savedUserDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> listOfAllUsers = this.userRepo.findAll();
        List<UserDto> allUsersDto = listOfAllUsers.stream().map(x -> this.modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList());
        return allUsersDto;
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        this.userRepo.deleteById(userId);
    }

    @Override
    public UserDto updateUser(UserDto userDto,Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setLocation(userDto.getLocation());

        User updatedUser = this.userRepo.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

}

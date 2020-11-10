package com.webeservice.example.restfulServices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webeservice.example.restfulServices.dtos.UserDtoV1;
import com.webeservice.example.restfulServices.dtos.UserDtoV2;
import com.webeservice.example.restfulServices.entites.User;
import com.webeservice.example.restfulServices.exception.UserNotFoundException;
import com.webeservice.example.restfulServices.services.UserService;

@RestController
@RequestMapping(value = "/versioning/custheader/users")
public class UserCustomHeaderVersioningController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value="/{id}", headers="API-VERSION=1")
	public UserDtoV1 getUserByIdV1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOpt = userService.getUserById(id);

		if (!userOpt.isPresent())
			throw new UserNotFoundException("Not Found");

		User user = userOpt.get();

		UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);

		return userDtoV1;
	}
	
	@GetMapping(value="/{id}", headers="API-VERSION=2")
	public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOpt = userService.getUserById(id);

		if (!userOpt.isPresent())
			throw new UserNotFoundException("Not Found");

		User user = userOpt.get();

		UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);

		return userDtoV2;
	}
}

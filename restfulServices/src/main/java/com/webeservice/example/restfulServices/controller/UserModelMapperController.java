package com.webeservice.example.restfulServices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webeservice.example.restfulServices.dtos.UserMmDto;
import com.webeservice.example.restfulServices.entites.User;
import com.webeservice.example.restfulServices.exception.UserNotFoundException;
import com.webeservice.example.restfulServices.services.UserService;

@RestController
@RequestMapping(value = "/modelmapper/users")
public class UserModelMapperController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOpt = userService.getUserById(id);

		if (!userOpt.isPresent())
			throw new UserNotFoundException("Not Found");

		User user = userOpt.get();

		UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
		
		return userMmDto;
	}

}

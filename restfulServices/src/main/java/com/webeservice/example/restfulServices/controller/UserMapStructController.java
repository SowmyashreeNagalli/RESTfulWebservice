package com.webeservice.example.restfulServices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webeservice.example.restfulServices.dtos.UserMsDto;
import com.webeservice.example.restfulServices.entites.User;
import com.webeservice.example.restfulServices.exception.UserNotFoundException;
import com.webeservice.example.restfulServices.mappers.UserMapper;
import com.webeservice.example.restfulServices.repository.UserReposistory;

@RestController
@RequestMapping(value = "/mapstruct/users")
public class UserMapStructController {

	@Autowired
	private UserReposistory userRep;

	@Autowired
	private UserMapper userMapper;

	@GetMapping
	public List<UserMsDto> getAllUserDtos() {

		return userMapper.usersToUserMsDto(userRep.findAll());
	}

	@GetMapping("/{id}")
	public UserMsDto getuserById(@PathVariable Long id) throws UserNotFoundException {

		Optional<User> userOpt = userRep.findById(id);

		if (!userOpt.isPresent())
			throw new UserNotFoundException("Not Found");

		User user = userOpt.get();

		return userMapper.userToUserMsDto(user);

	}
}

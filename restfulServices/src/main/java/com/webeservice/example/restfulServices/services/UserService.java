package com.webeservice.example.restfulServices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.webeservice.example.restfulServices.entites.User;
import com.webeservice.example.restfulServices.exception.UserExistException;
import com.webeservice.example.restfulServices.exception.UserNotFoundException;
import com.webeservice.example.restfulServices.repository.UserReposistory;

@Service
public class UserService {

	@Autowired
	private UserReposistory userReposistory;

	public List<User> getAllUsers() {

		return userReposistory.findAll();
	}

	public User createUser(User user) throws UserExistException {

		User existUser = userReposistory.findByUsername(user.getUsername());
		
		if (existUser != null)
			throw new UserExistException("User already exists");
		
		return userReposistory.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {

		Optional<User> user = userReposistory.findById(id);

		if (!user.isPresent())

			throw new UserNotFoundException("User Not found For the Specified Id");

		return user;
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {

		Optional<User> useropt = userReposistory.findById(id);

		if (!useropt.isPresent())

			throw new UserNotFoundException("User Not found For the Specified Id, enter the valid Id");

		user.setUserId(id);

		return userReposistory.save(user);
	}

	public void deleteUserById(Long id) {

		Optional<User> useropt = userReposistory.findById(id);

		if (!useropt.isPresent())

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not exist For the Specified Id");

		userReposistory.deleteById(id);
	}

	public User getUserByUsername(String username) {

		return userReposistory.findByUsername(username);
	}

}

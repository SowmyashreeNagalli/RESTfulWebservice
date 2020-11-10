package com.webeservice.example.restfulServices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.webeservice.example.restfulServices.entites.Order;
import com.webeservice.example.restfulServices.entites.User;
import com.webeservice.example.restfulServices.exception.UserNotFoundException;
import com.webeservice.example.restfulServices.repository.UserReposistory;
import com.webeservice.example.restfulServices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserReposistory userRepo;

	@Autowired
	private UserService userService;

	@GetMapping
	public Resources<User> getAllusers() throws UserNotFoundException {

		List<User> allusers = userService.getAllUsers();

		for (User u : allusers) {

			Long userid = u.getUserId();
			Link linkSelf = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			u.add(linkSelf);

			Resources<Order> orders = ControllerLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);

			Link orderLink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
			u.add(orderLink);
		}

		Link linkLast = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		Resources<User> finalResources = new Resources<User>(allusers,linkLast);
		return finalResources;
	}

	@GetMapping("/{id}")
	public Resource<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOpt = userService.getUserById(id);
			User user = userOpt.get();
			Long userId = user.getUserId();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			Resource<User> finalResouce = new Resource<User>(user);
			return finalResouce;

		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}

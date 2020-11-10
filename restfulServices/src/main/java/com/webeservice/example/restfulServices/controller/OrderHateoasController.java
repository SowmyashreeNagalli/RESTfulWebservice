package com.webeservice.example.restfulServices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webeservice.example.restfulServices.entites.Order;
import com.webeservice.example.restfulServices.entites.User;
import com.webeservice.example.restfulServices.exception.UserNotFoundException;
import com.webeservice.example.restfulServices.repository.OrderRepository;
import com.webeservice.example.restfulServices.repository.UserReposistory;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserReposistory userRepos;

	@Autowired
	private OrderRepository orderRepos;

	@GetMapping("/{userid}/orders")
	public Resources<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOpt = userRepos.findById(userid);

		if (!userOpt.isPresent())
			throw new UserNotFoundException("User not found in repo");

		List<Order> allOrders = userOpt.get().getOrder();
		Resources<Order> finalResources = new Resources<Order>(allOrders);

		return finalResources;
	}
}

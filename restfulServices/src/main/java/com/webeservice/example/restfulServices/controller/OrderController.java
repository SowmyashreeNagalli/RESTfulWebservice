package com.webeservice.example.restfulServices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webeservice.example.restfulServices.entites.Order;
import com.webeservice.example.restfulServices.entites.User;
import com.webeservice.example.restfulServices.exception.OrderNotFoundException;
import com.webeservice.example.restfulServices.exception.UserNotFoundException;
import com.webeservice.example.restfulServices.repository.OrderRepository;
import com.webeservice.example.restfulServices.repository.UserReposistory;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserReposistory userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOpt = userRepository.findById(userid);

		if (!userOpt.isPresent())
			throw new UserNotFoundException("User not found in repo");

		return userOpt.get().getOrder();
	}

	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {

		Optional<User> userOpt = userRepository.findById(userid);

		if (!userOpt.isPresent())
			throw new UserNotFoundException("User not found in repo");

		User user = userOpt.get();
		order.setUser(user);

		return orderRepository.save(order);

	}

	@GetMapping("/orders/{orderid}")
	public Optional<Order> getOrderByOrderId(@PathVariable Long orderid) throws OrderNotFoundException {

		Optional<Order> orderOpt = orderRepository.findById(orderid);

		if (!orderOpt.isPresent())
			throw new OrderNotFoundException("Order not found in repo");

		return orderRepository.findById(orderid);

	}

}

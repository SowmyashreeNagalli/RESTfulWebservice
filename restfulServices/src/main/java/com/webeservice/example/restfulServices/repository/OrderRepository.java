package com.webeservice.example.restfulServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webeservice.example.restfulServices.entites.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

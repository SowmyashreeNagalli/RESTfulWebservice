package com.webeservice.example.restfulServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webeservice.example.restfulServices.entites.User;

@Repository
public interface UserReposistory extends JpaRepository<User, Long> {

	User findByUsername(String username);
}

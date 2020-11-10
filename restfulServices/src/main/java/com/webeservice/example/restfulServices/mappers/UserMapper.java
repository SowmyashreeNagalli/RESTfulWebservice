package com.webeservice.example.restfulServices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.webeservice.example.restfulServices.dtos.UserMsDto;
import com.webeservice.example.restfulServices.entites.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings({ @Mapping(source = "email", target = "emailaddress"), @Mapping(source = "role", target = "rolename") })
	UserMsDto userToUserMsDto(User userEntity);

	List<UserMsDto> usersToUserMsDto(List<User> users);

}

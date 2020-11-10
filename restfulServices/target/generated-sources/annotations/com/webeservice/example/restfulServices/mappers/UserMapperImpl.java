package com.webeservice.example.restfulServices.mappers;

import com.webeservice.example.restfulServices.dtos.UserMsDto;
import com.webeservice.example.restfulServices.entites.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-07T15:06:39+0530",
    comments = "version: 1.3.0.Final, compiler: Eclipse JDT (IDE) 3.18.0.v20190522-0428, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserMsDto userToUserMsDto(User userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserMsDto userMsDto = new UserMsDto();

        userMsDto.setRolename( userEntity.getRole() );
        userMsDto.setEmailaddress( userEntity.getEmail() );
        userMsDto.setUserId( userEntity.getUserId() );
        userMsDto.setUsername( userEntity.getUsername() );

        return userMsDto;
    }

    @Override
    public List<UserMsDto> usersToUserMsDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserMsDto> list = new ArrayList<UserMsDto>( users.size() );
        for ( User user : users ) {
            list.add( userToUserMsDto( user ) );
        }

        return list;
    }
}

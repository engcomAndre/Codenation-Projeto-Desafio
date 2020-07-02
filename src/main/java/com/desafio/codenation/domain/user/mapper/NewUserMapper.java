package com.desafio.codenation.domain.user.mapper;

import com.desafio.codenation.domain.user.DTO.NewUserDTO;
import com.desafio.codenation.domain.user.DTO.UserDTO;
import com.desafio.codenation.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NewUserMapper {

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "grants", target = "grants")
    })
    User map(NewUserDTO newUserDTO);


    @Mappings({
            @Mapping(source = "origins", target = "origins",ignore = true)
    })
    UserDTO map(User user);

}

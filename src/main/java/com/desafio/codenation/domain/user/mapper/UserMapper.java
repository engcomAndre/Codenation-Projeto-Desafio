package com.desafio.codenation.domain.user.mapper;

import com.desafio.codenation.domain.user.DTO.NewUserDTO;
import com.desafio.codenation.domain.user.DTO.UserDTO;
import com.desafio.codenation.domain.user.DTO.UserListDTO;
import com.desafio.codenation.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "grants", target = "grants"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "modifiedAt", target = "modifiedAt", dateFormat = "yyyy-MM-dd HH:mm")
    })
    UserDTO map(User user);

    UserListDTO mapForFindAll(User users);

    User map(NewUserDTO newUserDTO);
}

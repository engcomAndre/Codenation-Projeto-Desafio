package com.desafio.codenation.domain.user.mapper;

import com.desafio.codenation.domain.user.DTO.UserDTO;
import com.desafio.codenation.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "perfis", target = "perfis"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "modifiedAt", target = "modifiedAt", dateFormat = "yyyy-MM-dd HH:mm")
    })
    UserDTO map(User user);

    List<UserDTO> map(List<User> userList);
}

package com.desafio.codenation.resources.utils;

import com.desafio.codenation.domain.origin.DTO.OriginsListDto;
import com.desafio.codenation.domain.user.DTO.UserDTO;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.mapper.UserMapper;

import java.util.stream.Collectors;

public class MapDetaisForUsers {

    public static UserDTO mapDetails(User user, UserMapper userMapper) {
        UserDTO userDTO = userMapper.map(user);

        userDTO.setOrigins(user.getOrigins().stream()
                .map(originUser ->
                        OriginsListDto.builder()
                                .id(originUser.getId().getOrigins().getId())
                                .name(originUser.getId().getOrigins().getName())
                                .description(originUser.getId().getOrigins().getDescription())
                                .build())
                .collect(Collectors.toList()));

        return userDTO;
    }
}

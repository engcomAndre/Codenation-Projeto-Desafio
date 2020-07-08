package com.desafio.codenation.resources.utils;

import com.desafio.codenation.domain.origin.DTO.OriginsDTO;
import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.domain.origin.Systems;
import com.desafio.codenation.domain.origin.mapper.ServicesMapper;
import com.desafio.codenation.domain.origin.mapper.SystemsMapper;
import com.desafio.codenation.domain.user.DTO.UserListDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MapDetailsForOrigins {

    public static OriginsDTO mapAllDetails(Origins origin, ServicesMapper servicesMapper) {
        OriginsDTO originsDTO = servicesMapper.mapToDetails((Services) origin);
        originsDTO.setUsers(mapUserListDTO(origin));
        return originsDTO;
    }


    public static OriginsDTO mapAllDetails(Origins origin, SystemsMapper systemsMapper) {
        OriginsDTO originsDTO = systemsMapper.mapToDetails((Systems) origin);
        originsDTO.setUsers(mapUserListDTO(origin));
        return originsDTO;
    }


    private static List<UserListDTO> mapUserListDTO(Origins origin) {
        return origin.getUsers().stream()
                .map(originUser ->
                        UserListDTO.builder()
                                .id(originUser.getId().getUser().getId())
                                .email(originUser.getId().getUser().getEmail())
                                .build()
                )
                .collect(Collectors.toList());
    }
}

package com.desafio.codenation.domain.origin.DTO;

import com.desafio.codenation.domain.events.DTO.EventsListDto;
import com.desafio.codenation.domain.user.DTO.UserListDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OriginsDTO implements Serializable {
    private static final long serialVersionUUID = 1L;
    private Long id;
    private String name;
    private String originKey;
    private String description;
    private LocalDateTime createdAt;
    private List<EventsListDto> events;
    private List<UserListDTO> users;
}


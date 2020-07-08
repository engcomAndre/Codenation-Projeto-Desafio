package com.desafio.codenation.domain.user.DTO;

import com.desafio.codenation.domain.origin.DTO.OriginsListDto;
import com.desafio.codenation.domain.user.enums.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private Set<TypeUser> grants;
    private List<OriginsListDto> origins;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}




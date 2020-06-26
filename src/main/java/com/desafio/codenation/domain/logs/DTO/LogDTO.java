package com.desafio.codenation.domain.logs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO implements Serializable {
    private static final long serialVersionUUID = 1L;

    private Long id;

    private String description;

    private Long eventsId;

    private LocalDateTime createdAt;

}

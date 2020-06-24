package com.desafio.codenation.domain.origin.DTO;

import com.desafio.codenation.domain.events.DTO.EventsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OriginsDTO implements Serializable {
    private static final long serialVersionUUID = 1L;
    private Long id;
    private String nome;
    private List<EventsDTO> eventos;
    private String chave;
    private String descricao;
    private LocalDate createdAt;
}


package com.desafio.codenation.domain.origem.DTO;

import com.desafio.codenation.domain.eventos.DTO.EventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrigemDTO implements Serializable {
    private static final long serialVersionUUID = 1L;
    private Long id;
    private String nome;
    private List<EventoDTO> eventos;
    private String chave;
    private LocalDate createdAt;
    private String descricao;
}


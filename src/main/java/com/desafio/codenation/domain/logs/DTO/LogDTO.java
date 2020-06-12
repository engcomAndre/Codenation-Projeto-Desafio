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

    private String descricao;

    private Long eventoId;

    private LocalDateTime createdAt;


//    public LogDTO() {
//    }
//
//    public LogDTO(Log log) {
//        this.id = log.getId();
//        this.descricao = log.getDescricao();
//        this.evento = new EventoDTO(log.getEvent());
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getDescricao() {
//        return descricao;
//    }
//
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }
//
//    public EventoDTO getEvento() {
//        return evento;
//    }
//
//    public void setEvento(EventoDTO evento) {
//        this.evento = evento;
//    }
}

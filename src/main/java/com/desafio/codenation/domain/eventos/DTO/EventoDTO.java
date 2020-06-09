package com.desafio.codenation.domain.eventos.DTO;

import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    private Long id;
    private String descricao;
    private TypeLevel level;
    private Long origemId;
    private LocalDateTime createdAt;
    private Integer quantidade;

//    public EventoDTO() {
//    }
//
//    public EventoDTO(Evento evento) {
//        this.id = evento.getId();
//        this.descricao = evento.getDescricao();
//        this.level = evento.getLevel();
//        this.origem = new OrigemDTO(evento.getOrigem());
//        this.data = evento.getData();
//        this.quantidade = evento.getQuantidade();
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
//    public TypeLevel getLevel() {
//        return level;
//    }
//
//    public void setLevel(TypeLevel level) {
//        this.level = level;
//    }
//
//    public OrigemDTO getOrigem() {
//        return origem;
//    }
//
//    public void setOrigem(OrigemDTO origem) {
//        this.origem = origem;
//    }
//
//    public LocalDate getData() {
//        return data;
//    }
//
//    public void setData(LocalDate data) {
//        this.data = data;
//    }
//
//    public Integer getQuantidade() {
//        return quantidade;
//    }
//
//    public void setQuantidade(Integer quantidade) {
//        this.quantidade = quantidade;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof EventoDTO)) return false;
//        EventoDTO eventoDTO = (EventoDTO) o;
//        return Objects.equals(getId(), eventoDTO.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId());
//    }
}

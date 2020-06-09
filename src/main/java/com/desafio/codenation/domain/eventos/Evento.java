package com.desafio.codenation.domain.eventos;

import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origem.Origem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Evento implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Log> logs = new ArrayList<>();

    private TypeLevel level;

    @ManyToOne
    @JoinColumn(name = "origem_id")
    private Origem origem;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    private Integer quantidade;

//    public Evento() {
//    }
//
//    public Evento(String descricao, List<Log> logs, TypeLevel level, Origem origem, LocalDate data, Integer quantidade) {
//        this.descricao = descricao;
//        this.logs = logs;
//        this.level = level;
//        this.origem = origem;
//        this.createdAt = data;
//        this.quantidade = quantidade;
//    }
//
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
//    public List<Log> getLogs() {
//        return logs;
//    }
//
//    public void setLog(List<Log> logs) {
//        this.logs = logs;
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
//    public Origem getOrigem() {
//        return origem;
//    }
//
//    public void setOrigem(Origem origem) {
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
//        if (!(o instanceof Evento)) return false;
//        Evento eventos = (Evento) o;
//        return id.equals(eventos.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}

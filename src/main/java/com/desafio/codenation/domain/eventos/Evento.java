package com.desafio.codenation.domain.eventos;

import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origem.Origem;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
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

    private Integer quantidade;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

}

package com.desafio.codenation.domain.events;

import com.desafio.codenation.domain.events.enums.TypeLevel;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origin.Origins;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Events implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Descrição é um campo obrigatório.")
    @Length(min = 10, max = 250, message = "Descrição possui tamanho mínimo de 10 e máximo de 250 caracteres.")
    private String description;

    @OneToOne(mappedBy = "events", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Log log;

    @NotNull(message = "Level do evento é um campo obrigatório.")
    private TypeLevel level;

    @ManyToOne
    @JoinColumn(name = "origins_id")
    private Origins origins;

    @NotNull(message = "Quantidade do evento é um campo obrigatório.")
    private Integer quantity;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedData;

}

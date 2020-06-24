package com.desafio.codenation.domain.origem;

import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.security.SecurityEntity;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public class Origem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é um campo obrigatório.")
    @Length(min = 5, max = 120, message = "Nome possui tamanho mínimo de 5 e máximo de 120 caracteres.")
    private String nome;

    @NotNull(message = "Descrição é um campo obrigatório.")
    @Length(min = 10, max = 250, message = "Descrição possui tamanho mínimo de 10 e máximo de 250 caracteres.")
    private String descricao;

    @NotNull(message = "Chave é um campo obrigatório.")
    @Length(min = 5, max = 60, message = "Descrição possui tamanho mínimo de 10 e máximo de 250 caracteres.")
    private String chave;

    @NotEmpty(message = "Uma senha valida deve ser informada.")
    @Length(min = 5, max = 20, message = "Senha possui tamanho mínimo de 5 e máximo de 20 caracteres.")
    private String password;

    private Boolean ativo;

    @NotEmpty(message = "Pelo menos umm perfil de usuário deve ser informado.")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<TypeUser> perfis = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "origem")
    private List<Evento> eventos;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate createdAt;
}

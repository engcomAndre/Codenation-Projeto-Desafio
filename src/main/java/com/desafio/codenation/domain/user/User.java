package com.desafio.codenation.domain.user;

import com.desafio.codenation.domain.user.enums.TypeUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Campo email com formato incorreto.")
    @NotEmpty(message = "Um email valido deve ser informado.")
    @Length(min = 5, max = 50, message = "Email possui tamanho mínimo de 5 e máximo de 50 caracteres.")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Uma senha valida deve ser informada.")
    @Length(min = 5, max = 20, message = "Senha possui tamanho mínimo de 5 e máximo de 20 caracteres.")
    private String password;


    @NotEmpty(message = "Pelo menos umm perfil de usuário deve ser informado.")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
    private Set<TypeUser> perfis = new HashSet<>();

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedAt;


}

package com.desafio.codenation.domain.user;

import com.desafio.codenation.domain.user.enums.TypeUser;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Builder
@Entity
public class User implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email inválido")
    @NotEmpty(message = "Um email deve ser informado")
    private String email;

    @NotEmpty(message = "Uma senha deve ser informada")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<TypeUser> perfis = new HashSet<>();

}

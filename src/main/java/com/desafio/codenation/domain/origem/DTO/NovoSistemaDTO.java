package com.desafio.codenation.domain.origem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NovoSistemaDTO {
    private String nome;
    private String descricao;
    private String identificador;
    private String chave;
}

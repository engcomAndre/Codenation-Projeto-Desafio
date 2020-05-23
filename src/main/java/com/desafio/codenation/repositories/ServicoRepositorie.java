package com.desafio.codenation.repositories;

import com.desafio.codenation.domain.user.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepositorie extends JpaRepository<Servico, Long> {
}

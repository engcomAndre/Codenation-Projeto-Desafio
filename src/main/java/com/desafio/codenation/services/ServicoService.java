package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.repositories.ServicoRepositorie;
import com.desafio.codenation.services.exception.DataIntegrityException;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"Serviços"}, value = "Recursos de Serviços", hidden = true, produces = APPLICATION_JSON_VALUE,consumes = APPLICATION_JSON_VALUE)
@Service
public class ServicoService {

    @Autowired
    private ServicoRepositorie servicoRepositorie;

    public Servico getServicoById(Long id) {
        return servicoRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Serviço com identificador " + id + " não encontrado."));
    }

    public Page<Servico> getServicos(Predicate predicate, Pageable pageable) {
        Page<Servico> servicoPage = servicoRepositorie.findAll(predicate, pageable);
        if (servicoPage.isEmpty()) throw new ObjectNotFoundException("Servico(s) não encontrado(s) para os parâmetros informados.");
        return servicoRepositorie.findAll(predicate, pageable);
    }

    public Servico insert(Servico servico) {
        if(servico.getChave() == null || servico.getChave().isEmpty() ) {
            servico.setChave(UUID.randomUUID().toString().replace("-", ""));
        }
        return servicoRepositorie.save(servico);
    }

    public void deleteUser(Long id) {
        try {
            getServicoById(id);
            servicoRepositorie.deleteById(id);
        }
        catch (DataIntegrityException die){
            throw new DataIntegrityException("Não é possível excluir um Serviço que possui registros de evento atrelados.");
        }
    }

    public void updateServico(Long id, Servico newServico) {
        Servico service = getServicoById(id);

        updtServico(service, newServico);

        servicoRepositorie.save(service);

    }

    private void updtServico(Servico servico, Servico newServico) {
        if (!Objects.equals(servico.getNome(), newServico.getNome())) {
            servico.setNome(newServico.getNome());
        }
        if (!Objects.equals(servico.getDescricao(), newServico.getDescricao())) {
            servico.setDescricao(newServico.getDescricao());
        }
        if (!Objects.equals(servico.getChave(), newServico.getChave())) {
            servico.setChave(newServico.getChave());
        }
    }
}

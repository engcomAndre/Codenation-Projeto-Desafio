package com.desafio.codenation.services;

import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.repositories.ServicesRepositorie;
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
public class ServicesService {

    @Autowired
    private ServicesRepositorie servicesRepositorie;

    public Services getServicoById(Long id) {
        return servicesRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Serviço com identificador " + id + " não encontrado."));
    }

    public Page<Services> getServicos(Predicate predicate, Pageable pageable) {
        Page<Services> servicoPage = servicesRepositorie.findAll(predicate, pageable);
        if (servicoPage.isEmpty()) throw new ObjectNotFoundException("Servico(s) não encontrado(s) para os parâmetros informados.");
        return servicesRepositorie.findAll(predicate, pageable);
    }

    public Services insert(Services services) {
        if(services.getChave() == null || services.getChave().isEmpty() ) {
            services.setChave(UUID.randomUUID().toString().replace("-", ""));
        }
        return servicesRepositorie.save(services);
    }

    public void deleteUser(Long id) {
        try {
            getServicoById(id);
            servicesRepositorie.deleteById(id);
        }
        catch (DataIntegrityException die){
            throw new DataIntegrityException("Não é possível excluir um Serviço que possui registros de evento atrelados.");
        }
    }

    public void updateServico(Long id, Services newServices) {
        Services service = getServicoById(id);

        updtServico(service, newServices);

        servicesRepositorie.save(service);

    }

    private void updtServico(Services services, Services newServices) {
        if (!Objects.equals(services.getNome(), newServices.getNome())) {
            services.setNome(newServices.getNome());
        }
        if (!Objects.equals(services.getDescricao(), newServices.getDescricao())) {
            services.setDescricao(newServices.getDescricao());
        }
        if (!Objects.equals(services.getChave(), newServices.getChave())) {
            services.setChave(newServices.getChave());
        }
    }
}

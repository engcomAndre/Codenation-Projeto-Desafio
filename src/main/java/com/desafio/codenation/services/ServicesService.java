package com.desafio.codenation.services;

import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.ServicesRepositorie;
import com.desafio.codenation.services.exception.DataIntegrityException;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.desafio.codenation.services.utils.updtUtils;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"Serviços"}, value = "Recursos de Serviços", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@Service
public class ServicesService {

    private final ServicesRepositorie servicesRepositorie;

    public ServicesService(ServicesRepositorie servicesRepositorie) {
        this.servicesRepositorie = servicesRepositorie;
    }

    public Services getServicoById(Long id) {
        return servicesRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Serviço com identificador " + id + " não encontrado."));
    }

    public Page<Services> getServicos(Predicate predicate, Pageable pageable) {
        Page<Services> servicoPage = servicesRepositorie.findAll(predicate, pageable);
        if (servicoPage.isEmpty())
            throw new ObjectNotFoundException("Servico(s) não encontrado(s) para os parâmetros informados.");
        return servicesRepositorie.findAll(predicate, pageable);
    }

    public Services insert(Services services) {
        services.setGrants(new HashSet<>(Collections.singleton(TypeUser.UNDEFINED)));
        if (services.getOriginKey() == null || services.getOriginKey().isEmpty()) {
            services.setOriginKey(UUID.randomUUID().toString().replace("-", ""));
        }
        return servicesRepositorie.save(services);
    }

    public void deleteService(Long id) {
        try {
            getServicoById(id);
            servicesRepositorie.deleteById(id);
        } catch (DataIntegrityException die) {
            throw new DataIntegrityException("Não é possível excluir um Serviço que possui registros de evento atrelados.");
        }
    }

    public void updateServico(Long id, Services newServices) {
        Services service = getServicoById(id);

        updtUtils.updtOrigins(service, newServices);

        servicesRepositorie.save(service);

    }
}

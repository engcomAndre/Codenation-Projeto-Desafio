package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origin.DTO.NewServiceDTO;
import com.desafio.codenation.domain.origin.DTO.ServicesDTO;
import com.desafio.codenation.domain.origin.DTO.ServicesListDto;
import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.domain.origin.mapper.ServicesMapper;
import com.desafio.codenation.resources.interfaces.ServicesResourcesContract;
import com.desafio.codenation.services.ServicesService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("servico")
public class ServicesResources implements ServicesResourcesContract {

    private final ServicesService servicesService;

    private final ServicesMapper servicesMapper;

    public ServicesResources(ServicesService servicesService, ServicesMapper servicesMapper) {
        this.servicesService = servicesService;
        this.servicesMapper = servicesMapper;
    }

    public ResponseEntity<ServicesDTO> getServicoById(Long id) {
        return ResponseEntity.ok().body(servicesMapper.map(servicesService.getServicoById(id)));
    }


    public ResponseEntity<Page<ServicesListDto>> getServicos(Predicate predicate, Long id, String nome, String descricao, String chave, String password, LocalDateTime createdAt, String sort, String page, Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        servicesService
                                .getServicos(predicate, pageable).stream()
                                .map(servicesMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }

    public ResponseEntity<Void> insertService(NewServiceDTO newServiceDTO) {
        Services services = servicesService.insert(servicesMapper.map(newServiceDTO));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(services.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<Void> updateServico(Long id, NewServiceDTO newServiceDTO) {

        servicesService.updateServico(id, servicesMapper.map(newServiceDTO));

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        servicesService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

}

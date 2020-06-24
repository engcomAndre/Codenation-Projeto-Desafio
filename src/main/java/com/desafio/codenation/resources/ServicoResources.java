package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origin.DTO.NewServiceDTO;
import com.desafio.codenation.domain.origin.DTO.ServicesDTO;
import com.desafio.codenation.domain.origin.DTO.ServicesListDto;
import com.desafio.codenation.domain.origin.Services;
import com.desafio.codenation.domain.origin.mapper.ServicesMapper;
import com.desafio.codenation.services.ServicoService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Api(tags = {"Serviços"}, value = "Recursos de Serviços", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("servico")
public class ServicoResources {

    private final ServicoService servicoService;

    private final ServicesMapper servicesMapper;

    public ServicoResources(ServicoService servicoService, ServicesMapper servicesMapper) {
        this.servicoService = servicoService;
        this.servicesMapper = servicesMapper;
    }

    @ApiOperation(value = "Buscar Serviço",notes = "Busca de Serviços por Id.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServicesDTO> getServicoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(servicesMapper.map(servicoService.getServicoById(id)));
    }

    @ApiOperation(value = "Buscar Serviços",notes = "Busca de Serviços por parâmetros com paginação e ordenação.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ServicesListDto>> getServicos(
            @QuerydslPredicate(root = Services.class) Predicate predicate,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        servicoService
                                .getServicos(predicate, pageable).stream()
                                .map(servicesMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }

    @ApiOperation(value = "Cadastrar Serviços",notes = "Cadastro de um novo Serviço.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> insertService(@Valid @RequestBody NewServiceDTO newServiceDTO) {
        Services services = servicoService.insert(servicesMapper.map(newServiceDTO));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(services.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Atualizar Servico",notes = "Atualizar um  Serviço existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateServico(@PathVariable("id") Long id, @Valid @RequestBody NewServiceDTO newServiceDTO) {

        servicoService.updateServico(id, servicesMapper.map(newServiceDTO));

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Buscar Tipos de Servico",notes = "Remover um  Serviço existente por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Impossível remover serviços com associações."),
            @ApiResponse(code = 404, message = "Serviço não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        servicoService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

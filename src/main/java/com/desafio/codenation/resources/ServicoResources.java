package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origem.DTO.NovoServicoDTO;
import com.desafio.codenation.domain.origem.DTO.ServicoDTO;
import com.desafio.codenation.domain.origem.DTO.ServicoListDto;
import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.domain.origem.mapper.ServicoMapper;
import com.desafio.codenation.services.ServicoService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Api(tags = {"Serviços"},value = "Recursos de Serviços",hidden = true,produces = APPLICATION_JSON_VALUE,consumes = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("servico")
public class ServicoResources {

    private final ServicoService servicoService;

    private final ServicoMapper servicoMapper;

    public ServicoResources(ServicoService servicoService, ServicoMapper servicoMapper) {
        this.servicoService = servicoService;
        this.servicoMapper = servicoMapper;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(servicoMapper.map(servicoService.getServicoById(id)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ServicoListDto>> getServicos(
            @QuerydslPredicate(root = Servico.class) Predicate predicate,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        servicoService
                                .getServicos(predicate, pageable).stream()
                                .map(servicoMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertService(@Valid @RequestBody NovoServicoDTO novoServicoDTO) {
        Servico servico = servicoService.insert(servicoMapper.map(novoServicoDTO));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(servico.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id,@Valid @RequestBody NovoServicoDTO novoServicoDTO) {

        servicoService.updateServico(id, servicoMapper.map(novoServicoDTO));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        servicoService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

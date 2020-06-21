package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origem.DTO.NovoSistemaDTO;
import com.desafio.codenation.domain.origem.DTO.SistemaDTO;
import com.desafio.codenation.domain.origem.DTO.SistemaListDto;
import com.desafio.codenation.domain.origem.Sistema;
import com.desafio.codenation.domain.origem.mapper.SistemaMapper;
import com.desafio.codenation.services.SistemaService;
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

@Api(tags = {"Sistema"},value = "Recursos de Sistema",hidden = true,produces = "application/json")
@RestController
@RequestMapping("sistema")
public class SistemaResources {

    private final SistemaService sistemaService;

    private final SistemaMapper sistemaMapper;

    public SistemaResources(SistemaService sistemaService, SistemaMapper sistemaMapper) {
        this.sistemaService = sistemaService;
        this.sistemaMapper = sistemaMapper;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SistemaDTO> getSistemaById(@PathVariable Long id) {
        return ResponseEntity.ok().body(sistemaMapper.map(sistemaService.getSistema(id)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<SistemaListDto>> getSistemas(
            @QuerydslPredicate(root = Sistema.class) Predicate predicate,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        sistemaService
                                .getSistemas(predicate, pageable).stream()
                                .map(sistemaMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertSistema(@Valid @RequestBody NovoSistemaDTO novoSistemaDTO) {
        Sistema sistema = sistemaService.insert(sistemaMapper.map(novoSistemaDTO));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sistema.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSistema(@Valid @PathVariable("id") Long id, @RequestBody NovoSistemaDTO novoSistemaDTO) {
        sistemaService.updateSistema(id, sistemaMapper.map(novoSistemaDTO));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSistema(@PathVariable("id") Long id) {
        sistemaService.deleteSistema(id);
        return ResponseEntity.noContent().build();
    }

}

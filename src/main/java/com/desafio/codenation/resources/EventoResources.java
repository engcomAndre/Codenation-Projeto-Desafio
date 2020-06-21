package com.desafio.codenation.resources;

import com.desafio.codenation.domain.eventos.DTO.EventoDTO;
import com.desafio.codenation.domain.eventos.DTO.EventoListDto;
import com.desafio.codenation.domain.eventos.DTO.NovoEventoDTO;
import com.desafio.codenation.domain.eventos.Evento;
import com.desafio.codenation.domain.eventos.enums.TypeLevel;
import com.desafio.codenation.domain.eventos.mapper.EventoMapper;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origem.Origem;
import com.desafio.codenation.services.EventoService;
import com.desafio.codenation.services.OrigemService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = {"Eventos"})
@RequestMapping("evento")
public class EventoResources {

    private final EventoService eventoService;

    private final OrigemService origemService;

    private final EventoMapper eventoMapper;

    public EventoResources(EventoService eventoService, EventoMapper eventoMapper, OrigemService origemService) {
        this.eventoService = eventoService;
        this.origemService = origemService;
        this.eventoMapper = eventoMapper;
    }

    @ApiOperation(
            value = "Get All",
            produces = APPLICATION_JSON_VALUE
            , authorizations = {@Authorization(value = "Authorization", scopes =
            {@AuthorizationScope(scope = "password", description = "password")})}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventoMapper.map(eventoService.getEvento(id)));
    }

    @ApiOperation(value = "Obter Eventos por parâmetros de URL e Paginação. ")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<EventoListDto>> getEventos(
            @QuerydslPredicate(root = Evento.class) Predicate predicate,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        eventoService
                                .getEventos(predicate, pageable).stream()
                                .map(eventoMapper::mapForFindAll)
                                .collect(Collectors.toList())));
    }

    @ApiOperation(value = "Cadastro um novo Evento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insertEvento(@Valid @RequestBody NovoEventoDTO novoEventoDTO) {

        Evento evento = eventoMapper.map(novoEventoDTO);

        Origem origem = origemService.findByIdAndAndChaveAndAtivo(
                Long.valueOf(novoEventoDTO.getOrigemId()),
                novoEventoDTO.getChave());

        evento.setOrigem(origem);

        evento.setLog(Log.builder()
                .evento(evento)
                .descricao(novoEventoDTO.getLogDescricao())
                .build());

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventoService
                        .insert(evento).getId())
                .toUri()).build();
    }


    @ApiOperation(value = "Atualizar um  Evento existente por Id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvento(@PathVariable("id") Long id, @Valid @RequestBody NovoEventoDTO novoEventoDTO) {
        Evento evento = eventoMapper.map(novoEventoDTO);

        evento.setOrigem(origemService.findById(Long.valueOf(novoEventoDTO.getOrigemId())));

        evento.setLog(Log.builder()
                .evento(evento)
                .descricao(novoEventoDTO.getLogDescricao())
                .build());

        eventoService.updateEvento(id, evento);

        return ResponseEntity.noContent().build();

    }

    @ApiOperation(value = "Remover um  Evento existente por Id")
    @ApiResponses(value = {
//            @ApiResponse(code = 400, message = "Não é possível excluir uma Evento que possui produtos"),
            @ApiResponse(code = 404, message = "Código inexistente")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable("id") Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }


    @ApiOperation(value = "Obter os tipos de Eventos disponíveis.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/event-level")
    public ResponseEntity<List<TypeLevel>> getTypeLevelResponseEntity() {
        return ResponseEntity
                .ok()
                .body(Arrays.asList(TypeLevel.values()));
    }
}

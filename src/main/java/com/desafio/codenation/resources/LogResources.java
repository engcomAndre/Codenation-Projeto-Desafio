//package com.desafio.codenation.resources;
//
//import com.desafio.codenation.domain.eventos.Evento;
//import com.desafio.codenation.domain.logs.DTO.LogDTO;
//import com.desafio.codenation.domain.logs.Log;
//import com.desafio.codenation.domain.logs.mapper.LogMapper;
//import com.desafio.codenation.services.LogService;
//import com.querydsl.core.types.Predicate;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.querydsl.binding.QuerydslPredicate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.validation.Valid;
//import java.util.stream.Collectors;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@Api(tags = {"Log"}, value = "Recursos de Log", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
//@RestController
//@RequestMapping("log")
//public class LogResources {
//
//    private final LogService logService;
//
//    private final LogMapper logMapper;
//
//    public LogResources(LogService logService, LogMapper logMapper) {
//        this.logService = logService;
//        this.logMapper = logMapper;
//    }
//
//    @ApiOperation(value = "Busca de Logs por Id.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
//    @GetMapping("/{id}")
//    public ResponseEntity<LogDTO> getLogById(@PathVariable Long id) {
//        return ResponseEntity.ok().body(logMapper.map(logService.getLog(id)));
//    }
//
//    @ApiOperation(value = "Busca de Logs por parâmetros com paginação e ordenação.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
//    @GetMapping
//    public ResponseEntity<Page<LogDTO>> getLog(
//            @QuerydslPredicate(root = Evento.class) Predicate predicate,
//            Pageable pageable) {
//
//        return ResponseEntity
//                .ok()
//                .body(new PageImpl<>(
//                        logService
//                                .getLogs(pageable).stream()
//                                .map(logMapper::map)
//                                .collect(Collectors.toList())));
//    }
//
//
//    @ApiOperation(value = "Cadastro de um novo log.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
//    @PostMapping
//    public ResponseEntity<Void> insertLog(@Valid @RequestBody Log log) {
//        Log _log = logService.insert(log);
//        return ResponseEntity.created(ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(_log.getId())
//                .toUri()).build();
//    }
//
//}

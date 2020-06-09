package com.desafio.codenation.resources;

import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.logs.dto.LogDTO;
import com.desafio.codenation.domain.logs.mapper.LogMapper;
import com.desafio.codenation.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Collectors;

@RestController
@RequestMapping("log")
public class LogResources {

    private final LogService logService;

    private final LogMapper logMapper;

    @Autowired
    public LogResources(LogService logService, LogMapper logMapper) {
        this.logService = logService;
        this.logMapper = logMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogDTO> getLogById(@PathVariable Long id) {
        return ResponseEntity.ok().body(logMapper.map(logService.getLog(id)));
    }

    @GetMapping
    public ResponseEntity<Page<LogDTO>> getLog(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(new PageImpl<>(
                        logService
                                .getLogs(pageable).stream()
                                .map(logMapper::map)
                                .collect(Collectors.toList())));
    }

    @PostMapping
    public ResponseEntity<Void> insertEvento(@RequestBody Log log) {
        Log _log = logService.insert(log);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(_log.getId())
                .toUri()).build();
    }

}

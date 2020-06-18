package com.desafio.codenation.resources;

import com.desafio.codenation.domain.origem.DTO.SistemaDTO;
import com.desafio.codenation.domain.origem.Sistema;
import com.desafio.codenation.domain.origem.mapper.SistemaMapper;
import com.desafio.codenation.services.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("sistema")
public class SistemaResources {

    @Autowired
    private SistemaService sistemaService;

    @Autowired
    private SistemaMapper sistemaMapper;



    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<SistemaDTO> getSistemaById(@PathVariable Long id) {
        return ResponseEntity.ok().body(sistemaMapper.map(sistemaService.getSistema(id)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SistemaDTO>> getSistemas(){
        return ResponseEntity.ok().body(sistemaMapper.map(sistemaService.getSistemas()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertSistema(@RequestBody Sistema _sistema){

        Sistema sistema = sistemaService.insert(_sistema);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sistema.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}

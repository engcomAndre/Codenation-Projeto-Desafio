package com.desafio.codenation.resources;

import com.desafio.codenation.domain.user.Sistema;
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

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Sistema> getContacts(@PathVariable Integer id) {
        Sistema sistema = sistemaService.getSistema(id);
        return ResponseEntity.ok().body(sistema);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Sistema>> getUsers(){
        List<Sistema>sistemas = sistemaService.getSistemas();
        return ResponseEntity.ok().body(sistemas);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertUser(@RequestBody Sistema _sistema){
        Sistema sistema = sistemaService.insert(_sistema);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sistema.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}

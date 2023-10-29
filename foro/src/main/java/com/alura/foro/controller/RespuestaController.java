package com.alura.foro.controller;

import com.alura.foro.domain.respuesta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    @Autowired
    RespuestaRepository respuestaRepository;

    @Autowired
    NuevaRespuestaService nuevaRespuestaService;

    @PostMapping
    public ResponseEntity nueva(@RequestBody @Valid DatosNuevaRespuesta datosNuevaRespuesta,
                                UriComponentsBuilder uriComponentsBuilder){

        var respuesta = nuevaRespuestaService.nuevo(datosNuevaRespuesta);
        respuestaRepository.save(respuesta);
        var url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(url).body(new RespuestaResponse(respuesta));
    }

    @GetMapping
    public ResponseEntity listarRespuestas(){
        return ResponseEntity.ok(respuestaRepository.findByActivoTrue());
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody DatosActualizarRespuesta datos){
        var respuesta = respuestaRepository.getReferenceById(datos.id());
        respuesta.actualizarDatos(datos);
        return ResponseEntity.ok(new RespuestaResponse(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivar(@PathVariable  Long id){
        Respuesta respuesta =respuestaRepository.getReferenceById(id);
        respuesta.desactivar();
        return ResponseEntity.ok().build();
    }
}

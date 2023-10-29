package com.alura.foro.controller;

import com.alura.foro.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private NuevoTopicoService topicoService;
    @PostMapping
    public ResponseEntity nuevo(@RequestBody @Valid DatosNuevoTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoService.nuevo(datos);
        topicoRepository.save(topico);
        URI url =uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(new TopicoResponse(topico));
    }

    @GetMapping
    public ResponseEntity listar() {
        List<Topico> topicos = topicoRepository.findByActivoTrue();
        List<TopicoResponse> topicosDTO = topicosToDTO(topicos);

        return ResponseEntity.ok(topicosDTO);

    }
    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        TopicoResponse topicoResponse = new TopicoResponse(topico);
        return ResponseEntity.ok(topicoResponse);

    }

    private List<TopicoResponse> topicosToDTO(List<Topico> topicos){
        List<TopicoResponse> topicoResponses = new ArrayList();
        for (Topico topico:
             topicos) {
            TopicoResponse dto = new TopicoResponse(topico);
            topicoResponses.add(dto);
        }
        return topicoResponses;
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datos){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizar(datos);
        return ResponseEntity.ok(new TopicoResponse(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivar();
        return ResponseEntity.noContent().build();
    }

}

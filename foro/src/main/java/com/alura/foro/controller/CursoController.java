package com.alura.foro.controller;

import com.alura.foro.domain.curso.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoRepository cursoRepository;


    @PostMapping
    public ResponseEntity registrarCurso(@RequestBody DatosNuevoCurso datosNuevoCurso,
                                         UriComponentsBuilder uriComponentsBuilder){

        Curso curso = cursoRepository.save(new Curso(datosNuevoCurso));
        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(url).body(new CursoResponse(curso));
    }

    @GetMapping
    public ResponseEntity listarCursos(){
        return ResponseEntity.ok().body(cursoRepository.findByActivoTrue());
    }

    @PutMapping
    @Transactional
    public ResponseEntity modificar(@RequestBody @Valid DatosActualizarCurso datos){
        var curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizarDatos(datos);
        return ResponseEntity.ok(new CursoResponse(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivar(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        curso.desactivar();
        return ResponseEntity.ok().build();
    }
}

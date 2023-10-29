package com.alura.foro.controller;

import com.alura.foro.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosNuevoUsuario datosNuevoUsuario,
                                           UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.save(new Usuario(datosNuevoUsuario));
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(new UsuarioResponse(usuario));
    }

    @GetMapping
    public ResponseEntity listarUsuarios(){
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue());
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datos){
        var usuario = usuarioRepository.getReferenceById(datos.id());
        usuario.actualizarInformacion(datos);
        return ResponseEntity.ok(new UsuarioResponse(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivar(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivar();
        return ResponseEntity.ok().build();
    }
}

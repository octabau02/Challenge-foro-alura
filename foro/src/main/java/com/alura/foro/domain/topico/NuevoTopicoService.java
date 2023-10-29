package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.usuario.UsuarioRepository;
import com.alura.foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NuevoTopicoService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    TopicoRepository topicoRepository;
    public Topico nuevo(DatosNuevoTopico datos) {
        if(!usuarioRepository.findById(datos.autor_id()).isPresent()){
            throw new ValidacionDeIntegridad("No se encontro al autor");
        }
        if(!cursoRepository.findById(datos.curso_id()).isPresent()){
            throw new ValidacionDeIntegridad("No se encontro el curso");
        }
        var usuario = usuarioRepository.findById(datos.autor_id()).get();
        var curso = cursoRepository.findById(datos.curso_id()).get();

        var topico = new Topico(datos.titulo(), datos.mensaje(), usuario, curso);
        return topico;
    }


}

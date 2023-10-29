package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.topico.TopicoRepository;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import com.alura.foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NuevaRespuestaService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TopicoRepository topicoRepository;
    public Respuesta nuevo(DatosNuevaRespuesta datosNuevaRespuesta) {
        if(!usuarioRepository.findById(datosNuevaRespuesta.autor_id()).isPresent()){
            throw new ValidacionDeIntegridad("No se encontro al autor");
        }
        if(!topicoRepository.findById(datosNuevaRespuesta.topico_id()).isPresent()){
            throw new ValidacionDeIntegridad("No se encontro el topico");
        }

        var usuario = usuarioRepository.findById(datosNuevaRespuesta.autor_id()).get();
        var topico = topicoRepository.findById(datosNuevaRespuesta.topico_id()).get();

        Respuesta respuesta = new Respuesta(datosNuevaRespuesta.mensaje(), usuario, topico);

        return respuesta;
    }
}

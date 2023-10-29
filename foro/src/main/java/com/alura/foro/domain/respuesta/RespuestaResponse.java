package com.alura.foro.domain.respuesta;

import java.time.LocalDateTime;

public record RespuestaResponse(Long id, String mensaje, LocalDateTime fechaCreacion, Long autor, Long topico,
                                Boolean solucion) {
    public RespuestaResponse(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getAutor().getId(),
                respuesta.getTopico().getId(), respuesta.getSolucion());

    }
}

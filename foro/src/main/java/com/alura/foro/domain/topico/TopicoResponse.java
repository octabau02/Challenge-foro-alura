package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record TopicoResponse(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion,
                             EstatusTopico estatus, Long usuario_id, Long Curso_id, boolean activo) {


    public TopicoResponse(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getEstatus(), topico.getAutor().getId(), topico.getCurso().getId(), topico.isActivo());
    }
}

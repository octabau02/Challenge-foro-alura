package com.alura.foro.domain.topico;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        EstatusTopico estatus) {
}

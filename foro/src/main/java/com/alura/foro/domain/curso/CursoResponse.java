package com.alura.foro.domain.curso;

public record CursoResponse(Long id, String nombre, String categoria) {
    public CursoResponse(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}

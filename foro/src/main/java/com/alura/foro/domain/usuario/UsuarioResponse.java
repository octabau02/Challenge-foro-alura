package com.alura.foro.domain.usuario;

public record UsuarioResponse(Long id, String nombre, String email, String contraseña) {
    public UsuarioResponse(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
    }
}

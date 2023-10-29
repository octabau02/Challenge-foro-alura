package com.alura.foro.domain.usuario;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Usuarios")
@Entity(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;

    public Usuario(DatosNuevoUsuario datosNuevoUsuario){
        this.nombre = datosNuevoUsuario.nombre();
        this.email = datosNuevoUsuario.email();
        this.contrasena = datosNuevoUsuario.contrasena();
    }

    public void actualizarInformacion(DatosActualizarUsuario datos) {
        if(datos.nombre() != null){
            this.nombre = datos.nombre();;
        }
        if(datos.email() != null){
            this.email = datos.email();
        }
        if(datos.contrasena() != null){
            this.contrasena = datos.contrasena();
        }
    }
}
